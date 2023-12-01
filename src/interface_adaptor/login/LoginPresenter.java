package interface_adaptor.login;

// for user homepage
import interface_adaptor.home.HomeState;
import interface_adaptor.home.HomeViewModel;

// for organization homepage
import interface_adaptor.organization.OrganizationHomeState;
import interface_adaptor.organization.OrganizationHomeViewModel;

import interface_adaptor.ViewManagerModel;
import interface_adaptor.signup.SignupState;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final HomeViewModel homeViewModel;
    private final OrganizationViewModel orgViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          HomeViewModel homeViewModel, OrganizationViewModel orgViewModel,
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
            homeState.setUsername(response.getUsername());
            this.homeViewModel.setState(homeState);
            this.homeViewModel.firePropertyChanged();

            this.viewManagerModel.setActiveView(homeViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
        else if (response.getType().equals("organization"))
        { // displays organization home page
            OrganizationHomeState orgHomeState = orgViewModel.getState();
            orgHomeState.setUsername(response.getUsername());
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
