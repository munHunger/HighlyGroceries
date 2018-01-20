package oven.dao;

import org.hibernate.Session;
import oven.model.persistance.Grocerie;

import java.util.Optional;

public class GrocerieDAO extends DatabaseDAO {

    public void insert(Grocerie item)
    {
        try (Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            session.saveOrUpdate(item);
            session.getTransaction().commit();
        }
    }

    public Optional<Grocerie> getByTitle(String title)
    {
        try (Session session = sessionFactory.openSession())
        {
            Grocerie grocerie = session.get(Grocerie.class, title);
            return grocerie != null ? Optional.of(grocerie) : Optional.empty();
        }
    }


}
