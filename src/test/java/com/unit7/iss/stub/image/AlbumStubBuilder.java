package com.unit7.iss.stub.image;

import com.unit7.iss.base.creation.Builder;
import com.unit7.iss.model.entity.Album;
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
        album.setImages(imageBuilder.buildList());
        album.setSubAlbums(buildList(depth));

        return album;
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

    private String name;
    private ImageStubBuilder imageBuilder = ImageStubBuilder.newInstance();
    private UserStubBuilder userBuilder = UserStubBuilder.newInstance();

    private int listInstanceCount;
    private int recurseDepth;
}
