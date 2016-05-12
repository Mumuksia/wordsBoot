package com.muksia.controllers;

import com.muksia.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.json.Json;
import javax.json.JsonObject;

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
    

    @RequestMapping(method =RequestMethod.POST, path = "/words/{person}/{word}/{translation}")
    public JsonObject addWord(@PathVariable("word") String word, @PathVariable("translation") String translation, @PathVariable("person") String person){
        dictionaryService.addWord(word, translation, person);
        return Json.createObjectBuilder().add(dictionaryService.getRandomWordForPerson(person).get().getValue(),  2).build();
    }

}
