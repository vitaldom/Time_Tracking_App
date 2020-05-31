package ua.kpi.model.dao.Mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.model.dao.impl.columns.ActivitySqlColumns;
import ua.kpi.model.dao.impl.columns.DeclarationSqlColumns;
import ua.kpi.model.entities.Activity;
import ua.kpi.model.entities.Administrator;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.entities.User;
import ua.kpi.model.services.ActivityService;
import ua.kpi.model.services.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityMapper implements Mapper<Activity> {

    UserService userService = new UserService();
    private static final Logger LOGGER = LogManager.getLogger(ActivityMapper.class);

    @Override
    public Activity extractFromResultSet(ResultSet rs) throws SQLException {

        Activity activity = new Activity.Builder()
                .id(rs.getInt(ActivitySqlColumns.ID))
                .name(rs.getString(ActivitySqlColumns.NAME))
                .description(rs.getString(ActivitySqlColumns.DESCRIPTION))
                .timeTracked(rs.getInt(ActivitySqlColumns.TIME_TRACKED))
                .report(rs.getString(ActivitySqlColumns.REPORT))
                .status(Activity.Status.valueOf(rs.getString(ActivitySqlColumns.STATUS)))
                .administratorId(rs.getInt(ActivitySqlColumns.ADMIN_ID))
                .userId(rs.getInt(ActivitySqlColumns.USER_ID))
                .build();

        activity.setAdministrator((Administrator) userService.findById(activity.getAdministratorId()));
        activity.setUser((User) userService.findById(activity.getUserId()));

//        LOGGER.info("New activity object recreated from database via Mapper: {}", activity); //TODO delete

        return activity;
    }
}