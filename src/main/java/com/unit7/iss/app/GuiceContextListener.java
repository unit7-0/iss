package com.unit7.iss.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.unit7.iss.restful.ImageResponder;
import com.unit7.iss.service.ImageService;
import com.unit7.iss.service.impl.ImageServiceImpl;

/**
 * Created by breezzo on 02.08.15.
 */
public class GuiceContextListener extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                bind(ImageResponder.class);
                bind(ImageService.class).to(ImageServiceImpl.class);

                serve("/rest/*").with(GuiceContainer.class);
            }
        });
    }
}
