package com.muksia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muksia.model.Person;
import com.muksia.repository.PersonRepository;

/**
 *
 * @author yurzav
 */
@Service
public class PersistenceService {
    
    @Autowired
    private PersonRepository personRepository;
    
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    public Optional<Person> gerPerson(final String name){
        return Optional.of(personRepository.findByName(name));
    }
    
    public void updatePerson(final Person p) {
        personRepository.save(p);
    }
}
