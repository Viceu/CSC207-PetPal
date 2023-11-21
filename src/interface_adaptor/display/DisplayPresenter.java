package interface_adaptor.display;

import interface_adaptor.ViewManagerModel;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewState;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewViewModel;
import use_case.display.*;

public class DisplayPresenter implements DisplayOutputBoundary {
    private final DisplayViewModel displayViewModel;
    private final ViewManagerModel viewManagerModel;
    private final AdoptUserPreviewViewModel adoptUserPreviewViewModel;

    public DisplayPresenter(DisplayViewModel displayViewModel, ViewManagerModel viewManagerModel, AdoptUserPreviewViewModel adoptUserPreviewViewModel) {
        this.displayViewModel = displayViewModel;
        this.viewManagerModel = viewManagerModel;
        this.adoptUserPreviewViewModel = adoptUserPreviewViewModel;
    }
    public void prepareSuccessView(DisplayOutputData petData){

        AdoptUserPreviewState newState = adoptUserPreviewViewModel.getState();
        newState.setPet(petData.getPet());
        this.adoptUserPreviewViewModel.setState(newState);
        adoptUserPreviewViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(adoptUserPreviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String failmessage) {
        DisplayState displayState = displayViewModel.getState();
        displayState.setSearchFailMessage(failmessage);
        displayViewModel.firePropertyChanged();
    }
}
