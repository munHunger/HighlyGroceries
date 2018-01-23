package oven.rest;

import io.swagger.annotations.Api;
import oven.dao.GrocerieDAO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Api(value = "initial_test")
@Path("/groceries")
public class Groceries {

    @Inject
    GrocerieDAO grocerieDAO;

    @GET
    @Path("/getAll")
    public Response print() throws Exception {
        return Response.ok(GrocerieDAO.getObjects("from Grocerie")).build();
    }

}
