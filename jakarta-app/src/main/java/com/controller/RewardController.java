package com.controller;

import com.facade.RewardFacade;
import com.model.Reward;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/v1/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class RewardController {

    @EJB
    private RewardFacade rewardFacade;

    @GET
    @Path("/rewards")
    public Response getAll() {
        return Response.ok(rewardFacade.getAll()).build();
    }

    @GET
    @Path("/{id}/rewards")
    public Response getAllActiveByCustomerId(@PathParam("id") String id) {
        return Response.ok(rewardFacade.getAllActiveByCustomerId(id)).build();
    }

    @POST
    @Path("/{id}/rewards")
    public Response create(@PathParam("id") String id, Reward reward) {
        Reward created = rewardFacade.create(reward, id);
        return Response.created(URI.create("/v1/customers/rewards/" + created.getId())).build();
    }

    @DELETE
    @Path("/rewards/{id}")
    public Response deleteById(@PathParam("id") String id) {
        rewardFacade.delete(id);
        return Response.noContent().build();
    }
}
