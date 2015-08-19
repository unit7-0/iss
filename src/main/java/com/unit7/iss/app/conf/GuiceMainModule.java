package com.unit7.iss.app.conf;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.unit7.iss.db.dao.AlbumDAO;
import com.unit7.iss.db.dao.ImageDAO;
import com.unit7.iss.db.dao.UserDAO;
import com.unit7.iss.restful.ImageResponder;
import com.unit7.iss.service.AlbumService;
import com.unit7.iss.service.ImageService;
import com.unit7.iss.service.image.impl.AlbumServiceImpl;
import com.unit7.iss.service.image.impl.ImageServiceImpl;
import com.unit7.iss.service.user.UserService;
import com.unit7.iss.service.user.impl.UserServiceImpl;

/**
 * Created by breezzo on 19.08.15.
 */
public class GuiceMainModule extends ServletModule {
    @Override
    protected void configureServlets() {
        bind(ImageResponder.class);
        bind(ImageService.class).to(ImageServiceImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
        bind(AlbumService.class).to(AlbumServiceImpl.class);
        bind(ImageDAO.class);
        bind(UserDAO.class);
        bind(AlbumDAO.class);

        serve("/rest/*").with(GuiceContainer.class);
    }
}
