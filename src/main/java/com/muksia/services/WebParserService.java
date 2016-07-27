/**
 * Copyright Flexpay AB
 */
package com.muksia.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.muksia.model.Blockat;
import com.muksia.repository.BlockatRowRepository;

@Service
public class WebParserService {

	@Autowired
	BlockatRowRepository blockatRowRepository;

	public String getChangedRowOrEmpty(final String url, final String formPath, final String tableName,
									   final int row) throws
			IOException {
		final String currentRow = getHtmlRowFromTable(url, formPath, tableName, row);

		if (checkIfRowExists(currentRow)) {
			return "";
		}

		updateDBWithCurrentRow(currentRow);

		return currentRow;
	}

	private String getHtmlRowFromTable(final String url, final String formPath, final String tableName,
									   final int row) throws IOException {
		final WebClient webClient = getWebClient();

		final HtmlPage page1 = webClient.getPage(url);
		webClient.waitForBackgroundJavaScript(60000);

		final HtmlForm form = page1.getFirstByXPath(formPath);

		final HtmlSubmitInput button = form.getInputByName("search");
		final HtmlPage page2 = button.click();
		final HtmlTable table = page2.getHtmlElementById(tableName);

		return table.getRow(row).getCell(0).getTextContent();
	}

	private void updateDBWithCurrentRow(final String currentRow) {
		blockatRowRepository.deleteAll();
		blockatRowRepository.insert(new Blockat(currentRow));
	}

	private boolean checkIfRowExists(final String currentRow) {
		final List<Blockat> dbRows = blockatRowRepository.findAll();
		return dbRows.stream().anyMatch(p -> p.getRow().equals(currentRow));
	}

	private WebClient getWebClient() {
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		return webClient;
	}

}
