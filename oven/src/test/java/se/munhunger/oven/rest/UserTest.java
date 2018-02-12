package se.munhunger.oven.rest;

import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import se.munhunger.oven.model.persistance.User;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(OleasterRunner.class)
public class UserTest
{
    private String baseURL = (env.get("OVEN_URL") != null ? env.get("OVEN_URL") : "http://localhost:8080") + "/oven/api/user";
    private Client client;

    {
        before(() -> {
            client = ClientBuilder.newClient();
        });
        describe("Creating a user", () -> {
            it("returns 204 upon creation", () -> {
                Assert.assertEquals(204,
                                    client.target(baseURL)
                                          .request()
                                          .header("email", "mail@mail.mail")
                                          .post(Entity.json(null))
                                          .getStatus());
            });
            describe("Has a user created", () -> {
                beforeEach(() -> {
                    client.target(baseURL)
                          .request()
                          .header("email", "mail@mail.mail")
                          .post(Entity.json(null));
                });
                it("Can fetch the user", () -> {
                    User user = client.target(baseURL).request().header("email", "mail@mail.mail").get(User.class);
                    Assert.assertEquals("mail@mail.mail", user.email);
                });
            });
            afterEach(() -> {
                client.target(baseURL).request().header("email", "mail@mail.mail").delete();
            });
        });
    }
}
