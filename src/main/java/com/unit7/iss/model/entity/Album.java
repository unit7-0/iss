package com.unit7.iss.model.entity;

import com.google.common.base.MoreObjects;
import org.mongodb.morphia.annotations.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by breezzo on 16.08.15.
 */
@org.mongodb.morphia.annotations.Entity("albums")
public class Album extends AbstractEntity {
    private String name;

    @Reference
    private List<Album> subAlbums = Collections.emptyList();

    @Reference(idOnly = true)
    private List<Image> images = Collections.emptyList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getSubAlbums() {
        return subAlbums;
    }

    public void setSubAlbums(List<Album> subAlbums) {
        this.subAlbums = subAlbums;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("subAlbums", subAlbums)
                .add("images", images)
                .toString();
    }
}
