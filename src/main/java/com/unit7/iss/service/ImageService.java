package com.unit7.iss.service;

import com.google.common.base.Optional;
import com.unit7.iss.model.entity.ImageEntity;

;

/**
 * Created by breezzo on 02.08.15.
 */
public interface ImageService {
    Optional<ImageEntity> getImage(String name);

    void createImage(ImageEntity image);
}
