package app;

import interface_adaptor.ViewManagerModel;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewViewModel;
import interface_adaptor.display.*;
import use_case.display.*;
import view.DisplayView;
import javax.swing.*;
import java.io.IOException;

public class DisplayUseCaseFactory {
    private DisplayUseCaseFactory() {}

    public static DisplayView create(
            ViewManagerModel viewManagerModel, DisplayViewModel displayViewModel, AdoptUserPreviewViewModel adoptUserPreviewViewModel) {

        try {
            DisplayController displayController = createSearchUseCase(viewManagerModel, displayViewModel, adoptUserPreviewViewModel);
            return new DisplayView(displayViewModel, displayController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open access adopt page.");
        }
        return null;
    }

    private static DisplayController createSearchUseCase(ViewManagerModel viewManagerModel, DisplayViewModel displayViewModel, AdoptUserPreviewViewModel adoptUserPreviewViewModel) throws IOException {

        DisplayOutputBoundary displayOutputBoundary = new DisplayPresenter(displayViewModel, viewManagerModel, adoptUserPreviewViewModel);

        DisplayInputBoundary displayInteractor = new DisplayInteractor(displayOutputBoundary);

        return new DisplayController(displayInteractor);
    }
}
