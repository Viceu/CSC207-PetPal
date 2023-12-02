package interface_adaptor.org_adopt;

import interface_adaptor.ViewModel;
import interface_adaptor.home.HomeState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class OrgHomeViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Requests:";
    public static final String LOGOUT_BUTTON_LABEL = "Log out";
    private String loggedInOrg;
    private OrgHomeState state = new OrgHomeState();

    // super = ViewModel
    public OrgHomeViewModel() {
        super("org home");
    }

    public void setState(OrgHomeState state) {
        this.state = state;
    }
    public OrgHomeState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public String getLoggedInOrg() {
        return loggedInOrg;
    }
    public void setLoggedInOrg(String loggedInUser) {
        loggedInOrg = loggedInUser;
    }
}
