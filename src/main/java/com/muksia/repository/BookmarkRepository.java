/**
 * Copyright Flexpay AB
 */
package com.muksia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.muksia.model.Bookmark;

public interface BookmarkRepository extends MongoRepository<Bookmark, String> {

}
