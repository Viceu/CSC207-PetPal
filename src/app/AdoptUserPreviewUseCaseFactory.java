package app;

import interface_adaptor.ViewManagerModel;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewController;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewPresenter;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewViewModel;
import interface_adaptor.display.*;
import interface_adaptor.home.HomeViewModel;
import use_case.adopt_user_preview.AdoptUserPreviewInputBoundary;
import use_case.adopt_user_preview.AdoptUserPreviewInteractor;
import use_case.adopt_user_preview.AdoptUserPreviewOutputBoundary;
import use_case.display.*;
import view.AdoptUserPreviewView;
import view.DisplayView;
import javax.swing.*;
import java.io.IOException;

public class AdoptUserPreviewUseCaseFactory {
    private AdoptUserPreviewUseCaseFactory() {}

    public static AdoptUserPreviewView create(
            ViewManagerModel viewManagerModel, AdoptUserPreviewViewModel requestViewModel, HomeViewModel homeViewModel) {

        try {
            AdoptUserPreviewController requestController = createSearchUseCase(viewManagerModel, requestViewModel, homeViewModel);
            return new AdoptUserPreviewView(requestViewModel, requestController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not send adoption request.");
        }
        return null;
    }

    private static AdoptUserPreviewController createSearchUseCase(ViewManagerModel viewManagerModel, AdoptUserPreviewViewModel requestViewModel, HomeViewModel homeViewModel) throws IOException {

        AdoptUserPreviewOutputBoundary requestOutputBoundary = new AdoptUserPreviewPresenter(requestViewModel, viewManagerModel, homeViewModel);

        AdoptUserPreviewInputBoundary requestInteractor = new AdoptUserPreviewInteractor(requestOutputBoundary);

        return new AdoptUserPreviewController(requestInteractor);
    }
}
