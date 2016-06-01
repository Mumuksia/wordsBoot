package com.muksia.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.easymock.EasyMock;
import org.junit.Test;

import com.muksia.model.Person;
import com.muksia.model.Word;
import com.muksia.repository.PersonRepository;
import com.muksia.repository.WordRepository;

public class PersistenceServiceTest {

	public static final String NAME = "name";
	public static final String UPDATED_NAME = "Updated_Name";
	public static final String VALUE = "value";
	private PersistenceService persistenceService = new PersistenceService();

	@Test
	public void testGetAllPersons() throws Exception {
		PersonRepository personRepository = EasyMock.mock(PersonRepository.class);
		EasyMock.expect(personRepository.findAll()).andReturn(mockPersons()).times(1);
		EasyMock.replay(personRepository);
		persistenceService.setPersonRepository(personRepository);

		assertEquals(persistenceService.getAllPersons().size(), 2);
	}

	@Test
	public void testGerPerson() throws Exception {
		PersonRepository personRepository = EasyMock.mock(PersonRepository.class);
		EasyMock.expect(personRepository.findByName(NAME)).andReturn(mockPerson(NAME)).times(1);
		EasyMock.replay(personRepository);
		persistenceService.setPersonRepository(personRepository);

		Optional<Person> person = persistenceService.gerPerson(NAME);
		assertTrue(person.isPresent());
		assertEquals(person.get().getName(), NAME);
	}

	@Test
	public void testUpdatePerson() throws Exception {
		PersonRepository personRepository = EasyMock.mock(PersonRepository.class);
		EasyMock.expect(personRepository.save(mockPerson(NAME))).andReturn(mockPerson(UPDATED_NAME)).times(1);
		personRepository.delete(mockPerson(NAME));
		EasyMock.expectLastCall();
		EasyMock.replay(personRepository);
		persistenceService.setPersonRepository(personRepository);

		Person person = persistenceService.updatePerson(mockPerson(NAME));
		EasyMock.verify(personRepository);
		assertEquals(person, mockPerson(UPDATED_NAME));
	}

	@Test
	public void testAddWord() throws Exception {
		Word word = mockWord(VALUE);
		WordRepository wordRepository = EasyMock.mock(WordRepository.class);
		EasyMock.expect(wordRepository.save(word)).andReturn(word).times(1);
		EasyMock.replay(wordRepository);
		persistenceService.setWordRepository(wordRepository);

		Word wordResult = persistenceService.addWord(word);
		assertNotNull(wordResult);
		assertEquals(wordResult.getValue(), VALUE);
	}

	@Test
	public void testGetAllWords() throws Exception {
		WordRepository wordRepository = EasyMock.mock(WordRepository.class);
		EasyMock.expect(wordRepository.findAll()).andReturn(mockWords()).times(1);
		EasyMock.replay(wordRepository);
		persistenceService.setWordRepository(wordRepository);

		List<Word> words = persistenceService.getAllWords();
		assertEquals(words.size(), 2);
	}

	private List<Word> mockWords() {
		return Arrays.asList(mockWord(VALUE), mockWord("Value2"));
	}

	private List<Person> mockPersons() {
		return Arrays.asList(mockPerson(NAME), mockPerson("name2"));
	}

	private Person mockPerson(final String name) {
		return new Person(name, "comment", new ArrayList<>());
	}

	private Word mockWord(final String value) {
		return new Word(value, "translation", "person");
	}
}
