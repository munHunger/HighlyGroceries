package oven;

import oven.injection.Binder;
import org.glassfish.jersey.server.ResourceConfig;

public class Startup extends ResourceConfig
{
    public Startup() {
        register(new Binder());
        packages("true", "oven");
    }
}
