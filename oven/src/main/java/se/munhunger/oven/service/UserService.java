package se.munhunger.oven.service;

import se.munhunger.oven.dao.UserDAO;
import se.munhunger.oven.exceptions.NotInDatabaseException;
import se.munhunger.oven.model.persistance.User;

import javax.inject.Inject;

public class UserService
{
    @Inject
    private UserDAO userDAO;

    public void createUser(String email) {
        userDAO.createUser(email);
    }

    public User getUser(String email) throws NotInDatabaseException
    {
        return userDAO.getUser(email).orElseThrow(NotInDatabaseException::new);
    }
}
