package com.unit7.iss.model.entity;

import com.google.common.base.MoreObjects;
import org.mongodb.morphia.annotations.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by breezzo on 16.08.15.
 */
@org.mongodb.morphia.annotations.Entity("albums")
public class AlbumEntity extends AbstractEntity {
    private String name;

    @Reference
    private List<AlbumEntity> subAlbums = Collections.emptyList();

    @Reference(idOnly = true)
    private List<ImageEntity> images = Collections.emptyList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AlbumEntity> getSubAlbums() {
        return subAlbums;
    }

    public void setSubAlbums(List<AlbumEntity> subAlbums) {
        this.subAlbums = subAlbums;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageEntity> images) {
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
