package ua.kpi.model.services;

import ua.kpi.model.dao.DaoFactory;
import ua.kpi.model.dao.ActivityDao;
import ua.kpi.model.entities.Activity;

import java.util.List;

public class ActivityService {

    private DaoFactory daoFactory = DaoFactory.getInstance();


//    public List<Declaration> findAllByInspectorLogin(String login) {              //TODO
//        try(DeclarationDao dao = daoFactory.createDeclarationDao()) {
//            return dao.findAllByInspectorLogin(login);
//        }
//    }

    public List<Activity> findByUserId(int id) {
        try(ActivityDao dao = daoFactory.createActivityDao()) {
            return dao.findByUserId(id);
        }
    }

    public boolean create(Activity activity) {
        try(ActivityDao dao = daoFactory.createActivityDao()) {
            return dao.create(activity);
        }
    }

    public boolean setTimeTracked(int activityId, int timeTracked) {
        try(ActivityDao dao = daoFactory.createActivityDao()) {
            return dao.setTimeTracked(activityId, timeTracked);
        }
    }

    public boolean setStatus(int activityId, String newStatus) {
        try(ActivityDao dao = daoFactory.createActivityDao()) {
            return dao.setStatus(activityId, newStatus);
        }
    }

//    public boolean decline(int id, String declineMessage) {
//        try(DeclarationDao dao = daoFactory.createDeclarationDao()) {
//            return dao.decline(id, declineMessage);
//        }
//    }
//
//    public boolean correct(Declaration correctedDeclaration) {
//        try(DeclarationDao dao = daoFactory.createDeclarationDao()) {
//            return dao.correct(correctedDeclaration);
//        }
//    }
}
