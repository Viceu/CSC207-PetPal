package use_case.login;

public class LoginOutputData {

    private final String username;
    private final String type; //Whether the user is an organization or a user
    private boolean useCaseFailed;

    public LoginOutputData(String username, String type, boolean useCaseFailed) {
        this.username = username;
        this.type = type;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }
}
