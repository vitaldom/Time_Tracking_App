package ua.kpi.model.dao;

import ua.kpi.model.entities.AbstractAppUser;
import ua.kpi.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<AbstractAppUser> {

        AbstractAppUser find(String login, String password);

        AbstractAppUser findById(int id);

        List<AbstractAppUser> findAll();

        User findClientByLogin(String login);
}
