package app;

import entities.CommonPetFactory;
import entities.PetFactory;
import interface_adaptor.ViewManagerModel;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewViewModel;
import interface_adaptor.edit.EditViewModel;
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

    /**
     * To create HomeView
     * @param viewManagerModel to react with changing views
     * @param homeViewModel own view model
     * @param adoptUserPreviewViewModel in case of adopting a pet
     * @param searchViewModel in case of wanting to search for a pet
     * @param editViewModel in case of wanting to edit profile
     * @param loginViewModel in case of loggin out
     * @return
     */
    public static HomeView create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel,
                                  AdoptUserPreviewViewModel adoptUserPreviewViewModel,
                                  SearchViewModel searchViewModel, EditViewModel editViewModel,
                                  LoginViewModel loginViewModel) {
        HomeController homeController = createHomeUseCase(viewManagerModel, homeViewModel,
                adoptUserPreviewViewModel, searchViewModel, editViewModel, loginViewModel);
        return new HomeView(homeController, homeViewModel);
    }

    /**
     * To create a controller
     * @param viewManagerModel
     * @param homeViewModel
     * @param adoptUserPreviewViewModel
     * @param searchViewModel
     * @param editViewModel
     * @param loginViewModel
     * @return
     */
    private static HomeController createHomeUseCase(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel,
                                                    AdoptUserPreviewViewModel adoptUserPreviewViewModel,
                                                    SearchViewModel searchViewModel, EditViewModel editViewModel,
                                                    LoginViewModel loginViewModel) {
        HomeOutputBoundary homePresenter = new HomePresenter(homeViewModel, viewManagerModel, adoptUserPreviewViewModel,
                searchViewModel, editViewModel, loginViewModel);
        HomeInputBoundary homeInteractor = new HomeInteractor(homePresenter);
        return new HomeController(homeInteractor);
    }
}