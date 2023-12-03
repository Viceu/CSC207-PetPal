package interface_adaptor.login;

// for user homepage
import interface_adaptor.home.HomeState;
import interface_adaptor.home.HomeViewModel;

// for organization homepage
import interface_adaptor.org_adopt.OrgHomeState;
import interface_adaptor.org_adopt.OrgHomeViewModel;

import interface_adaptor.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;


public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final HomeViewModel homeViewModel;
    private final OrgHomeViewModel orgViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          HomeViewModel homeViewModel, OrgHomeViewModel orgViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.orgViewModel = orgViewModel;
        this.loginViewModel = loginViewModel;
    }

    
    @Override
    public void prepareSuccessView(LoginOutputData response) {
        if (response.getType().equals("user"))
        { // displays user homepage
            HomeState homeState = homeViewModel.getState();
            homeState.setUser(response.getUser());
            homeState.setResults(response.getResults());
            this.homeViewModel.setState(homeState);
            this.homeViewModel.firePropertyChanged();

            this.viewManagerModel.setActiveView(homeViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
        else if (response.getType().equals("organization"))
        { // displays organization home page
            OrgHomeState orgHomeState = orgViewModel.getState();
            this.orgViewModel.setState(orgHomeState);
            this.orgViewModel.firePropertyChanged();

            this.viewManagerModel.setActiveView(homeViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
