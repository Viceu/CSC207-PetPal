package app;

import interface_adaptor.ViewManagerModel;
import interface_adaptor.home.HomeController;
import interface_adaptor.home.HomePresenter;
import interface_adaptor.home.HomeViewModel;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
import use_case.search.SearchPetDataAccessInterface;
import view.HomeView;

import javax.swing.*;
import java.io.IOException;

public class HomeUseCaseFactory {
    private HomeUseCaseFactory(){}

    public static HomeView create(
            ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, SearchPetDataAccessInterface searchPetDataAccessObject) {

        try {
            HomeController homeController = createHomeUseCase(viewManagerModel, homeViewModel, searchPetDataAccessObject);
            return new HomeView(homeController, homeViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open access adopt page.");
        }
        return null;
    }

    private static HomeController createHomeUseCase(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, SearchPetDataAccessInterface searchPetDataAccessObject) throws IOException {

        HomeOutputBoundary homePresenter = new HomePresenter(homeViewModel, viewManagerModel);

        HomeInputBoundary homeInteractor = new HomeInteractor(searchPetDataAccessObject, homePresenter);

        return new HomeController(homeInteractor);
    }
}