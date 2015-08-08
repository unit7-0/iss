package com.unit7.iss.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by breezzo on 02.08.15.
 */
public class DatabaseFactory {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseFactory.class);

    private static final String HOST = "localhost";
    private static final int PORT = 27033;

    private MongoClient mongoClient;

    private DatabaseFactory() {
        logger.info("Connect to mongo database");

        mongoClient = new MongoClient(HOST, PORT);

        // TODO where to close?
    }

    public MongoDatabase getMongo(String databaseName) {
        return mongoClient.getDatabase(databaseName);
    }

    public void destroy() {
        mongoClient.close();
        instance = null;
    }

    public static DatabaseFactory instance() {
        if (instance == null) {
            synchronized (DatabaseFactory.class) {
                if (instance == null) {
                    instance = new DatabaseFactory();
                }
            }
        }

        return instance;
    }

    private static DatabaseFactory instance;
}
