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
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
                .build();
        Configuration config = new Configuration();
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String pass = System.getenv("DB_PASS");
        if(url != null) {
            logger.info(() -> "DB_URL Environment was set, setting hibernate.connection.url to " + url);
            config.setProperty("hibernate.connection.url", "jdbc:" + url);
        }
        if(user != null) {
            logger.info(() -> "DB_USER Environment was set, setting hibernate.connection.username to " + user);
            config.setProperty("hibernate.connection.username", user);
        }
        if(pass != null) {
            logger.info(() -> "DB_PASS Environment was set, setting hibernate.connection.password");
            config.setProperty("hibernate.connection.password", pass);
        }
        sessionFactory = config.buildSessionFactory(registry);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> StandardServiceRegistryBuilder.destroy(registry)));
    }

    static {
        init();
    }
}
