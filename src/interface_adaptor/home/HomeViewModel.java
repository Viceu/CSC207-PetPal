package interface_adaptor.home;

import interface_adaptor.ViewModel;
import view.HomeView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomeViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Home";
    public static final String EDIT_BUTTON_LABEL = "Edit Profile";
    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String LOGOUT_BUTTON_LABEL = "Log out";
    private String loggedInUser;
    private HomeState state = new HomeState();

    // super = ViewModel
    public HomeViewModel() {
        super("logged in");
    }

    public void setState(HomeState state) {
        this.state = state;
    }
    public HomeState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    // TODO: To call from Login Presenter, alongside when HomeState sets username
    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

}
