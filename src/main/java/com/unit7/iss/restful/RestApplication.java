package com.unit7.iss.restful;

import com.google.common.collect.Sets;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * Created by breezzo on 20.08.15.
 */
@ApplicationPath("/iss/rest")
public class RestApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Sets.newHashSet(ImageResponder.class, AlbumRestService.class);
    }

    @Override
    public Set<Object> getSingletons() {

        final ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final JacksonJaxbJsonProvider jaxbProvider = new JacksonJaxbJsonProvider();
        jaxbProvider.setMapper(objectMapper);

        return Sets.newHashSet(jaxbProvider);
    }
}
