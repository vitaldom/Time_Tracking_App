package ua.kpi.model.dao.Mapper;

import ua.kpi.model.dao.impl.columns.UserSqlColumns;
import ua.kpi.model.entities.AbstractAppUser;
import ua.kpi.model.entities.User;
import ua.kpi.model.entities.Administrator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AbstractUserMapper implements Mapper<AbstractAppUser> {

    @Override
    public AbstractAppUser extractFromResultSet(ResultSet rs) throws SQLException {
        String role = rs.getString(UserSqlColumns.ROLE);

        if (role == null) {
            return null;
        }

        if (role.equals(AbstractAppUser.Role.ADMINISTRATOR.name())) {
            return new Administrator.Builder()
                    .id(rs.getInt(UserSqlColumns.ID))
                    .firstName(rs.getString(UserSqlColumns.FIRST_NAME))
                    .secondName(rs.getString(UserSqlColumns.SECOND_NAME))               //TODO find way to avoid duplication
                    .login(rs.getString(UserSqlColumns.LOGIN))                          //TODO verify necessity of login
                    .role(rs.getString(UserSqlColumns.ROLE))
                    .build();
        } else {
            return new User.Builder()
                    .id(rs.getInt(UserSqlColumns.ID))
                    .firstName(rs.getString(UserSqlColumns.FIRST_NAME))
                    .secondName(rs.getString(UserSqlColumns.SECOND_NAME))
                    .login(rs.getString(UserSqlColumns.LOGIN))
                    .role(rs.getString(UserSqlColumns.ROLE))
                    .build();
        }
    }
}
