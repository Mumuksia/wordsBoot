package com.muksia.controllers;

import com.muksia.model.Person;
import com.muksia.services.PersonService;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yurzav
 */
@RestController
public class PersonResource {
    
    @Autowired
    PersonService personService;
    
    @RequestMapping("/persons/{person}")    
    public Person getPerson(@PathVariable("person") String person){
        Optional<Person> optPerson = personService.getPersonByName(person);
        if (optPerson.isPresent()){
            return optPerson.get();
        }
        return null;
    }    
    
    @RequestMapping("/persons/test")
    public String getPerson(){
        return new Date().toString();
    }

}
