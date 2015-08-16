package com.unit7.iss.db;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by breezzo on 15.08.15.
 */
public class DatabaseFactory {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseFactory.class);

    private static DatabaseFactory instance;

    private static final String HOST = "localhost";
    private static final int PORT = 27033;

    public static final String ISS_DATABASE_NAME = "iss";

    private static final String ENTITY_PACKAGE_NAME = "com.unit7.iss.model.entity";

    private Morphia morphia;
    private MongoClient mongo;

    private DatabaseFactory(String packageName) {

        logger.info("instantiate DatabaseFactory [ entityPackageName: {}, host: {}, port: {} ]", packageName, HOST, PORT);

        mongo = new MongoClient(HOST, PORT);
        morphia = new Morphia();

        morphia.mapPackage(packageName);
    }

    public static DatabaseFactory instance() {
        if (instance == null) {
            synchronized (DatabaseFactory.class) {
                if (instance == null) {
                    instance = new DatabaseFactory(ENTITY_PACKAGE_NAME);
                }
            }
        }

        return instance;
    }

    public Datastore createDatastore(String datastoreName) {
        logger.debug("create datastore with name: {}", datastoreName);

        return morphia.createDatastore(mongo, datastoreName);
    }

    public void destroy() {
        logger.info("destroing DatabaseFactory");

        mongo.close();
        instance = null;
    }
}
