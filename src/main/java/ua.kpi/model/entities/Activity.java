package ua.kpi.model.entities;

/**
 * Represents activity. Can be created by Administrator, and assigned to a User.
 */
public class Activity {
                                                //Parameters assigned at construction //TODO consider necessity of comments
    private String name;
    private String description;
    private int administratorId;
    private int userId;
                                                //Parameters set after construction
    private int id;
    private int timeTracked;
    private Status status;
    private Administrator administrator;
    private User user;
                                                //Optional parameter
    private String report;

    public enum Status {
        IN_PROCESS("status.in.work"), DELETION_REQUESTED ("status.deletion.requested"),
        DONE("status.done");

        private final String resourceBundleKey;

        Status(String resourceBundleKey) {
            this.resourceBundleKey = resourceBundleKey;
        }

        public String getResourceBundleKey() {
            return resourceBundleKey;
        }
    }

                                                //Getters and setters
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

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReport() {
        if(report == null) {
            return ("Correction message not set.");
        }
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(int administratorId) {
        this.administratorId = administratorId;
    }

    public int getTimeTracked() {
        return timeTracked;
    }

    public void setTimeTracked(int timeTracked) {
        this.timeTracked = timeTracked;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    //Builder
    private Activity(Builder builder) {
        id = builder.id;
        name = builder.name;
        description = builder.description;
        administratorId = builder.administratorId;
        userId = builder.userId;
        timeTracked = builder.timeTracked;
        report = builder.report;
        status = builder.status;
    }

    public static class Builder {

        private int id;
        private String name;
        private String description;
        private int administratorId;
        private int userId;
        private int timeTracked;
        private String report;
        private Status status;
        private Administrator administrator;            //TODO validate usage
        private User user;

        public Builder() {}

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder administratorId(int val) {
            administratorId = val;
            return this;
        }

        public Builder userId(int val) {
            userId = val;
            return this;
        }

        public Builder timeTracked(int val) {
            timeTracked = val;
            return this;
        }

        public Builder report(String val) {
            report = val;
            return this;
        }

        public Builder status(Status val) {
            status = val;
            return this;
        }

        public Activity build() {
            return new Activity(this);
        }
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", administratorId=" + administratorId +
                ", userId=" + userId +
                ", id=" + id +
                ", timeTracked=" + timeTracked +
                ", status=" + status +
                ", administrator=" + administrator +
                ", user=" + user +
                ", report='" + report + '\'' +
                '}';
    }
}

