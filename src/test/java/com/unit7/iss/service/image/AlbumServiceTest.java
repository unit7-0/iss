package com.unit7.iss.service.image;

import com.carlosbecker.guice.GuiceModules;
import com.carlosbecker.guice.GuiceTestRunner;
import com.google.inject.Inject;
import com.unit7.iss.app.conf.GuiceMainModule;
import com.unit7.iss.db.DatabaseFactory;
import com.unit7.iss.model.entity.Album;
import com.unit7.iss.model.entity.User;
import com.unit7.iss.service.AlbumService;
import com.unit7.iss.service.user.UserService;
import com.unit7.iss.stub.image.AlbumStubBuilder;
import com.unit7.iss.stub.image.ImageStubBuilder;
import com.unit7.iss.stub.user.UserStubBuilder;
import com.unit7.iss.util.compare.ReflectionComparator;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by breezzo on 19.08.15.
 */
@RunWith(GuiceTestRunner.class)
@GuiceModules(GuiceMainModule.class)
public class AlbumServiceTest {

    private static final String USER_LOGIN = "service_user";

    @Inject
    private AlbumService albumService;

    @Inject
    private UserService userService;

    private AlbumStubBuilder albumBuilder;
    private ImageStubBuilder imageBuilder;
    private UserStubBuilder userBuilder;

    @Before
    public void setup() {
        userBuilder = UserStubBuilder.newInstance()
                .setLogin(USER_LOGIN)
                .setName("album_service_user")
                .setPassword("123456");

        imageBuilder = ImageStubBuilder.newInstance()
                .setContent(new byte[] { 1, 2, 3 })
                .setName("album_service_image1")
                .setListInstanceCount(2);

        albumBuilder = AlbumStubBuilder.newInstance()
                            .setName("album_service_test")
                            .setRecurseDepth(1)
                            .setListInstanceCount(2)
                            .setImageBuilder(imageBuilder)
                            .setUserBuilder(userBuilder);
    }

    @Test
    public void create() {
        userBuilder.setStub(user());

        final Album album = album();

        albumService.create(album);

        final Album fromDB = albumService.get(album.getId());

        Assert.assertTrue(equals(album, fromDB));
    }

    @Test
    public void get() {
        final Album album = album();

        albumService.create(album);

        Assert.assertTrue(equals(album, albumService.get(album.getId())));
    }

    @Test
    public void getByUser() {
        final Album a = album();
        final Album b = album();

        albumService.create(a);
        albumService.create(b);

        final List<Album> albums = albumService.getByUser(user());

        Album first = null;
        for (Album album : albums) {
            if (equals(album, a)) {
                first = album;
                break;
            }
        }

        Album second = null;
        for (Album album : albums) {
            if (equals(album, b) && !equals(album, first)) {
                second = album;
                break;
            }
        }

        Assert.assertTrue(first != null && second != null);
    }

    private User user() {
        User user = userService.getByLogin(USER_LOGIN);

        if (user == null) {
            user = userBuilder.build();
        }

        return user;
    }

    private Album album() {
        userBuilder.setStub(user());

        return albumBuilder.build();
    }

    private boolean equals(Album a, Album b) {
        return new ReflectionComparator().compare(a, b) == 0;
    }

    @AfterClass
    public static void tearDown() {
        DatabaseFactory.instance().destroy();
    }
}
