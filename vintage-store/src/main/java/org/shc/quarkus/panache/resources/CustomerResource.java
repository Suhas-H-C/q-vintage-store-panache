package org.shc.quarkus.panache.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.shc.quarkus.jdbc.pojo.Artist;
import org.shc.quarkus.jpa.entity.Customer;
import org.shc.quarkus.panache.repository.ArtistRepository;
import org.shc.quarkus.panache.repository.CustomerRepository;

import java.util.List;

@Path("/v1/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerRepository repository;

    @GET
    public List<Customer> allCustomer() {
        return repository.listAll();
    }

    @GET
    @Path("/{id}")
    public Customer getCustomerById(@PathParam("id") Long id) {
        return repository.findById(id);
    }
}
