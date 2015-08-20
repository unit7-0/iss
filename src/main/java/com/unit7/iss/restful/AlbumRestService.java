package com.unit7.iss.restful;

import com.google.inject.Inject;
import com.unit7.iss.model.entity.Album;
import com.unit7.iss.service.AlbumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by breezzo on 20.08.15.
 */
@Path("/image/album")
public class AlbumRestService {
    private static final Logger logger = LoggerFactory.getLogger(AlbumRestService.class);

    @Inject
    private AlbumService albumService;

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAlbum(Album album) {
        logger.debug("try to create album: {}", album);

        return Response.ok().build();
    }

    @GET
    @Path("/getByUser/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUser(@PathParam("userId") String userId) {
        return Response.ok().build();
    }
}
