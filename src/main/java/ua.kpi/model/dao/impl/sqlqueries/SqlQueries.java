package ua.kpi.model.dao.impl.sqlqueries;

public interface SqlQueries {

    // "user" table
    String PRE_CHECK_CREATION_USER = "SELECT COUNT(1) AS count FROM user WHERE login = ?";
    String CREATE_USER = "INSERT INTO user (second_name, first_name, login, password, role) VALUES (?, ?, ?, ?, ?)";
    String FIND_USER = "SELECT id, second_name, first_name, login, password, role FROM user WHERE login = ?" +
            " AND password = ?";
    String FIND_CLIENT_BY_LOGIN = "SELECT second_name, first_name, login, password, role FROM user WHERE login = ?";
    String SELECT_LEAST_BUSY_INSPECTOR = "SELECT login FROM user WHERE reports_assigned = " +
            "(SELECT MIN(reports_assigned) from USER)";
    String INCREMENT_REPORTS_ASSIGNED = "UPDATE user SET reports_assigned = reports_assigned + 1 WHERE login = ?";
    String FIND_ALL_USERS = "SELECT * FROM user WHERE role = 'USER'";
    String FIND_APP_USER_BY_ID = "SELECT id, second_name, first_name, login, role FROM user WHERE id = ?";


    // "declarations" table
    String SELECT_LAST_DECLARATION_ID = "SELECT MAX(id) FROM declarations";
    String CREATE_DECLARATION = "INSERT INTO declarations(id, author_login, inspector_login, declaration_year, " +
            "tax_category, income, tax_sum_declared, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String SELECT_DECLARATIONS_BY_CLIENT_LOGIN = "SELECT * FROM declarations WHERE author_login = ?";
    String SELECT_DECLARATIONS_BY_INSPECTOR_LOGIN = "SELECT * FROM declarations WHERE inspector_login = ? AND status" +
            " = 'SUBMITTED'";
    String CHANGE_DECLARATION_STATUS = "UPDATE declarations SET status = ? WHERE id = ?";
    String DECLINE_DECLARATION = "UPDATE declarations SET status = 'UNDER_CORRECTION', correction_message = ? " +
            "WHERE id = ?";
    String CORRECT_DECLARATION = "UPDATE declarations SET declaration_year = ?, tax_category = ?, income = ?," +
            " tax_sum_declared = ?, status = ? WHERE  id = ?";
    String FIND_DECLARATION_BY_ID = "SELECT * FROM declarations WHERE id = ?";

    // "activities" table
    String CREATE_ACTIVITY = "INSERT INTO activity(name, description, admin_id, user_id) VALUES (?, ?, ?, ?)";
    String FIND_ACTIVITY_BY_USER_ID = "SELECT * FROM activity WHERE user_id = ?";
    String SET_TIME_TRACKED = "UPDATE activity SET time_tracked = ? WHERE id = ?";
    String SET_STATUS = "UPDATE activity SET status = ? WHERE id = ?";

    // "inspector_change_requests" table
    String SELECT_LAST_INSPECTOR_CHANGE_REQUEST_ID = "SELECT MAX(id) FROM inspector_change_requests";
    String CREATE_INSPECTOR_CHANGE_REQUEST = "INSERT INTO inspector_change_requests(id, declaration_id, author_login," +
            " inspector_login, request_reason) VALUES (?, ?, ?, ?, ?)";

    // "activity_request" table
    String CREATE_ACTIVITY_REQUEST = "INSERT INTO activity_request(user_id) VALUES (?)";
}