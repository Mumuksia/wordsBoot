package com.muksia.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.muksia.model.Word;
import com.muksia.services.DictionaryService;

/**
 *
 * @author Muksia
 */
@CrossOrigin(origins = "https://wordsboot.herokuapp.com")
@RestController
public class WordResource {
    
    @Autowired
    DictionaryService dictionaryService;
   
    @RequestMapping("/words")
    public
    @ResponseBody
    Word developer() {
        dictionaryService.addWord("test", "ttttttrrrraaaa", "test");
        return dictionaryService.getRandomWordForPerson("test").get();
    }
    

    @RequestMapping(method =RequestMethod.GET, path = "/words/{person}/{word}/{translation}")
    public
    @ResponseBody
    Word addWord(@PathVariable("word") String word, @PathVariable("translation") String translation,
                 @PathVariable("person") String person) {
        dictionaryService.addWord(word, translation, person);
        return dictionaryService.getRandomWordForPerson(person).get();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/word/{personName}")
    public
    @ResponseBody
    Word getWordForPerson(@PathVariable("personName") String personName) {
        Optional<Word> wordOptional = dictionaryService.getRandomWordForPerson(personName);
        return wordOptional.isPresent() ? wordOptional.get() : null;
    }

}
