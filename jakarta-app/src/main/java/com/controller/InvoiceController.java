package com.controller;

import com.facade.InvoiceFacade;
import com.model.Invoice;

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
public class InvoiceController {

    @EJB
    private InvoiceFacade invoiceFacade;

    @GET
    @Path("/invoices")
    public Response getAll() {
        return Response.ok(invoiceFacade.getAll()).build();
    }

    @POST
    @Path("/{id}/invoices")
    public Response create(Invoice invoice, @PathParam("id") String id, @QueryParam("reward") String reward) {
        Invoice created = invoiceFacade.create(invoice, id, reward);
        return Response.created(URI.create("/v1/customers/invoices/" + created.getId())).build();
    }
}
