package com.unit7.iss.dao;

import com.google.common.base.Objects;
import com.google.common.io.ByteStreams;
import com.unit7.iss.model.ImageModel;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Created by breezzo on 06.08.15.
 */
public class ImageDAOTest {

    private final String filePath = "/home/breezzo/img2.jpg";
    private final String imageName = "nature";

    private ImageDAO imageDAO = new ImageDAO();

    @Test
    public void createAndGetImage() {
        try {
            final ImageModel image = imageModel(imageName, imageContent(filePath));

            imageDAO.createImage(image);

            final ImageModel createdImage = imageDAO.getImage(imageName);

            assertTrue(equals(image, createdImage));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @After
    public void tearDown() {
        DatabaseFactory.instance().destroy();
    }

    private ImageModel imageModel(String name, byte[] content) {
        final ImageModel model = new ImageModel();

        model.setName(name);
        model.setContent(content);

        return model;
    }

    private byte[] imageContent(String filePath) throws IOException {
        final InputStream in = new FileInputStream(filePath);

        byte[] data = ByteStreams.toByteArray(in);

        in.close();

        return data;
    }

    private boolean equals(ImageModel standart, ImageModel that) {
        return Objects.equal(standart.getName(), that.getName()) &&
                Arrays.equals(standart.getContent(), that.getContent());
    }
}
