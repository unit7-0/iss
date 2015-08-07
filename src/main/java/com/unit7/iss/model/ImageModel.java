package com.unit7.iss.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.bson.Document;
import org.bson.types.Binary;

import java.util.Arrays;

/**
 * Created by breezzo on 03.08.15.
 */
public class ImageModel implements BasicModel {

    public ImageModel() {
        this.document = new Document();
    }

    public ImageModel(Document document) {
        this.document = document;
    }

    public String getName() {
        return document.getString("name");
    }

    public void setName(String name) {
        document.put("name", name);
    }

    public byte[] getContent() {
        if (document.get("content") instanceof byte[])
            return document.get("content", byte[].class);
        else {
            final Binary data = (Binary) document.get("content");
            if (data != null)
                return data.getData();
            else
                return null;
        }
    }

    public void setContent(byte[] content) {
        document.put("content", content);
    }

    public Document getDocument() {
        return document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageModel that = (ImageModel) o;

        return Objects.equal(getName(), that.getName()) && Arrays.equals(getContent(), that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getContent());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", getName())
                .add("content", getContent())
                .toString();
    }

    private Document document;
}
