package com.muksia.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

public class WebClientProviderTest {

	WebClientProvider webClientProvider = new WebClientProvider();

	@Test
	public void testGetWebClient() throws Exception {
		WebClient webClient = webClientProvider.getWebClient();

		assertFalse(webClient.getOptions().isCssEnabled());
		assertTrue(webClient.getOptions().isJavaScriptEnabled());
		assertFalse(webClient.getOptions().isThrowExceptionOnScriptError());
		assertNotNull(webClient.getAjaxController());
		assertEquals(BrowserVersion.FIREFOX_45, webClient.getBrowserVersion());

	}
}
