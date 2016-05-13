/**
 * Copyright Flexpay AB
 */
package com.muksia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.muksia.model.Person;

public interface PersonRepository extends MongoRepository<Person, String> {

	Person findByName(String name);
}
