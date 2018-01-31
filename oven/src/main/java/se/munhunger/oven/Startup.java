package se.munhunger.oven;

import org.glassfish.jersey.server.ResourceConfig;
import se.munhunger.oven.injection.Binder;

public class Startup extends ResourceConfig
{
    public Startup() {
        register(new Binder());
        packages("true", "oven");
    }
}
