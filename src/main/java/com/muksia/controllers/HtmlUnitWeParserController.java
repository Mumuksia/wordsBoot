/**
 * Copyright Flexpay AB
 */
package com.muksia.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

@CrossOrigin
@RestController
public class HtmlUnitWeParserController {

	final String urlbase = "https://nya.boplats.se/";

	@RequestMapping("/webParser")
	@ResponseBody
	public String getInfo() throws IOException {
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());


		final HtmlPage page1 = webClient.getPage(urlbase);
		webClient.waitForBackgroundJavaScript(60000);

		// Get the form that we are dealing with and within that form,
		// find the submit button and the field that we want to change.
		//		final HtmlForm form = page1.getFormByName("objectsearchform");
		final HtmlForm form = page1.getFirstByXPath("//form[@action='https://nya.boplats.se/sok']");

		final HtmlSubmitInput button = form.getInputByName("search");

		// Now submit the form by clicking the button and get back the second page.
		final HtmlPage page2 = button.click();

		return page2.getAnchorByName("imageitem").toString();
	}
}
