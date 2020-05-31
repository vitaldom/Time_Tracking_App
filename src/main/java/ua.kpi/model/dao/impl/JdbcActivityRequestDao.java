package ua.kpi.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.model.dao.ActivityRequestDao;
import ua.kpi.model.dao.impl.sqlqueries.SqlQueries;
import ua.kpi.model.entities.ActivityRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcActivityRequestDao implements ActivityRequestDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcActivityRequestDao.class);

    Connection connection;

    public JdbcActivityRequestDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(ActivityRequest activityRequest) {
        try(PreparedStatement ps1 = connection.prepareStatement(SqlQueries.CREATE_ACTIVITY_REQUEST)) {
            ps1.setInt(1, activityRequest.getUserId());
            ps1.executeUpdate();
            return true;
        } catch (SQLException exception) {
            LOGGER.error("SQL error creating new activity request {} ", exception.getMessage());
            return false;
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
            throw new RuntimeException(exception); //TODO validate throwing runtime exception
        }
    }
}
