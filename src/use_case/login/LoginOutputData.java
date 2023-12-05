package use_case.login;

import entities.Pet;
import entities.User;
import java.util.Map;

public class LoginOutputData {

    private final String username;
    private final String type; //Whether the user is an organization or a user
    private boolean useCaseFailed;
    private final Map<Integer, Pet> results;
    private final User user;

    public LoginOutputData(String username, String type, boolean useCaseFailed, Map<Integer, Pet> results, User user) {
        this.username = username;
        this.type = type;
        this.useCaseFailed = useCaseFailed;
        this.results = results;
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public Map<Integer, Pet> getResults() {
        return results;
    }
    public User getUser(){ return user; }
}
