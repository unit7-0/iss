package com.unit7.iss.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.unit7.iss.model.UserModel;
import com.unit7.iss.model.behavior.ModelFactory;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Created by breezzo on 09.08.15.
 */
public class UserDAO {

    // TODO config
    private static final String IMAGE_DATABASE_NAME = "image";
    private static final String USERS_COLLECTIONS_NAME = "users";

    private MongoDatabase getDatabase() {
        return DatabaseFactory.instance().getMongo(IMAGE_DATABASE_NAME);
    }

    private MongoCollection<Document> getCollection() {
        return getDatabase().getCollection(USERS_COLLECTIONS_NAME);
    }

    public String createUser(UserModel model) {
        final Document doc = new Document(ModelFactory.toMap(model));
        getCollection().insertOne(doc);

        return ((ObjectId) doc.get("_id")).toHexString();
    }
}
