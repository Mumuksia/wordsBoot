package com.muksia.controllers;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.muksia.model.Person;
import com.muksia.services.PersonService;

/**
 *
 * @author yurzav
 */
@CrossOrigin(origins = "https://wordsboot.herokuapp.com")
@RestController
public class PersonResource {
    
    @Autowired
    PersonService personService;
    
    @RequestMapping("/persons/{person}")
    public
    @ResponseBody
    Person getPerson(@PathVariable("person") String person) {
        Optional<Person> optPerson = personService.getPersonByName(person);
        if (optPerson.isPresent()){
            return optPerson.get();
        }
        return null;
    }    
    
    @RequestMapping("/persons/test")
    public
    @ResponseBody
    String getPerson() {
        return new Date().toString();
    }

}
