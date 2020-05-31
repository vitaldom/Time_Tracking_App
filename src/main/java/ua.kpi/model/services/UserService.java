package ua.kpi.model.services;

import ua.kpi.model.dao.DaoFactory;
import ua.kpi.model.entities.AbstractAppUser;
import ua.kpi.model.dao.UserDao;
import ua.kpi.model.entities.User;

import java.util.List;

public class UserService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public boolean create(AbstractAppUser user) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.create(user);
        }
    }

    public AbstractAppUser find(String login, String password) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.find(login, password);
        }
    }

    public List<AbstractAppUser> findAll() {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }

    public User findClientByLogin(String login) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findClientByLogin(login);
        }
    }

    public AbstractAppUser findById(int id) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findById(id);
        }
    }
}