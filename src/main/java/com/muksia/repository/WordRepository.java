/**
 * Copyright Flexpay AB
 */
package com.muksia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.muksia.model.Word;


public interface WordRepository extends MongoRepository<Word, String> {

}
