package ua.kpi.model.dao;

import ua.kpi.model.entities.Activity;

import java.util.List;

public interface ActivityDao extends GenericDao<Activity> {

    List<Activity> findByUserId(int userId);

    boolean setTimeTracked(int activityId, int timeTracked);

//    List<Activity> findAllByInspectorLogin(String inspectorLogin);
//
    boolean setStatus(int activityId, String newStatus);
//
//    boolean decline(int id, String declineMessage);
//
//    boolean correct(Activity correctedActivity);
//
//    boolean assignInspector(Activity activity);
}