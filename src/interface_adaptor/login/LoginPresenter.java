package interface_adaptor.login;

import interface_adaptor.logged_in.LoggedInState;
import interface_adaptor.logged_in.LoggedInViewModel;
import interface_adaptor.ViewManagerModel;
import interface_adaptor.signup.SignupState;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {

        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
