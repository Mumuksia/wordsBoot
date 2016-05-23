package com.muksia.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.muksia.model.Word;

/**
 *
 * @author yurzav
 */
@Service
public class DictionaryService {

    @Autowired
    PersistenceService persistenceService;
    
    @Autowired
    PersonService personService;

    private final Random randomGenerator = new Random();

    public Optional<Word> getRandomWordForPerson(final String personName) {

        final List<Word> wordList = persistenceService.getAllWords();
        if (CollectionUtils.isEmpty(wordList)) {
            return Optional.empty();
        }
        return getRandomWord(wordList);
    }

    public void addWord(final String word, final String translation, final String personName) {
        persistenceService.addWord(new Word(word, translation, personName));
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }


    private Optional<Word> getRandomWord(final List<Word> words) {
        final int randomId = randomGenerator.nextInt(words.size());
        return Optional.of(words.get(randomId));
    }

}
