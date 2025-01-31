package org.shc.quarkus.panache.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.shc.quarkus.panache.entity.Book;

import java.util.List;

@Path("/v1/book")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @GET
    public List<Book> allBooks() {
        return Book.listAll();
    }

    @GET
    @Path("/{id}")
    public Book getBookById(@PathParam("id") Long id) {
        return Book.findById(id);
    }
}