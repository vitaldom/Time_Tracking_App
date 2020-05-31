package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.Activity;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.services.ActivityService;
import ua.kpi.model.services.DeclarationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.kpi.controller.TextConstants.DECLARATION_TO_PROCEED;
import static ua.kpi.controller.TextConstants.DECLINE_MESSAGE;

/**
 * Invoked when inspector chooses to decline a declaration, with obligatory reason message. Declaration becomes
 * available for a client for correction.
 */
public class RequestActivityDeletionCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(RequestActivityDeletionCommand.class);
    ActivityService activityService = new  ActivityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int activityId = Integer.parseInt(request.getParameter("activityId"));      //TODO test for exception, consider try-catch

        activityService.setStatus(activityId, Activity.Status.DELETION_REQUESTED.name());

//        Declaration declaration = (Declaration) request.getSession().getAttribute(DECLARATION_TO_PROCEED);
//        String declineMessage = request.getParameter(DECLINE_MESSAGE);
//
//        if (declineMessage == null) { //TODO check if this null, or empty string
//
//            InputChecker.setSessionErrorMessage(request, "check.declaration.no.decline.message");
//            response.sendRedirect(ServletPath.CHECK_DECLARATION);
//            return;
//        }

        InputChecker.setServiceMessage(request, "user.activities.successful.deletion.request");
        response.sendRedirect(ServletPath.VIEW_ACTIVITIES_USER);
    }
}
