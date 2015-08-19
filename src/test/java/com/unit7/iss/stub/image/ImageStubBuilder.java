package com.unit7.iss.stub.image;

import com.unit7.iss.model.entity.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by breezzo on 19.08.15.
 */
public class ImageStubBuilder {
    public static ImageStubBuilder newInstance() {
        return new ImageStubBuilder();
    }

    public Image build() {
        final Image image = new Image();
        image.setName(name);
        image.setContent(content);

        return image;
    }

    public List<Image> buildList() {
        final List<Image> result = new ArrayList<>(listInstanceCount);
        for (int i = 0; i < listInstanceCount; ++i) {
            result.add(build());
        }

        return result;
    }

    public ImageStubBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ImageStubBuilder setContent(byte[] content) {
        this.content = content;
        return this;
    }

    public ImageStubBuilder setListInstanceCount(int listInstanceCount) {
        this.listInstanceCount = listInstanceCount;
        return this;
    }

    private String name;
    private byte[] content;

    private int listInstanceCount;
}
