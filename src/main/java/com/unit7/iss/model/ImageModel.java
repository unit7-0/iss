package com.unit7.iss.model;

/**
 * Created by breezzo on 03.08.15.
 */
public class ImageModel implements BasicModel {

    public ImageModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }


    private String name;
    private byte[] content;
}
