package com.muksia.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.easymock.EasyMock;
import org.junit.Test;

import com.muksia.model.Word;

public class DictionaryServiceTest {

	private DictionaryService dictionaryService = new DictionaryService();

	@Test
	public void testGetRandomWordForPerson() throws Exception {
		final List<Word> words = mockWords();
		PersistenceService persistenceService = EasyMock.mock(PersistenceService.class);
		EasyMock.expect(persistenceService.getAllWords()).andReturn(words).anyTimes();
		EasyMock.replay(persistenceService);
		dictionaryService.setPersistenceService(persistenceService);

		for (int i = 0; i < 10000; i++) {
			Optional<Word> word = dictionaryService.getRandomWordForPerson("person");
			assertTrue(word.isPresent());
			assertTrue(word.get().getValue().length() == 6);
		}

		assertFalse(dictionaryService.getRandomWordForPerson("person4").isPresent());

	}


	private List<Word> mockWords() {
		return Arrays.asList(new Word("value1", "translation", "person"), new Word("value2", "translation", "person"),
							 new Word("value3", "translation", "person"), new Word("value4", "translation", "person"),
							 new Word("value5", "translation", "person"), new Word("value6", "translation", "person"),
							 new Word("value7", "translation", "person"), new Word("value8", "translation", "person"),
							 new Word("value9", "translation", "person"), new Word("value10", "translation", "person2"),
							 new Word("value11", "translation", "person2"),
							 new Word("value12", "translation", "person2"),
							 new Word("value13", "translation", "person2"),
							 new Word("value14", "translation", "person3"));
	}

	@Test
	public void testAddWord() throws Exception {
		PersistenceService persistenceService = EasyMock.mock(PersistenceService.class);
		EasyMock.expect(persistenceService.addWord(mockWord())).andReturn(mockWord()).times(1);
		EasyMock.replay(persistenceService);
		dictionaryService.setPersistenceService(persistenceService);

		final Word result = dictionaryService.addWord("word", "translation", "personName");
		assertEquals(result, mockWord());
	}

	private Word mockWord() {
		return new Word("word", "translation", "personName");
	}
}
