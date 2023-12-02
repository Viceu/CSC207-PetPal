package app;

import entities.CommonPetFactory;
import entities.PetFactory;
import interface_adaptor.ViewManagerModel;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewViewModel;
import interface_adaptor.home.HomeController;
import interface_adaptor.home.HomePresenter;
import interface_adaptor.home.HomeViewModel;
import interface_adaptor.login.LoginViewModel;
import interface_adaptor.search.SearchViewModel;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
import use_case.search.SearchPetDataAccessInterface;
import view.HomeView;

import javax.swing.*;
import java.io.IOException;

public class HomeUseCaseFactory {
    private HomeUseCaseFactory(){}

    public static HomeView create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel,
                                  AdoptUserPreviewViewModel adoptUserPreviewViewModel,
                                  SearchViewModel searchViewModel, EditViewModel editViewModel,
                                  LoginViewModel loginViewModel) {
        try {
            HomeController homeController = createHomeUseCase(viewManagerModel, homeViewModel,
                    adoptUserPreviewViewModel, searchViewModel, editViewModel, loginViewModel);
            return new HomeView(homeController, homeViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open access adopt page.");
        }
        return null;
    }

    private static HomeController createHomeUseCase(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel,
                                                    AdoptUserPreviewViewModel adoptUserPreviewViewModel,
                                                    SearchViewModel searchViewModel, EditViewModel editViewModel,
                                                    LoginViewModel loginViewModel) throws IOException {
        HomeOutputBoundary homePresenter = new HomePresenter(homeViewModel, viewManagerModel, adoptUserPreviewViewModel,
                searchViewModel, editViewModel, loginViewModel);
        HomeInputBoundary homeInteractor = new HomeInteractor(homePresenter);
        return new HomeController(homeInteractor);
    }
}