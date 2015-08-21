package com.unit7.iss.service;

import com.google.common.base.Optional;
import com.unit7.iss.model.entity.Image;

;

/**
 * Created by breezzo on 02.08.15.
 */
public interface ImageService {
    @Deprecated
    Optional<Image> getFirstImageByName(String name);

    void createImage(Image image);

    // TODO перенести в нормальный инструмент, возвращать значение другого типа?
    byte[] generatePreview(Image image, int width, int height);
}
