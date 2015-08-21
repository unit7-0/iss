package com.unit7.iss.model.entity;

import com.google.common.base.MoreObjects;
import com.unit7.iss.model.entity.base.AbstractEntity;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

import java.util.Collections;
import java.util.List;

/**
 * Created by breezzo on 16.08.15.
 */
@org.mongodb.morphia.annotations.Entity("albums")
public class Album extends AbstractEntity {
    private String name;

    @Reference(idOnly = true)
    private User user;

    @Reference
    private List<Album> subAlbums = Collections.emptyList();

    @Embedded
    private List<AlbumImage> images = Collections.emptyList();

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

    public List<AlbumImage> getImages() {
        return images;
    }

    public void setImages(List<AlbumImage> images) {
        this.images = images;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("subAlbums", subAlbums)
                .add("images", images)
                .add("user", user)
                .toString();
    }
}
