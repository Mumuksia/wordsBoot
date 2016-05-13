package com.muksia.services;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author yurzav
 */
public class ConnectorService {

    public MongoCollection getMongoClient() {
        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://muksia:iverson@ds045511.mlab.com:45511/dictionary");
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase database = mongoClient.getDatabase(mongoClientURI.getDatabase());
        return database.getCollection("persons");
    }

}
