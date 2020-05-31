package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.controller.path.JspPath;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.AbstractAppUser;
import ua.kpi.model.entities.Activity;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.services.ActivityService;
import ua.kpi.model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;
import static ua.kpi.controller.TextConstants.SUBMITTED;
import static ua.kpi.controller.TextConstants.YEAR_;

/**
 * Encapsulates logic for creation of a new declaration.
 */
public class CreateActivityCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CreateActivityCommand.class);

    ActivityService activityService = new ActivityService();
    UserService userService = new UserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("userList", userService.findAll());            //TODO check for better place of setting attribute

        String name = request.getParameter("activity-name");
        String description = request.getParameter("activity-description");
        String userId = request.getParameter("user-id");



        if (!allNotNull(name, description, userId)) { //TODO add error message. ID Check necessary?
            forward(request, response, JspPath.CREATE_ACTIVITY_PAGE);                            //TODO consider moving check to InputChecker
            return;
        }
//        || name.isEmpty() || description.isEmpty()

//        User user = (User) request.getSession().getAttribute(USER);
//
//        if (!InputChecker.checkActivityDataValidity()) {
//            response.sendRedirect(ServletPath.NEW_DECLARATION);
//            return;
//        }


        Activity activity = new Activity.Builder()
                .name(name)
                .description(description)
                .userId(Integer.parseInt(userId))           //TODO move parsing to other place
                .administratorId(18)                   //TODO add log ic for retrieving admin id
                .build();

        boolean tmp = activityService.create(activity); //TODO consider use of tmp

        LOGGER.debug("New declaration written to database, value of tmp: {} ", tmp);
                                                                        //TODO consider check for actual insert into DB

        if (tmp) {
             InputChecker.setServiceMessage(request, "new.activity.successful.submission");

             redirect(request, response, ServletPath.MENU);
             return;
        }

        forward(request, response, JspPath.USER_MENU_PAGE); // TODO test if this forward ever happens
    }
}
