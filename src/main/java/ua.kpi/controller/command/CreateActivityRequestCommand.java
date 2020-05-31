package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.controller.path.JspPath;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.*;
import ua.kpi.model.services.ActivityRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.kpi.controller.TextConstants.USER;

/**
 * Invoked when client user chooses to send request for inspector change. This option becomes available
 * if the declaration had been previously declined by inspector. Declaration stays available for the client for view
 * (but not correction). New inspector change request is stored to database.
 */
public class CreateActivityRequestCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CreateActivityRequestCommand.class);
    ActivityRequestService activityRequestService = new ActivityRequestService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute(USER);
        ActivityRequest activityRequest = new ActivityRequest(user.getId());

        boolean tmp = activityRequestService.create(activityRequest); //TODO consider use of tmp

        if (tmp) {
             InputChecker.setServiceMessage(request, "activity.request.created.message");
             redirect(request, response, ServletPath.MENU);
             return;
        }

        forward(request, response, JspPath.USER_MENU_PAGE); // TODO test if this forward ever happens. Add error message?
    }
}
