package com.unit7.iss.model.behavior;

import com.unit7.iss.model.ImageModel;

/**
 * Created by breezzo on 05.08.15.
 */
public interface FormatAdapter {
    void adaptSource(String source, ImageModel target);

    String adaptTarget(ImageModel target);
}
