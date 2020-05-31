package ua.kpi.model.entities;

public class ActivityRequest {
    private int id;
    private String name;
    private String description;
    private int userId;
    private int assignedActivityId;

    public ActivityRequest(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAssignedActivityId() {
        return assignedActivityId;
    }

    public void setAssignedActivityId(int assignedActivityId) {
        this.assignedActivityId = assignedActivityId;
    }
}
