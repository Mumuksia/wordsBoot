package com.muksia.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muksia.model.Person;

/**
 *
 * @author yurzav
 */
@Service
public class PersonService {
    
    @Autowired
    PersistenceService persistenceService;
    
    public Optional<Person> getPersonByName(final String name){
        return persistenceService.gerPerson(name);
    }

    public Person addNewPerson(final String name, final String comment) {
        return persistenceService.updatePerson(new Person(name, comment, new ArrayList<>()));
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

}
