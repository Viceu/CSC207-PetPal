package interface_adaptor.home;
import interface_adaptor.ViewManagerModel;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewState;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewViewModel;
import interface_adaptor.edit.EditState;
import interface_adaptor.edit.EditViewModel;
import interface_adaptor.login.LoginViewModel;
import interface_adaptor.login.LoginState;
import interface_adaptor.search.SearchState;
import interface_adaptor.search.SearchViewModel;
import use_case.home.HomeOutputBoundary;
import use_case.home.HomeOutputData;

public class HomePresenter implements HomeOutputBoundary{

    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final AdoptUserPreviewViewModel adoptUserPreviewViewModel;
    private final SearchViewModel searchViewModel;
    private final EditViewModel editViewModel;
    private final LoginViewModel loginViewModel;

    public HomePresenter(HomeViewModel homeViewModel, ViewManagerModel viewManagerModel,
                         AdoptUserPreviewViewModel adoptUserPreviewViewModel,
                         SearchViewModel searchViewModel, EditViewModel editViewModel, LoginViewModel loginViewModel) {
        this.homeViewModel = homeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.adoptUserPreviewViewModel = adoptUserPreviewViewModel;
        this.searchViewModel = searchViewModel;
        this.editViewModel = editViewModel;
        this.loginViewModel = loginViewModel;
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
            SearchState newState = searchViewModel.getState();
            this.searchViewModel.setState(newState);
            searchViewModel.firePropertyChanged();
            viewManagerModel.setActiveView(searchViewModel.getViewName());
        }
        else if(nextView.equals("edit")) {
            EditState newState = editViewModel.getState();
            newState.setUser(result.getUser());
            this.editViewModel.setState(newState);
            editViewModel.firePropertyChanged();
            viewManagerModel.setActiveView(editViewModel.getViewName());
        }
        else if(nextView.equals("logOut")) {
            LoginState newState = loginViewModel.getState();
            System.out.println("d");
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