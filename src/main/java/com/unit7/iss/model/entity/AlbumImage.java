package com.unit7.iss.model.entity;

import com.google.common.base.MoreObjects;
import com.unit7.iss.model.entity.base.AbstractEntity;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

/**
 * Created by breezzo on 22.08.15.
 */
// TODO сделать просто встраиваемым объектом без id, не реализовать интерфейст Entity?
@Entity(value = "albumImage", noClassnameStored = true)
public class AlbumImage extends AbstractEntity {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImagePreviewContent() {
        return imagePreviewContent;
    }

    public void setImagePreviewContent(byte[] imagePreviewContent) {
        this.imagePreviewContent = imagePreviewContent;
    }

    public Image getOriginal() {
        return original;
    }

    public void setOriginal(Image original) {
        this.original = original;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("imagePreviewContent", imagePreviewContent)
                .add("original", original)
                .toString();
    }

    private String name;
    private byte[] imagePreviewContent;

    @Reference(idOnly = true)
    private Image original;
}
