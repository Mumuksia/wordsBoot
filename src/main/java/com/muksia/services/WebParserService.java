/**
 * Copyright Flexpay AB
 */
package com.muksia.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muksia.repository.BoplatsRowRepository;

@Service
public class WebParserService {

	@Autowired
	BoplatsRowRepository boplatsRowRepository;

	@Autowired
	BoplatsRowFetchService boplatsRowFetchService;

	public String getChangedRowOrEmpty(final String url, final String formPath, final String tableName,
									   final int row) throws
			IOException {
		final String currentRow = boplatsRowFetchService.getHtmlRowFromTable(url, formPath, tableName, row);

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
}
