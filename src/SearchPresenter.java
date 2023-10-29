public class SearchPresenter {

    private final SearchViewModel searchViewModel;

    private ViewManagerModel viewManagerModel;

    private ResultViewModel resultViewModel;

    public SearchPresenter(SearchViewModel searchViewModel,
                           ViewManagerModel viewManagerModel,
                           ResultViewModel resultViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.resultViewModel = resultViewModel;
    }

    public void prepareSuccessView(SearchOutputData result){
        // on success, switch to the result-displaying view

        ResultState resultState = resultViewModel.getState();
        resultState.setUsername(result.getRequirements());
        this.resultViewModel.setState(resultState);
        resultViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(resultViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String failmessage) {
        SearchState searchState = searchViewModel.getState();
        searchState.setSearchFailMessage(failmessage);
        searchViewModel.firePropertyChanged();
    }
}
