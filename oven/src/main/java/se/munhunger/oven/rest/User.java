package se.munhunger.oven.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import se.munhunger.oven.dao.GrocerieDAO;
import se.munhunger.oven.exceptions.NotInDatabaseException;
import se.munhunger.oven.service.UserService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Api(value = "User", description = "User related queries")
@Path("/user")
public class User
{
    private static Logger logger = Logger.getLogger(User.class.getName());

    @Inject
    private UserService userService;

    //TODO: Get email from facebook
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates a new user in the database")
    @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "User created")
    public Response createUser(@HeaderParam("email") String email) {
        logger.info(() -> "Creating user " + email);
        userService.createUser(email);
        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@HeaderParam("email") String email) {
        try
        {
            logger.info(() -> "Fetching user " + email);
            return Response.ok(userService.getUser(email)).build();
        } catch (NotInDatabaseException e)
        {
            logger.warning(() -> "User " + email + " was not found in the database");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    public Response deleteUser(@HeaderParam("email") String email) {
        try
        {
            logger.info("Deleting user " + email);
            userService.deleteUser(email);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotInDatabaseException e) {
            logger.warning(() -> "User " + email + " was not found in the database");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
