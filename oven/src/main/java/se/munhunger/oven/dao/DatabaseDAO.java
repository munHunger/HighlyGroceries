package se.munhunger.oven.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Map;

public class DatabaseDAO {
    protected static SessionFactory sessionFactory;

    public static void resetSessions()
    {
        sessionFactory = null;
    }

    private static void init()
    {
        Map<String, String> env = System.getenv();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
                .build();
        Configuration config = new Configuration();
        if(env.containsKey("DB_URL"))
            config.setProperty("hibernate.connection.url", "jdbc:" + env.get("DB_URL"));
        if(env.containsKey("DB_USER"))
            config.setProperty("hibernate.connection.username", env.get("DB_USER"));
        if(env.containsKey("DB_PASS"))
            config.setProperty("hibernate.connection.password", env.get("DB_PASS"));
        sessionFactory = config.buildSessionFactory(registry);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> StandardServiceRegistryBuilder.destroy(registry)));
    }

    static {
        init();
    }
}
