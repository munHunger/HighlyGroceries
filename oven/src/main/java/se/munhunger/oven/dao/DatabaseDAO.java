package se.munhunger.oven.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Map;
import java.util.logging.Logger;

public class DatabaseDAO {
    private static Logger logger = Logger.getLogger(DatabaseDAO.class.getName());
    protected static SessionFactory sessionFactory;

    public static void resetSessions()
    {
        sessionFactory = null;
    }

    private static void init()
    {
        logger.info("Initializing database");
        Map<String, String> env = System.getenv();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
                .build();
        Configuration config = new Configuration();
        if(env.containsKey("DB_URL")) {
            logger.info(() -> "DB_URL Environment was set, setting hibernate.connection.url to " + env.get("DB_URL"));
            config.setProperty("hibernate.connection.url", "jdbc:" + env.get("DB_URL"));
        }
        if(env.containsKey("DB_USER")) {
            logger.info(() -> "DB_USER Environment was set, setting hibernate.connection.username to " + env.get("DB_USER"));
            config.setProperty("hibernate.connection.username", env.get("DB_USER"));
        }
        if(env.containsKey("DB_PASS")) {
            logger.info(() -> "DB_PASS Environment was set, setting hibernate.connection.password");
            config.setProperty("hibernate.connection.password", env.get("DB_PASS"));
        }
        sessionFactory = config.buildSessionFactory(registry);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> StandardServiceRegistryBuilder.destroy(registry)));
    }

    static {
        init();
    }
}
