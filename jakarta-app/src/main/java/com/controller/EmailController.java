package com.controller;

import com.facade.EmailFacade;
import com.model.Email;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/notifications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class EmailController {

    @Inject
    private EmailFacade emailFacade;

    @GET
    public Response getAll() {
        return Response.ok(emailFacade.getAll()).build();
    }

    @POST
    public Response create(Email email) {
        emailFacade.create(email);
        return Response.accepted().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") String id) {
        emailFacade.delete(id);
        return Response.noContent().build();
    }
}
