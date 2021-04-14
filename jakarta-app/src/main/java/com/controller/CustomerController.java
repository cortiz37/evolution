package com.controller;

import com.facade.CustomerFacade;
import com.model.Customer;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

@Path("/v1/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CustomerController {

    @EJB
    private CustomerFacade customerFacade;

    @GET
    public Response getAll() {
        return Response.ok(customerFacade.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        final Optional<Customer> customerById = customerFacade.getCustomerById(id);
        if (customerById.isPresent()) {
            return Response.ok(customerById.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") String id) {
        customerFacade.delete(id);
        return Response.noContent().build();
    }

    @POST
    public Response create(Customer customer) {
        Customer created = customerFacade.create(customer);
        return Response.created(URI.create("/v1/customers/" + created.getId())).build();
    }
}
