package interface_adaptor.display;

import interface_adaptor.ViewModel;
import view.DisplayView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DisplayViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Here are the results of your search: ";
    public static final String NEXT_BUTTON_LABEL = "Next";
    public static final String ID_LABEL = "Pet";
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
