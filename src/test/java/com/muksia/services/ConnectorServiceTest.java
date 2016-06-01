package com.muksia.services;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.mongodb.client.MongoCollection;

public class ConnectorServiceTest {

	private ConnectorService connectorService = new ConnectorService();

	@Test
	public void testGetMongoClient() throws Exception {
		MongoCollection mongoCollection = connectorService.getMongoClient();
		assertNotNull(mongoCollection);
	}
}
