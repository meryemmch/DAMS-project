package src.main.java.UserManager;

public class UserNameValidator extends Validator {
    @Override
    public boolean Valid(String username) {
        // Validate username: 3-20 characters, letters, digits, underscores
        return username.matches("^[a-zA-Z0-9_]{3,20}$");
    }
}
