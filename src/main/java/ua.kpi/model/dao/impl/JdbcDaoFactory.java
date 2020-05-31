package ua.kpi.model.dao.impl;

import ua.kpi.model.dao.*;
import ua.kpi.model.entities.Activity;

import java.sql.Connection;

public class JdbcDaoFactory extends DaoFactory {

    private ConnectionPool connectionPool = new ConnectionPool(); //TODO rework into singleton

    @Override
    public UserDao createUserDao() {
        return new JdbcAbstractUserDao(getConnection());
    }

    @Override
    public DeclarationDao createDeclarationDao() {
        return new JdbcDeclarationDao(getConnection());
    }

    @Override
    public ActivityDao createActivityDao() {
        return new JdbcActivityDao(getConnection());
    }

    @Override
    public ActivityRequestDao createActivityRequestDao() {
        return new JdbcActivityRequestDao(getConnection());
    }

    private Connection getConnection() {
        return connectionPool.getConnection();
    }
}