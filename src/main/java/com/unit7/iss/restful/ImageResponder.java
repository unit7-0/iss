package com.unit7.iss.restful;

import com.google.common.base.Optional;
import com.google.common.io.ByteStreams;
import com.google.inject.Inject;
import com.unit7.iss.model.entity.ImageModel;
import com.unit7.iss.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by breezzo on 01.08.15.
 */
@Path("/image")
public class ImageResponder {

    private static final Logger logger = LoggerFactory.getLogger(ImageResponder.class);

    @Inject
    private ImageService imageService;

    @GET
    @Path("/get/{image_name}")
    @Produces({ "image/jpeg", "text/plain" })
    public Response getImage(@PathParam("image_name") String imageName) {
        final String fileName = "/home/breezzo/" + imageName;

        logger.debug("try to get file {}", fileName);

        try {

            final Optional<ImageModel> image = imageService.getImage(imageName);

            if (!image.isPresent()) {
                logger.error("file not found");
                return Response.status(Response.Status.NOT_FOUND).entity("Image not found").build();
            }

            return Response.ok(image.get().getContent()).build();
        } catch (Exception e) {
            logger.error("Error when get file", e);
            return Response.serverError().entity(e.getMessage()).build();
        }

    }

    @GET
    @Path("/create/{image_name}")
    public Response createImage(@PathParam("image_name") String imageName) {
        logger.debug("try to create image: {}", imageName);

        try {
            final File file = new File("/home/breezzo/img2.jpg");
            final byte[] content = ByteStreams.toByteArray(new FileInputStream(file));

            final ImageModel image = new ImageModel();
            image.setName(imageName);
            image.setContent(content);

            imageService.createImage(image);

            return Response.ok().build();

        } catch (IOException e) {
            logger.error("Error when create image", e);
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
