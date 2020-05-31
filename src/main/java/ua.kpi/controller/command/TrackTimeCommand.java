package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.controller.path.JspPath;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.entities.User;
import ua.kpi.model.services.ActivityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.kpi.controller.TextConstants.*;

/**
 * Encapsulates logic for correction declaration by a client user, if the declaration was
 * previously declined by inspector. Corrected declaration is stored to database under same id.
 */
public class TrackTimeCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(TrackTimeCommand.class);
    ActivityService activityService = new ActivityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String activityId = request.getParameter("activityId");
        String timeToTrack = request.getParameter(TIME_TO_TRACK);
        String timeTracked = request.getParameter(TIME_TRACKED);

//        if (!allNotNull(firstName, secondName, declarationYear, taxCategory, income, taxSumDeclared)) {
//            forward(request, response, JspPath.CORRECT_DECLARATION_PAGE);
//            return;
//        }         //TODO validate usage

        if (!InputChecker.checkTimeToTrackParameter(timeToTrack)) {         //TODO add error message
            response.sendRedirect(ServletPath.VIEW_ACTIVITY_USER);
            return;
        }

        int newTimeTracked = Integer.parseInt(timeTracked) + Integer.parseInt(timeToTrack);

        boolean tmp = activityService.setTimeTracked(Integer.parseInt(activityId), newTimeTracked); //TODO consider use of tmp

//        LOGGER.debug("Corrected declaration written to database, value of tmp: {} ", tmp);
//                                                                        //TODO consider check for actual insert into DB

        if(tmp) {
             InputChecker.setServiceMessage(request, "track.time.success.message");
             redirect(request, response, ServletPath.MENU);
             return;
        }

        forward(request, response, JspPath.USER_MENU_PAGE); // TODO test if this forward ever happens
    }
}
