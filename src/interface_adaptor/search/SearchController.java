package interface_adaptor.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

import java.util.Map;

public class SearchController {
    final SearchInputBoundary searchUseCaseInteractor;
    public SearchController(SearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;
    }

    public void execute(Map<String, String> requirements) {
        SearchInputData searchInputData = new SearchInputData(requirements);

        searchUseCaseInteractor.execute(searchInputData);
    }
}
