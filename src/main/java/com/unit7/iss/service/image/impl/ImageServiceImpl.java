package com.unit7.iss.service.image.impl;

import com.google.common.base.Optional;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.unit7.iss.db.dao.ImageDAO;
import com.unit7.iss.exception.ApplicationException;
import com.unit7.iss.model.entity.Image;
import com.unit7.iss.service.ImageService;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by breezzo on 02.08.15.
 */
@Singleton
public class ImageServiceImpl implements ImageService {
    @Inject
    private ImageDAO imageDAO;

    @Override
    public Optional<Image> getFirstImageByName(String name) {
        return Optional.fromNullable(imageDAO.getFirstImageByName(name));
    }

    @Override
    public void createImage(Image image) {
        imageDAO.create(image);
    }

    @Override
    public byte[] generatePreview(Image image, int width, int height) {

        try {
            final ByteArrayInputStream imageByteStream = new ByteArrayInputStream(image.getContent());
            final ImageInputStream imageInputStream = ImageIO.createImageInputStream(imageByteStream);

            final BufferedImage bufferedImage = ImageIO.read(imageInputStream);

            final int newWidth = Math.min(bufferedImage.getWidth(), width);
            final int newHeight = Math.min(bufferedImage.getHeight(), height);

            final BufferedImage resized = new BufferedImage(newWidth, newHeight, bufferedImage.getType());
            final Graphics2D canvas = resized.createGraphics();

            canvas.drawImage(bufferedImage, 0, 0, newWidth, newHeight, null);
            canvas.dispose();

            final ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();

            final boolean writed = ImageIO.write(resized, "jpg", byteOutStream);

            if (writed) {
                return byteOutStream.toByteArray();
            } else {
                throw new ApplicationException("Cannot convert image");
            }

        } catch (IOException e) {
            throw new ApplicationException(e.getMessage(), e);
        } finally {

        }
    }
}
