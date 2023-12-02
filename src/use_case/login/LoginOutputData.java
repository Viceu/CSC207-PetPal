package use_case.login;

import entities.Pet;

import java.util.Map;

public class LoginOutputData {

    private final String username;
    private final String type; //Whether the user is an organization or a user
    private boolean useCaseFailed;
    private final Map<Integer, Pet> results;

    public LoginOutputData(String username, String type, boolean useCaseFailed, Map<Integer, Pet> results) {
        this.username = username;
        this.type = type;
        this.useCaseFailed = useCaseFailed;
        this.results = results;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public Map<Integer, Pet> getResults() {
        return results;
    }
}
