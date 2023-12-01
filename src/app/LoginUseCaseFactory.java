package app;

import entities.PersonalUserFactory;
import entities.UserFactory;
import interface_adaptor.ViewManagerModel;
import interface_adaptor.home.HomeViewModel;
import interface_adaptor.organization.OrgHomeViewModel;
import interface_adaptor.login.LoginController;
import interface_adaptor.login.LoginPresenter;
import interface_adaptor.login.LoginViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;
public class LoginUseCaseFactory {

    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            HomeViewModel homeViewModel,
            LoginUserDataAccessInterface userDataAccessObject) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, homeViewModel, userDataAccessObject);
            return new LoginView(loginViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            HomeViewModel homeViewModel,
            OrgHomeViewModel orgViewModel,
            LoginUserDataAccessInterface userDataAccessObject) throws IOException {

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, homeViewModel, orgViewModel, loginViewModel);

        UserFactory userFactory = new PersonalUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
