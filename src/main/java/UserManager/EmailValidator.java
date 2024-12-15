package src.main.java.UserManager;

public class EmailValidator extends Validator {
    @Override
    public boolean Valid(String email) {
        // Simple email pattern validation
        String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailPattern);
    }
}
