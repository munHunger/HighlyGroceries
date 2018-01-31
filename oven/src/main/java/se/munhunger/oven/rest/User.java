package se.munhunger.oven.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import se.munhunger.oven.exceptions.NotInDatabaseException;
import se.munhunger.oven.service.UserService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "User", description = "User related queries")
@Path("/user")
public class User
{
    @Inject
    private UserService userService;

    //TODO: Get email from facebook
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates a new user in the database")
    @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "User created")
    public Response createUser(@HeaderParam("email") String email) {
        userService.createUser(email);
        return Response.noContent().build();
    }

    @GET
    public Response getUser(@HeaderParam("email") String email) {
        try
        {
            return Response.ok(userService.getUser(email)).build();
        } catch (NotInDatabaseException e)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
