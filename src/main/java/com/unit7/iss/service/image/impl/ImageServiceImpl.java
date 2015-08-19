package com.unit7.iss.service.image.impl;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.unit7.iss.db.dao.ImageDAO;
import com.unit7.iss.model.entity.Image;
import com.unit7.iss.service.ImageService;

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
}
