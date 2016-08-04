/**
 * Copyright Flexpay AB
 */
package com.muksia.services;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.muksia.repository.BoplatsRowRepository;

public class WebParserServiceTest {

	public static final String URL = "url";
	public static final String PATH = "path";
	public static final String TABLE_NAME = "tableName";
	public static final String SOME_ROW = "someRow";
	private WebParserService webParserService = new WebParserService();

	@Test
	public void testGetChangeRowOrEmpty_Exists() throws IOException {
		setBoplatsRowFetchService();

		BoplatsRowRepository boplatsRowRepository = mock(BoplatsRowRepository.class);
		expect(boplatsRowRepository.checkIfRowExists(SOME_ROW)).andReturn(true).times(1);
		replay(boplatsRowRepository);
		webParserService.setBoplatsRowRepository(boplatsRowRepository);

		final String replay = webParserService.getChangedRowOrEmpty(URL, PATH, TABLE_NAME, 1);

		Assert.assertEquals(StringUtils.EMPTY, replay);
	}

	@Test
	public void testGetChangeRowOrEmpty_NotExists() throws IOException {
		setBoplatsRowFetchService();

		BoplatsRowRepository boplatsRowRepository = mock(BoplatsRowRepository.class);
		expect(boplatsRowRepository.checkIfRowExists(SOME_ROW)).andReturn(false).times(1);
		boplatsRowRepository.updateDBWithCurrentRow(SOME_ROW);
		EasyMock.expectLastCall().once();
		replay(boplatsRowRepository);
		webParserService.setBoplatsRowRepository(boplatsRowRepository);

		final String replay = webParserService.getChangedRowOrEmpty(URL, PATH, TABLE_NAME, 1);

		EasyMock.verify(boplatsRowRepository);

		Assert.assertEquals(SOME_ROW, replay);
	}

	private void setBoplatsRowFetchService() throws IOException {
		BoplatsRowFetchService boplatsRowFetchService = mock(BoplatsRowFetchService.class);
		expect(boplatsRowFetchService.getHtmlRowFromTable(URL, PATH, TABLE_NAME, 1)).andReturn(SOME_ROW).times(1);
		replay(boplatsRowFetchService);
		webParserService.setBoplatsRowFetchService(boplatsRowFetchService);
	}
}
