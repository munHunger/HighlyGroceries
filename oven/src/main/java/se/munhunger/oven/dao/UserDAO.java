package se.munhunger.oven.dao;


import org.hibernate.Session;
import se.munhunger.oven.model.persistance.User;

import java.util.Optional;

public class UserDAO extends DatabaseDAO
{
    public void createUser(String email) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new User(email));
            session.getTransaction().commit();
        }
    }

    public Optional<User> getUser(String email) {
        try(Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, email);
            if(user == null)
                return Optional.empty();
            return Optional.of(user);
        }
    }

    public void deleteUser(User user) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
    }
}
