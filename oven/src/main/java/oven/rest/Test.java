package oven.rest;

import io.swagger.annotations.Api;
import oven.dao.GrocerieDAO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Api(value = "initial_test")
@Path("/ping")
public class Test {

    @Inject
    GrocerieDAO grocerieDAO;

    @GET
    @Path("/print")
    public Response print() {
        return Response.ok(grocerieDAO.getByTitle("random").get()).build();
    }

}
