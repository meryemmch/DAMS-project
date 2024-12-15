package src.main.java.UserManager;

public class PasswordValidator extends Validator {
    @Override
    public boolean Valid(String password) {
        // Check if password contains at least one letter and one digit
        return password.matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$");
    }
}