package interface_adaptor.search;

import interface_adaptor.ViewManagerModel;
import interface_adaptor.display.DisplayViewModel;
import interface_adaptor.display.DisplayState;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;

    private final ViewManagerModel viewManagerModel;

    private final DisplayViewModel resultViewModel;

    public SearchPresenter(SearchViewModel searchViewModel,
                           ViewManagerModel viewManagerModel,
                           DisplayViewModel resultViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.resultViewModel = resultViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData result){
        // on success, switch to the result-displaying view

        DisplayState resultState = resultViewModel.getState();
        resultState.setResults(result.getResults());
        this.resultViewModel.setState(resultState);
        resultViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(resultViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String failmessage) {
        SearchState searchState = searchViewModel.getState();
        searchState.setSearchFailMessage(failmessage);
        searchViewModel.firePropertyChanged();
    }
}
