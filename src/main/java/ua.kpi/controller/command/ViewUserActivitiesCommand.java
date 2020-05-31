package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.JspPath;
import ua.kpi.model.entities.Activity;
import ua.kpi.model.entities.User;
import ua.kpi.model.services.ActivityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ua.kpi.controller.TextConstants.ACTIVITY_LIST;
import static ua.kpi.controller.TextConstants.USER;

/**
 * Renders page for client user to view a list of all submitted declarations.
 */
public class ViewUserActivitiesCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ViewUserActivitiesCommand.class);
    ActivityService activityService = new ActivityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute(USER);
        List<Activity> activityList = activityService.findByUserId(user.getId());

        System.out.println("List of activities assigned to a user (in ViewUserActivitiesCommand: ");
        for (Activity a : activityList) {
            System.out.println(a.getName() + ", ID: " + a.getId());
        }

        request.getSession().setAttribute(ACTIVITY_LIST, activityList);
        forward(request, response, JspPath.USER_ACTIVITY_LIST_PAGE);
    }
}
