package com.unit7.iss.stub.image;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.unit7.iss.base.creation.Builder;
import com.unit7.iss.model.entity.Album;
import com.unit7.iss.model.entity.AlbumImage;
import com.unit7.iss.model.entity.Image;
import com.unit7.iss.service.ImageService;
import com.unit7.iss.stub.user.UserStubBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by breezzo on 19.08.15.
 */
public class AlbumStubBuilder implements Builder<Album> {

    public static AlbumStubBuilder newInstance() {
        return new AlbumStubBuilder();
    }

    @Override
    public Album build() {
        return build(recurseDepth);
    }

    public List<Album> buildList() {
        return buildList(recurseDepth + 1);
    }

    private List<Album> buildList(int depth) {
        if (depth <= 0)
            return Collections.emptyList();

        final List<Album> result = new ArrayList<>(listInstanceCount);
        for (int i = 0; i < listInstanceCount; ++i) {
            result.add(build(depth - 1));
        }

        return result;
    }

    private Album build(int depth) {
        final Album album = new Album();

        album.setName(name);
        album.setUser(userBuilder.build());
        album.setImages(convertImages(imageBuilder.buildList()));
        album.setSubAlbums(buildList(depth));

        return album;
    }

    private AlbumImage convertImage(Image image) {
        final AlbumImage albumImage = new AlbumImage();
        albumImage.setName(image.getName());
        albumImage.setOriginal(image);
        albumImage.setImagePreviewContent(imageService.generatePreview(image, previewWidth, previewHeight));

        return albumImage;
    }

    private List<AlbumImage> convertImages(List<Image> images) {
        return Lists.transform(images, new Function<Image, AlbumImage>() {
            @Override
            public AlbumImage apply(Image input) {
                return convertImage(input);
            }
        });
    }

    public AlbumStubBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public AlbumStubBuilder setUserBuilder(UserStubBuilder userBuilder) {
        this.userBuilder = userBuilder;
        return this;
    }

    public AlbumStubBuilder setImageBuilder(ImageStubBuilder imageBuilder) {
        this.imageBuilder = imageBuilder;
        return this;
    }

    public AlbumStubBuilder setListInstanceCount(int listInstanceCount) {
        this.listInstanceCount = listInstanceCount;
        return this;
    }

    public AlbumStubBuilder setRecurseDepth(int recurseDepth) {
        this.recurseDepth = recurseDepth;
        return this;
    }

    public AlbumStubBuilder setImageService(ImageService imageService) {
        this.imageService = imageService;
        return this;
    }

    public AlbumStubBuilder setPreviewWidth(int previewWidth) {
        this.previewWidth = previewWidth;
        return this;
    }

    public AlbumStubBuilder setPreviewHeight(int previewHeight) {
        this.previewHeight = previewHeight;
        return this;
    }

    private String name;
    private ImageStubBuilder imageBuilder = ImageStubBuilder.newInstance();
    private UserStubBuilder userBuilder = UserStubBuilder.newInstance();
    private ImageService imageService;

    private int previewWidth = 100;
    private int previewHeight = 100;

    private int listInstanceCount;
    private int recurseDepth;
}
