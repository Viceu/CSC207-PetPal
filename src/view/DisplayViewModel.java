package view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DisplayViewModel extends ViewModel {
    private DisplayState state = new DisplayState();

    public DisplayViewModel() {
        super("search");
    }
    public DisplayState getState() {
        return state;
    }

    public void addPropertyChangeListener(DisplayView displayView) {
    }

    public void setState(DisplayState currentState) {
        state = currentState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
