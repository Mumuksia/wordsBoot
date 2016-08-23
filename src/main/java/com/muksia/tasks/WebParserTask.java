package com.muksia.tasks;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.muksia.services.WebParserService;

@Component
public class WebParserTask {

	final static Logger LOGGER = LoggerFactory.getLogger(WebParserTask.class);

	@Autowired
	private WebParserService webParserService;

	@Autowired
	private MailSender mailSender;

	@Scheduled(fixedRate = 600000)
	public void reportBlocketRow() throws IOException {
		final String result =
				webParserService.getChangedRowOrEmpty("https://nya.boplats.se/",
													  "//form[@action='https://nya.boplats.se/sok']", "objectlist", 4);

		if (!"".equals(result)) {
			sendEmailSpring(result);
		}
	}

	//	@Scheduled(fixedRate = 6000000)
	public void reportTime() throws IOException {
		sendEmail("test asdasd");
	}

	public void sendEmail(final String body) {
		final String username = "mumuksia@mail.com";
		final String password = "M@1l1234";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.mail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
											  new javax.mail.Authenticator() {
												  protected PasswordAuthentication getPasswordAuthentication() {
													  return new PasswordAuthentication(username, password);
												  }
											  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mumuksia@mail.com"));
			message.setRecipients(Message.RecipientType.TO,
								  InternetAddress.parse("yurko.zavada@gmail.com"));
			message.setSubject("Blocket update");
			message.setText(body);

			Transport.send(message);

			LOGGER.info("Mail sent succesfully!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendEmailSpring(final String body) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo("yurko.zavada@gmail.com");
		simpleMailMessage.setSubject("Boplats update");
		simpleMailMessage.setFrom("mumuksiatemp@gmail.com");
		simpleMailMessage.setText(body);


		mailSender.send(simpleMailMessage);
		LOGGER.info("Spring Mail sent succesfully!");

	}

}
