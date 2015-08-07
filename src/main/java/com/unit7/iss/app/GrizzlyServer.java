package com.unit7.iss.app;

import com.google.inject.servlet.GuiceFilter;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.unit7.iss.dao.DatabaseFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.grizzly.servlet.WebappContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ProcessingException;
import java.io.IOException;
import java.net.URI;

/**
 * Создание и настройка Grizzly сервера
 *
 * TODO доработать настройку путей
 *
 * Created by breezzo on 02.08.15.
 */
public class GrizzlyServer {

    private static final Logger logger = LoggerFactory.getLogger(GrizzlyServer.class);

    public static final String STATIC_ROOT =
            "/home/breezzo/sources/Idea/Java/image-storage-service/src/main/resources/web";

    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/iss";
    public static final String STATIC_URI = "/static";

    private URI baseUri = URI.create(BASE_URI);

    private HttpServer server;

    public void start() {
        server = HttpServer.createSimpleServer();

        configureServer();
        deployServer();

        try {
            server.start();
        } catch (IOException e) {
            throw new ProcessingException(e.getMessage(), e);
        }
    }

    public void stop() {
        DatabaseFactory.instance().closeConnections();
        server.shutdownNow();
    }

    private void configureServer() {
        final ServerConfiguration serverConfig = server.getServerConfiguration();

        serverConfig.addHttpHandler(new StaticHttpHandler(STATIC_ROOT), STATIC_URI);
    }

    private void deployServer() {
        final WebappContext context = createWebappContext();

        context.deploy(server);
    }

    private WebappContext createWebappContext() {
        String path = baseUri.getPath();
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }

        // создаем контекст приложения с contextPath
        final WebappContext webappContext = new WebappContext("Grizzly WebApp", path);

        configureWebappContext(webappContext);

        return webappContext;
    }

    private void configureWebappContext(WebappContext context) {
        // GuiceListener для конфигурирования DI в рест-сервисы
        context.addListener(GuiceContextListener.class);

        // Guice фильтр будет отрабатывать на все rest запросы и инжектить зависимости при необходимости
        context.addFilter("GuiceFilter", GuiceFilter.class)
                .addMappingForUrlPatterns(null, "/rest/*");


        // добавить сервлет, чтобы срабатывал фильтр Guice
        context.addServlet("GuiceContainer", GuiceContainer.class);
    }
}
