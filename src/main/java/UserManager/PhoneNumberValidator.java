package src.main.java.UserManager;

public class PhoneNumberValidator extends Validator {
    @Override
    public boolean Valid(String phoneNumber) {
        // Validate phone number: digits only, 10-15 characters
        return phoneNumber.matches("^\\d{8,12}$");
    }
}
