package app;

import entities.CommonPetFactory;
import entities.PetFactory;
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
import use_case.search.SearchPetDataAccessInterface;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            HomeViewModel homeViewModel, OrgHomeViewModel orgViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            SearchPetDataAccessInterface petDataAccessObject) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, homeViewModel,
                    orgViewModel, userDataAccessObject, petDataAccessObject);
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
            LoginUserDataAccessInterface userDataAccessObject,
            SearchPetDataAccessInterface petDataAccessObject) throws IOException {

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, homeViewModel, orgViewModel, loginViewModel);

        PetFactory petFactory = new CommonPetFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary, petDataAccessObject, petFactory);

        return new LoginController(loginInteractor);
    }
}
