package interface_adaptor.home;
import interface_adaptor.ViewManagerModel;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewState;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewViewModel;
import interface_adaptor.search.SearchViewModel;
import use_case.home.HomeOutputBoundary;
import use_case.home.HomeOutputData;

public class HomePresenter implements HomeOutputBoundary{

    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final AdoptUserPreviewViewModel adoptUserPreviewViewModel;

    //private final DisplayViewModel resultViewModel;

    public HomePresenter(HomeViewModel homeViewModel,
                           ViewManagerModel viewManagerModel,
                         AdoptUserPreviewViewModel adoptUserPreviewViewModel) {
        this.homeViewModel = homeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.adoptUserPreviewViewModel = adoptUserPreviewViewModel;
    }

    @Override
    public void prepareSuccessView(HomeOutputData result) {
        // on success, switch to the respective next view
        String nextView = result.getNextView();

        if(nextView.equals("adopt")) {
            AdoptUserPreviewState newState = adoptUserPreviewViewModel.getState();
            newState.setPet(result.getPet());
            this.adoptUserPreviewViewModel.setState(newState);
            adoptUserPreviewViewModel.firePropertyChanged();
            viewManagerModel.setActiveView(adoptUserPreviewViewModel.getViewName());
        }
        else if(nextView.equals("search")) {
            SearchViewModel newState = searchViewModel.getState();
            this.searchViewModel.setState(newState);
            searchViewModel.firePropertyChanged();
            viewManagerModel.setActiveView(searchViewModel.getViewName());
        }
        else if(nextView.equals("edit")) {
            EditViewModel newState = editViewModel.getState();
            newState.setUser(result.getUser());
            this.editViewModel.setState(newState);
            editViewModel.firePropertyChanged();
            viewManagerModel.setActiveView(editViewModel.getViewName());
        }
        else if(nextView.equals("logout")) {
            LoginViewModel newState = loginViewModel.getState();
            this.loginViewModel.setState(newState);
            loginViewModel.firePropertyChanged();
            viewManagerModel.setActiveView(loginViewModel.getViewName());
        }
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String failMessage) {
        HomeState homeState = homeViewModel.getState();
        homeState.setHomeFailMessage(failMessage);
        homeViewModel.firePropertyChanged();
    }
}