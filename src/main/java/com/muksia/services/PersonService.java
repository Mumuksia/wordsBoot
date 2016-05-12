package com.muksia.services;

import com.muksia.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

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
    
    public void addNewPerson(final String name, final String comment){
        persistenceService.updatePerson(new Person(name, comment, new ArrayList<>()));
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

}
