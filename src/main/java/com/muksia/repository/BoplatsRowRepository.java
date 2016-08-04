/**
 * Copyright Flexpay AB
 */
package com.muksia.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.muksia.model.Blockat;

public interface BoplatsRowRepository extends MongoRepository<Blockat, String> {

    default void updateDBWithCurrentRow(final String currentRow){
        deleteAll();
        insert(new Blockat(currentRow));
    }

    default boolean checkIfRowExists(final String currentRow) {
        final List<Blockat> dbRows = findAll();
        return dbRows.stream().anyMatch(p -> p.getRow().equals(currentRow));
    }

}
