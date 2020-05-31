package ua.kpi.model.entities;

/**
 * Represents client users of the application, who can register and submit declarations.
 */
public class User extends AbstractAppUser {

    public User(String firstName, String secondName, String login, String password) {

        setFirstName(firstName);
        setSecondName(secondName);
        setLogin(login);
        setPassword(password); //TODO add personal tax number
        setRole(Role.USER.toString());
    }

    public static class Builder extends AbstractAppUser.Builder<Builder> {

        @Override
        public User build() {
            return new User(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private User(Builder builder) {
        super(builder);
    }
}
