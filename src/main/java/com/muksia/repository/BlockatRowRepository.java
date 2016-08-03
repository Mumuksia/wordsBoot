/**
 * Copyright Flexpay AB
 */
package com.muksia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.muksia.model.Blockat;

import java.util.List;

public interface BlockatRowRepository extends MongoRepository<Blockat, String> {

    default void updateDBWithCurrentRow(final String currentRow){
        deleteAll();
        insert(new Blockat(currentRow));
    }

    default boolean checkIfRowExists(final String currentRow) {
        final List<Blockat> dbRows = findAll();
        return dbRows.stream().anyMatch(p -> p.getRow().equals(currentRow));
    }

}
