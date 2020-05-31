package ua.kpi.model.services;

import ua.kpi.model.dao.ActivityRequestDao;
import ua.kpi.model.dao.DaoFactory;
import ua.kpi.model.entities.ActivityRequest;

public class ActivityRequestService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public boolean create(ActivityRequest activityRequest) {
        try (ActivityRequestDao dao = daoFactory.createActivityRequestDao()) {
            return dao.create(activityRequest);
        }
    }
}