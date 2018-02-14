package se.munhunger.oven.dao;


import org.hibernate.Session;
import se.munhunger.oven.model.persistance.User;

import java.util.Optional;
import java.util.logging.Logger;

public class UserDAO extends DatabaseDAO
{
    private static Logger logger = Logger.getLogger(UserDAO.class.getName());

    public void createUser(String email) {
        logger.info(() -> "Creating user with email:" + email);
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new User(email));
            session.getTransaction().commit();
        }
        logger.info(() -> "User " + email + " created");
    }

    public Optional<User> getUser(String email) {
        logger.info(() -> "Fetching user " + email);
        try(Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(User.class, email));
        }
    }

    public void deleteUser(User user) {
        logger.info(() -> "Deleting user with email: " + user.email);
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
        logger.info(() -> "User " + user.email + " was deleted");
    }
}
