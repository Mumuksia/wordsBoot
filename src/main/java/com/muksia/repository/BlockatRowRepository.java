/**
 * Copyright Flexpay AB
 */
package com.muksia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.muksia.model.Blockat;

public interface BlockatRowRepository extends MongoRepository<Blockat, String> {

}
