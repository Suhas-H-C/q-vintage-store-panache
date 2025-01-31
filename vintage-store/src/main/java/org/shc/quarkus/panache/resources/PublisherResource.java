package org.shc.quarkus.panache.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.shc.quarkus.panache.entity.Publisher;

import java.util.List;

@Path("/v1/publisher")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublisherResource {

    @GET
    public List<Publisher> allCustomer() {
        return Publisher.listAll();
    }

    @GET
    @Path("/{name}")
    public Publisher getPublisherById(@PathParam("name") String name) {
        return Publisher.findByName(name).orElse(null);
    }
}
