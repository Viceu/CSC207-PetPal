package data_access;

import entities.User;
import entities.UserFactory;
import use_case.edit.EditUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class EditInMemoryUserDataAccessObject implements EditUserDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();
    // user name to User

    //private final String petsNames = new String();
    //private UserFactory userFactory;

    /**
     * @param identifier the user's username
     * @return whether the user exists
     */

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public User getUser(String username) {
        return users.get(username);
    }

    @Override
    public void updateUser(User user, String password, String bio) {

        // update user's password, bio and pets;
        // if the user did not change their password, the input password string would be null.

        // if map contains "user"
        if (users.containsKey(user.getName())) {
            // if user wants to change password
            if (password != "") {
                users.get(user).setPassword(password);
            } else if (bio != "") {
                // if user wants to change bio
                // then update respective info in User
                // map.get("key") = value (user)
                users.get(user).setBio(bio);
            } else {
                // both inputs are null, do nothing
            }
        } else {
            // it user not contain the user, do nothing
        }
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }
}
