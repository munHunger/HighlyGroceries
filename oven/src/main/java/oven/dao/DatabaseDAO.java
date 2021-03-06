package oven.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DatabaseDAO {
    protected static SessionFactory sessionFactory;

    public static void resetSessions()
    {
        sessionFactory = null;
    }

    private static void init()
    {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
                .build();
        MetadataSources mds = new MetadataSources(registry);
        Metadata md = mds.buildMetadata();
        sessionFactory = md.buildSessionFactory();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> StandardServiceRegistryBuilder.destroy(registry)));
    }

    static {
        init();
    }
}
