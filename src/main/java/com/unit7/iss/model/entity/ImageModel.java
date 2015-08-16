package com.unit7.iss.model.entity;

import com.google.common.base.MoreObjects;

/**
 * Created by breezzo on 16.08.15.
 */
@org.mongodb.morphia.annotations.Entity("images")
public class ImageModel extends AbstractEntity {
    private String name;
    private byte[] content;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("content", content)
                .toString();
    }
}
