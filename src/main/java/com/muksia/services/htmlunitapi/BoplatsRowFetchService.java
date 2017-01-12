package com.muksia.services.htmlunitapi;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.muksia.services.WebClientProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: Muksia
 * Date: 03/08/2016
 * Time: 22:20
 */
@Service
public class BoplatsRowFetchService {

	@Autowired
	WebClientProvider webClientProvider;

	public String getHtmlRowFromTable(final String url, final String formPath, final String tableName,
									  final int row) throws IOException {
		final HtmlTable table;
		try (WebClient webClient = webClientProvider.getWebClient()) {
			final HtmlPage page1 = webClient.getPage(url);
			webClient.waitForBackgroundJavaScript(60000);

			final HtmlForm form = page1.getFirstByXPath(formPath);

			final HtmlSubmitInput button = form.getInputByName("search");
			HtmlPage page2 = button.click();
			table = page2.getHtmlElementById(tableName);
		}
		return table.getRow(row).getCell(0).getTextContent();
	}

	public List<String> getListOfChallenges(final String url) throws IOException {
		try (WebClient webClient = webClientProvider.getWebClient()) {
			final HtmlPage page1 = webClient.getPage(url);
			webClient.waitForBackgroundJavaScript(60000);
			return page1.getByXPath("//div[contains(@class, 'set_name')]").stream().
					map(t->((HtmlDivision)t).asText()).collect(Collectors.toList());
		}

	}
}
