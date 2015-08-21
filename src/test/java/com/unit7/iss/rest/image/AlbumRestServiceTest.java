package com.unit7.iss.rest.image;

import com.carlosbecker.guice.GuiceModules;
import com.carlosbecker.guice.GuiceTestRunner;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.unit7.iss.app.conf.GrizzlyServer;
import com.unit7.iss.app.conf.GuiceMainModule;
import com.unit7.iss.model.entity.Album;
import com.unit7.iss.model.entity.User;
import com.unit7.iss.service.user.UserService;
import com.unit7.iss.stub.image.AlbumStubBuilder;
import com.unit7.iss.stub.image.ImageStubBuilder;
import com.unit7.iss.stub.user.UserStubBuilder;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by breezzo on 20.08.15.
 */
@RunWith(GuiceTestRunner.class)
@GuiceModules(GuiceMainModule.class)
public class AlbumRestServiceTest {

    private static final String USER_LOGIN = "rest_user_test";

    private GrizzlyServer server;
    private WebResource resource;

    private AlbumStubBuilder albumBuilder;
    private ImageStubBuilder imageBuilder;
    private UserStubBuilder userBuilder;

    @Inject
    private UserService userService;

    @Before
    public void setup() {
        server = new GrizzlyServer();
        server.start();

        final Client client = Client.create();
        resource = client.resource(GrizzlyServer.BASE_URI);

        userBuilder = UserStubBuilder.newInstance()
                .setLogin(USER_LOGIN)
                .setName("album_rest_service_user")
                .setPassword("123456");

        imageBuilder = ImageStubBuilder.newInstance()
                .setContent(new byte[] { 1, 2, 3 })
                .setName("album_rest_service_image1")
                .setListInstanceCount(2);

        albumBuilder = AlbumStubBuilder.newInstance()
                .setName("album_rest_service_test")
                .setRecurseDepth(1)
                .setListInstanceCount(2)
                .setImageBuilder(imageBuilder)
                .setUserBuilder(userBuilder);
    }

    @Test
    public void create() {
        final Album album = album();

        final ClientResponse response = resource.path("/image/album/create")
                                                .accept(MediaType.APPLICATION_JSON_TYPE)
                                                .put(ClientResponse.class, album);

        Assert.assertEquals(Response.Status.OK, response.getStatus());
    }

    private Album album() {
        userBuilder.setStub(user());

        return albumBuilder.build();
    }

    private User user() {
        User user = userService.getByLogin(USER_LOGIN);

        if (user == null) {
            user = userBuilder.build();
        }

        return user;
    }

    @After
    public void tearDown() {
        server.stop();
    }
}
