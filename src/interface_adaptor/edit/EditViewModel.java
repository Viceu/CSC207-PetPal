package interface_adaptor.edit;

import interface_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EditViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Edit View";

    public static final String EDIT_BUTTON_LABEL = "Add";
    public static final String EXIT_BUTTON_LABEL = "Exit";
    private EditState state = new EditState();

    public EditViewModel() {
        super("Add");
    }
    public EditState getState() {
        return state;
    }

    public void setState(EditState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
