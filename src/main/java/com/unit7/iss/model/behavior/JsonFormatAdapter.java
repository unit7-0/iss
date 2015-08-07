package com.unit7.iss.model.behavior;

import com.unit7.iss.model.ImageModel;

/**
 * Created by breezzo on 05.08.15.
 */
public class JsonFormatAdapter implements FormatAdapter {
    @Override
    public void adaptSource(String source, ImageModel target) {
    }

    @Override
    public String adaptTarget(ImageModel target) {
        return null;
    }
}
