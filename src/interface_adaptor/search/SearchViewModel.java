package interface_adaptor.search;

import interface_adaptor.ViewModel;
import view.SearchView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Pet Properties";
    public static final String SPECIES_LABEL = "Species: ";
    public static final String GOOD_WITH_KIDS_LABEL = "Good with kids: ";
    public static final String COLOR_LABEL = "Color: ";
    public static final String SEARCH_BUTTON_LABEL = "Search!";
    public static final String BREED_LABEL = "Breed: ";
    public static final String PHONE_LABEL = "Phone: ";
    public static final String EMAIL_LABEL = "Email: ";
    public static final String AGE_LABEL = "Age: ";
    public static final String SEX_LABEL = "Sex: ";
    public static final String SIZE_LABEL = "Size: ";
    public static final String COAT_LABEL = "Coat: ";
    public static final String HOUSE_TRAINED_LABEL = "House-trained: ";
    public static final String DECLAWED_LABEL = "Declawed: ";
    public static final String GOOD_WITH_DOGS_LABEL = "Good with (other) dogs: ";
    public static final String GOOD_WITH_CATS_LABEL = "Good with (other) cats: ";
    private SearchState state = new SearchState();

    public SearchViewModel() {
        super("search");
    }
    public SearchState getState() {
        return state;
    }

    public void addPropertyChangeListener(SearchView searchView) {
    }

    public void setState(SearchState currentState) {
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
