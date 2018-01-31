package se.munhunger.oven.dao;

import org.hibernate.Session;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import se.munhunger.oven.model.persistance.Grocerie;

import java.util.List;
import java.util.Optional;

public class GrocerieDAO extends DatabaseDAO {

    private static void init()
    {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures settings from hibernate.cfg.xml
                .build();
        MetadataSources mds = new MetadataSources(registry);
        Metadata md = mds.buildMetadata();
        sessionFactory = md.buildSessionFactory();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            StandardServiceRegistryBuilder.destroy(registry);
        }));
    }

    public void insert(Grocerie item)
    {
        try (Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            session.saveOrUpdate(item);
            session.getTransaction().commit();
        }
    }

    public static List getObjects(String hibernateQuery) throws Exception
    {
        if (sessionFactory == null)
            init();
        try (Session session = sessionFactory.openSession())
        {
            Query query = session.createQuery(hibernateQuery);
            return query.getResultList();
        }
    }

    public Optional<Grocerie> getByName(String name)
    {
        try (Session session = sessionFactory.openSession())
        {
            Grocerie grocerie = session.get(Grocerie.class, name);
            return grocerie != null ? Optional.of(grocerie) : Optional.empty();
        }
    }


}
