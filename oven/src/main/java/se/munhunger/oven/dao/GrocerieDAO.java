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
import java.util.logging.Logger;

public class GrocerieDAO extends DatabaseDAO {

    private static Logger logger = Logger.getLogger(GrocerieDAO.class.getName());

    public void insert(Grocerie item)
    {
        logger.info(() -> "Inserting item: " + item.food_group + ":" + item.name);
        try (Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            session.saveOrUpdate(item);
            session.getTransaction().commit();
        }
    }

    public static List getObjects(String hibernateQuery) throws Exception
    {
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
