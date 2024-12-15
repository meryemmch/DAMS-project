package src.main.java.UserManager;

import src.main.java.model.User;
import java.util.List;

public interface UserOperations {
    void signup(); // No need for name, email, phoneNumber, username, password parameters since these are handled inside the method
    boolean login(); // No need to pass username and password here, as they're handled inside the method
    List<User> getAllUsers();
}

