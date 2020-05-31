package ua.kpi.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.model.dao.ActivityDao;
import ua.kpi.model.dao.Mapper.ActivityMapper;
import ua.kpi.model.entities.Activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.kpi.model.dao.impl.sqlqueries.SqlQueries.*;

public class JdbcActivityDao implements ActivityDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcActivityDao.class);

    private static final String MAX_ID = "max(id)";

    Connection connection;

    public JdbcActivityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Activity activity) {
        try (PreparedStatement ps1 = connection.prepareStatement(CREATE_ACTIVITY)) {
            ps1.setString(1, activity.getName());
            ps1.setString(2, activity.getDescription());
            ps1.setInt(3, activity.getAdministratorId());
            ps1.setInt(4, activity.getUserId());
            ps1.executeUpdate();

            return true;
        } catch (SQLException exception) {
            LOGGER.error("SQL error creating new activity {} ", exception.getMessage());
        }
        return false;
    }

//    @Override
//    public boolean assignInspector(Declaration declaration) {
//        try(PreparedStatement ps1 = connection.prepareStatement(SELECT_LEAST_BUSY_INSPECTOR)) {
//
//            ResultSet rs = ps1.executeQuery();
//            if (rs.next()) {
//                String inspectorLogin = rs.getString(LOGIN);
//                declaration.setInspectorLogin(inspectorLogin);
//
//                PreparedStatement ps2 = connection.prepareStatement(INCREMENT_REPORTS_ASSIGNED); //TODO Combine in one request?
//                ps2.setString(1, inspectorLogin);
//                ps2.executeUpdate(); //TODO Any additional checks needed?
//                ps2.close();
//            }
//        } catch (SQLException exception) {
//
//        LOGGER.error("SQL error assigning inspector {} ", exception.getMessage());
//    }
//        return false;
//    }
//
    @Override
    public List<Activity> findByUserId(int userId) {
        List<Activity> activities = new ArrayList<>();
        ActivityMapper activityMapper = new ActivityMapper();

            try (PreparedStatement ps = connection.prepareStatement(FIND_ACTIVITY_BY_USER_ID)) {
                ps.setInt(1,userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Activity activity = activityMapper.extractFromResultSet(rs);
                    activities.add(activity);
                }

            } catch (SQLException exception) {
                LOGGER.error("SQL receiving all declaration by client login {} ", exception.getMessage());
            }

        return activities;
    }

    @Override
    public boolean setTimeTracked(int activityId, int timeTracked) {
        try (PreparedStatement ps = connection.prepareStatement(SET_TIME_TRACKED)) {
            ps.setInt(2, activityId);
            ps.setInt(1, timeTracked);
            ps.executeUpdate();
            return true;
        } catch (SQLException exception) {
            LOGGER.error("Error setting time tracked: {} ", exception.getMessage());
            exception.printStackTrace();
        }
        return false;
    }
//
//    @Override
//    public List<Declaration> findAllByInspectorLogin(String login) {
//        List<Declaration> list = new ArrayList<>();
//        DeclarationMapper declarationMapper = new DeclarationMapper();
//
//        try (PreparedStatement ps = connection.prepareStatement(SELECT_DECLARATIONS_BY_INSPECTOR_LOGIN)) {
//
//            ps.setString(1,login);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Declaration declaration = declarationMapper.extractFromResultSet(rs);
//                list.add(declaration);
//            }
//
//        } catch (SQLException exception) {
//            LOGGER.error("SQL receiving all declaration by inspector login {} ", exception.getMessage());
//        }
//
//        return list;
//    }

    @Override
    public boolean setStatus(int activityId, String newStatus) {
        try (PreparedStatement statement = connection.prepareStatement(SET_STATUS)) {
            statement.setInt(2, activityId);
            statement.setString(1, newStatus);
            statement.executeUpdate();
            return true;
        } catch (SQLException exception) {
            LOGGER.error("Error updating activity status: {} ", exception.getMessage());
            exception.printStackTrace();
        }
        return false;
    }

//    @Override
//    public boolean decline(int id, String declineMessage) {
//        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.DECLINE_DECLARATION)) {
//            statement.setInt(2, id);
//            statement.setString(1, declineMessage);
//
//            statement.executeUpdate();
//        } catch (SQLException exception) {
//            LOGGER.error("Error declining declaration: {} ", exception.getMessage());
//            exception.printStackTrace();
//        }
//
//        return true;
//    }
//
//    @Override
//    public boolean correct(Declaration correctedDeclaration) {
//        try (PreparedStatement ps = connection.prepareStatement(CORRECT_DECLARATION)) {
//
//            ps.setString(1, correctedDeclaration.getDeclarationYear().getYear());
//            ps.setString(2, correctedDeclaration.getTaxCategory().toString());
//            ps.setLong(3, correctedDeclaration.getIncome());
//            ps.setLong(4, correctedDeclaration.getTaxSumDeclared());
//            ps.setString(5, correctedDeclaration.getStatus().toString());
//            ps.setInt(6, correctedDeclaration.getId());
//            ps.executeUpdate();
//
//        } catch (SQLException exception) {
//            LOGGER.error("SQL error correcting declaration {} ", exception.getMessage());
//            exception.printStackTrace();
//        }
//
//        return true;
//    }

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
