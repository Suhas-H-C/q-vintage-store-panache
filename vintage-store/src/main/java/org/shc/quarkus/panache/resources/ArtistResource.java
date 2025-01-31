package org.shc.quarkus.panache.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.shc.quarkus.jdbc.pojo.Artist;
import org.shc.quarkus.panache.repository.ArtistRepository;

import java.util.List;

@Path("/v1/artist")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArtistResource {

    @Inject
    ArtistRepository repository;

    @GET
    public List<Artist> allArtists() {
        return repository.listAll();
    }

    @GET
    @Path("/{id}")
    public Artist getArtistById(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @POST
    @Transactional
    public Response save(Artist artist) {
        return Response
                .status(201)
                .entity(artist)
                .build();
    }
}