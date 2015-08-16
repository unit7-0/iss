package com.unit7.iss.service.impl;

import com.google.common.base.Optional;
import com.google.inject.Singleton;
import com.unit7.iss.dao.ImageDAO;
import com.unit7.iss.model.entity.ImageEntity;
import com.unit7.iss.service.ImageService;

/**
 * Created by breezzo on 02.08.15.
 */
@Singleton
public class ImageServiceImpl implements ImageService {
    final ImageDAO imageDAO = new ImageDAO();

    @Override
    public Optional<ImageEntity> getImage(String name) {
        return Optional.fromNullable(imageDAO.getImage(name));
    }

    @Override
    public void createImage(ImageEntity image) {
        imageDAO.create(image);
    }
}
