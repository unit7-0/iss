package com.unit7.iss.model;

import com.google.common.base.Objects;
import org.bson.types.Binary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by breezzo on 03.08.15.
 */
public class ImageModel implements BasicModel {

    public ImageModel() {
    }

    public ImageModel(Map<String, Object> map) {
        fromMap(map);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }


    private void fromMap(Map<String, Object> map) {
        this.name = (String) map.get("name");
        this.content = ((Binary) map.get("content")).getData();
    }

    public Map<String, Object> toMap() {
        final Map<String, Object> map = new HashMap<String, Object>();

        map.put("name", name);
        map.put("content", content);

        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageModel)) return false;
        ImageModel that = (ImageModel) o;
        return Objects.equal(name, that.name) &&
                Arrays.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, content);
    }

    private String name;
    private byte[] content;
}
