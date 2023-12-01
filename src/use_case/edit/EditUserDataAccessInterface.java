package use_case.edit;
import entities.User;

public interface EditUserDataAccessInterface {
    boolean existsByName(String identifier);
    // identifier = user name

    User getUser(String username);
    void updateUser(User user, String password, String bio);
    void save(User user);

}
