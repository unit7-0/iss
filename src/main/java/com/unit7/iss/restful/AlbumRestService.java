package com.unit7.iss.restful;

import javax.inject.Inject;
import com.unit7.iss.model.entity.Album;
import com.unit7.iss.model.entity.User;
import com.unit7.iss.service.AlbumService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by breezzo on 20.08.15.
 */
@Path("/image/album")
@Produces(MediaType.APPLICATION_JSON)
public class AlbumRestService {
    private static final Logger logger = LoggerFactory.getLogger(AlbumRestService.class);

    @Inject
    private AlbumService albumService;

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAlbum(Album album) {
        logger.debug("try to create album: {}", album);

        albumService.create(album);

        return Response.ok().build();
    }

    @GET
    @Path("/getByUser/{userId}")
    public Response getByUser(@PathParam("userId") String userId) {

        logger.debug("get albums for user with id: {}", userId);

        if (userId == null) {
            return Response.serverError().entity("Необходимо указать пользователя").build();
        }

        final User user = new User();
        user.setId(new ObjectId(userId));

        final Album album = new Album();
        album.setSubAlbums(albumService.getByUser(user));
        album.setUser(user);

        return Response.ok(album).build();
    }

    @GET
    @Path("/get/{albumId}")
    public Response get(@PathParam("albumId") String albumId) {

        logger.debug("get album with id: {}", albumId);

        if (albumId == null) {
            return Response.serverError().entity("Необходимо выбрать альбом").build();
        }

        return Response.ok(albumService.get(new ObjectId(albumId))).build();
    }
}
