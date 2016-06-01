package com.muksia.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.easymock.EasyMock;
import org.junit.Test;

import com.muksia.model.Person;

public class PersonServiceTest {

	public static final String NAME = "name";
	public static final String COMMENT = "comment";
	private PersonService personService = new PersonService();

	@Test
	public void testGetPersonByName() throws Exception {
		PersistenceService persistenceService = EasyMock.mock(PersistenceService.class);
		EasyMock.expect(persistenceService.gerPerson(NAME)).andReturn(mockPerson()).times(1);
		EasyMock.replay(persistenceService);
		personService.setPersistenceService(persistenceService);

		Optional<Person> person = personService.getPersonByName(NAME);
		assertTrue(person.isPresent());
		assertEquals(person.get().getName(), NAME);

	}

	@Test
	public void testAddNewPerson() throws Exception {
		PersistenceService persistenceService = EasyMock.mock(PersistenceService.class);
		EasyMock.expect(persistenceService.updatePerson(new Person(NAME, COMMENT, new ArrayList<>()))).andReturn(
				mockPerson().get()).times(1);
		EasyMock.replay(persistenceService);
		personService.setPersistenceService(persistenceService);

		final Person p = personService.addNewPerson(NAME, COMMENT);
		assertEquals(p, mockPerson().get());

	}


	private Optional<Person> mockPerson() {
		return Optional.of(new Person(NAME, COMMENT, new ArrayList<>()));
	}
}
