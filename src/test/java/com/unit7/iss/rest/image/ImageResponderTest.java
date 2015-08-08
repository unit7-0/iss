package com.unit7.iss.rest.image;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.unit7.iss.app.GrizzlyServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by breezzo on 08.08.15.
 */
public class ImageResponderTest {

    private static final Logger logger = LoggerFactory.getLogger(ImageResponderTest.class);

    private GrizzlyServer server;
    private WebResource resource;

    @Before
    public void setup() {
        server = new GrizzlyServer();
        server.start();

        Client c = Client.create();
        resource = c.resource(GrizzlyServer.BASE_URI);
    }

    @Test
    public void createImage() {
        final String response = resource.path("/rest/image/create/nat.jpg").get(String.class);

        logger.info("response: {}", response);
    }

    @Test
    public void getImageSuccess() {
        resource.path("/rest/image/create/createSuccess.jpg").get(String.class);
        final byte[] response = resource.path("/rest/image/get/createSuccess.jpg").get(byte[].class);

        logger.info("response: {}", response);

        assertTrue(response.length > 0);
    }

    @Test(expected = UniformInterfaceException.class)
    public void getImageError() {
        resource.path("/rest/image/get/bararabararararar___!2323.jpg").get(String.class);
    }

    @After
    public void tearDown() {
        server.stop();
    }
}
