package com.unit7.iss.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.unit7.iss.model.ImageModel;
import com.unit7.iss.model.behavior.ModelFactory;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by breezzo on 02.08.15.
 */
public class ImageDAO {
    private static final Logger logger = LoggerFactory.getLogger(ImageDAO.class);

    private static final String IMAGE_DATABASE_NAME = "image";
    private static final String IMAGE_COLLECTION_NAME = "images";


    private MongoDatabase getDatabase() {
        return DatabaseFactory.instance().getMongo(IMAGE_DATABASE_NAME);
    }

    private MongoCollection<Document> getCollection() {
        return getDatabase().getCollection(IMAGE_COLLECTION_NAME);
    }


    public ImageModel getImage(String name) {
        //TODO
        final Document filter = new Document("name", name);

        if (getCollection().count(filter) != 0) {
            return ModelFactory.createImage(getCollection().find(filter).first());
        }

        return null;
    }

    public void createImage(ImageModel image) {
        getCollection().insertOne(new Document(ModelFactory.toMap(image)));
    }
}