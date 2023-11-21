package interface_adaptor.adopt_user_preview;

import interface_adaptor.ViewModel;
import interface_adaptor.display.DisplayState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AdoptUserPreviewViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Submit your request to adopt!";
    public static final String SUBMIT_BUTTON_LABEL = "Submit Request!";
    private AdoptUserPreviewState state = new AdoptUserPreviewState();

    public AdoptUserPreviewViewModel() {
        super("adopt preview");
    }
    public AdoptUserPreviewState getState() {
        return state;
    }

    public void setState(AdoptUserPreviewState newState) {
        this.state = newState;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
