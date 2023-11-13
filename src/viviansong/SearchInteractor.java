package viviansong;

import java.util.Map;

public class SearchInteractor implements SearchInputBoundary {
    final SearchUserDataAccessInterface userDataAccessObject;
    final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchUserDataAccessInterface userDataAccessInterface,
                            SearchOutputBoundary searchOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.searchPresenter = searchOutputBoundary;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        Map<String, String> requirements = searchInputData.getRequirements();
    }


}
