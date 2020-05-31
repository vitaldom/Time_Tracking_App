package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.JspPath;
import ua.kpi.model.entities.Activity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static ua.kpi.controller.TextConstants.*;

/**
 * Renders page for client user to view a single submitted declaration.
 */
public class ViewUserActivityCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ViewUserActivityCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter(ACTIVITY_INDEX_IN_LIST) == null) {        //on-page language change case TODO check, probably delete
            forward(request, response, JspPath.USER_ACTIVITY_PAGE);
        }

        int index = Integer.parseInt(request.getParameter(ACTIVITY_INDEX_IN_LIST));
        ArrayList<Activity> activityList = (ArrayList<Activity>) request.getSession().getAttribute(ACTIVITY_LIST);
        Activity activityToUpdate = activityList.get(index);

        System.out.println("Activity to update in VewUserActivity Command: " + activityToUpdate); //TODO delete
        System.out.println("Time tracked: " + activityToUpdate.getTimeTracked());

        request.getSession().setAttribute(ACTIVITY_TO_UPDATE, activityToUpdate);

        forward(request, response, JspPath.USER_ACTIVITY_PAGE);
    }
}
