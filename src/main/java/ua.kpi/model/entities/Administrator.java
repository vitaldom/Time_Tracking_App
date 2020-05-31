package ua.kpi.model.entities;

/**
 * Represents inspector users of the application, who can check (approve or decline) submitted declarations.
 */
public class Administrator extends AbstractAppUser {
    public Administrator(String firstName, String secondName, String login, String password) {

        setFirstName(firstName);
        setSecondName(secondName);
        setLogin(login);
        setPassword(password);
        setRole(Role.ADMINISTRATOR.toString());
    }

    public static class Builder extends AbstractAppUser.Builder<Builder> {

        @Override
        public Administrator build() {
            return new Administrator(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private Administrator(Builder builder) {
        super(builder);
    }
}
