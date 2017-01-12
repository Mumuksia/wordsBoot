/**
 * Copyright Flexpay AB
 */
package com.muksia.services;

import com.muksia.repository.BoplatsRowRepository;
import com.muksia.services.htmlunitapi.BoplatsRowFetchService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class WebParserService {

	final static Logger LOGGER = LoggerFactory.getLogger(WebParserService.class);

	@Autowired
	BoplatsRowRepository boplatsRowRepository;

	@Autowired
	BoplatsRowFetchService boplatsRowFetchService;

	public String getChangedRowOrEmpty(final String url, final String formPath, final String tableName,
									   final int row) throws
			IOException {
		String currentRow = boplatsRowFetchService.getHtmlRowFromTable(url, formPath, tableName, row);
		currentRow = StringUtils.trim(currentRow);
		LOGGER.info(StringUtils.isNoneEmpty(currentRow) ? currentRow.substring(0, 12) : "");

		if (boplatsRowRepository.checkIfRowExists(currentRow)) {
			return "";
		}

		boplatsRowRepository.updateDBWithCurrentRow(currentRow);

		return currentRow;
	}

	public void setBoplatsRowRepository(final BoplatsRowRepository boplatsRowRepository) {
		this.boplatsRowRepository = boplatsRowRepository;
	}

	public void setBoplatsRowFetchService(final BoplatsRowFetchService boplatsRowFetchService) {
		this.boplatsRowFetchService = boplatsRowFetchService;
	}

	public List<String> getAllChallenges(final String url) throws IOException {
		return boplatsRowFetchService.getListOfChallenges(url);
	}
}
