package interface_adaptor.home;

public class HomeState {
    private String username = "";

    public HomeState(HomeState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public HomeState() {}

    public String getUsername() {
        return username;
    }

    // TODO: call from LoginPresenter
    public void setUsername(String username) {
        this.username = username;
    }
}
