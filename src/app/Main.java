package app;

import data_access.ApiPetDataAccessObject;
import entities.CommonPetFactory;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewViewModel;
import interface_adaptor.display.DisplayViewModel;
import interface_adaptor.home.HomeViewModel;
import interface_adaptor.search.SearchViewModel;
import interface_adaptor.ViewManagerModel;
import view.AdoptUserPreviewView;
import view.DisplayView;
import view.SearchView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Search Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        SearchViewModel searchViewModel = new SearchViewModel();
        DisplayViewModel displayViewModel = new DisplayViewModel();
        AdoptUserPreviewViewModel adoptUserPreviewViewModel = new AdoptUserPreviewViewModel();
        HomeViewModel homeViewModel = new HomeViewModel();

        ApiPetDataAccessObject apiPetDataAccessObject;
        try {
            apiPetDataAccessObject = new ApiPetDataAccessObject(new CommonPetFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, displayViewModel,
                apiPetDataAccessObject);
        views.add(searchView, searchView.viewName);

        DisplayView displayView = DisplayUseCaseFactory.create(viewManagerModel, displayViewModel, adoptUserPreviewViewModel);
        views.add(displayView, displayView.viewName);

        AdoptUserPreviewView requestView = AdoptUserPreviewUseCaseFactory.create(viewManagerModel, adoptUserPreviewViewModel, homeViewModel);
        views.add(requestView, requestView.viewName);

        viewManagerModel.setActiveView(searchView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}