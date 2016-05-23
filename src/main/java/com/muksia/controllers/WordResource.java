package com.muksia.controllers;

import java.util.Optional;

import javax.json.Json;
import javax.json.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.muksia.model.Word;
import com.muksia.services.DictionaryService;

/**
 *
 * @author Muksia
 */
@RestController
public class WordResource {
    
    @Autowired
    DictionaryService dictionaryService;
   
    @RequestMapping("/words")  
    public JsonObject developer(){
        dictionaryService.addWord("test", "ttttttrrrraaaa", "test");
        return Json.createObjectBuilder().add(dictionaryService.getRandomWordForPerson("test").get().getValue(),  2).build();
    }
    

    @RequestMapping(method =RequestMethod.GET, path = "/words/{person}/{word}/{translation}")
    public JsonObject addWord(@PathVariable("word") String word, @PathVariable("translation") String translation, @PathVariable("person") String person){
        dictionaryService.addWord(word, translation, person);
        return Json.createObjectBuilder().add(dictionaryService.getRandomWordForPerson(person).get().getValue(),  2).build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/word/{personName}")
    public String getWordForPerson(@PathVariable("personName") String personName) {
        Optional<Word> wordOptional = dictionaryService.getRandomWordForPerson(personName);
        return wordOptional.isPresent() ? wordOptional.get().toString() : "Now words found for " + personName;
    }

}
