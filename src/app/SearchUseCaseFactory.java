package app;

import data_access.ApiPetDataAccessObject;
import interface_adaptor.ViewManagerModel;
import interface_adaptor.search.SearchViewModel;
import interface_adaptor.display.DisplayViewModel;
import interface_adaptor.search.SearchController;
import interface_adaptor.search.SearchPresenter;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchPetDataAccessInterface;
import entities.CommonPetFactory;
import entities.PetFactory;
import view.SearchView;
import view.DisplayView;

import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {

    /** Prevent instantiation. */
    private SearchUseCaseFactory() {}

    public static SearchView create(
            ViewManagerModel viewManagerModel, SearchViewModel searchViewModel,
            DisplayViewModel displayViewModel, SearchPetDataAccessInterface searchPetDataAccessObject) {

        try {
            SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, displayViewModel, searchPetDataAccessObject);
            return new SearchView(searchController, searchViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, DisplayViewModel displayViewModel, SearchPetDataAccessInterface petDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(searchViewModel, viewManagerModel, displayViewModel);

        PetFactory petFactory = new CommonPetFactory();

        SearchInputBoundary searchInteractor = new SearchInteractor(
                petDataAccessObject, searchOutputBoundary, petFactory);

        return new SearchController(searchInteractor);
    }

}
