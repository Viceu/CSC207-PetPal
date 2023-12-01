package interface_adaptor.adopt_user_preview;

import interface_adaptor.ViewManagerModel;
import use_case.adopt_user_preview.AdoptUserPreviewOutputBoundary;
import use_case.adopt_user_preview.AdoptUserPreviewOutputData;

public class AdoptUserPreviewPresenter implements AdoptUserPreviewOutputBoundary {
    private final AdoptUserPreviewViewModel adoptUserPreviewViewModel;
    private final ViewManagerModel viewManagerModel;
    // TODO: private final AdoptUserPreviewViewModel adoptUserPreviewViewModel;

    public AdoptUserPreviewPresenter(AdoptUserPreviewViewModel displayViewModel, ViewManagerModel viewManagerModel /*, TODO: AdoptUserPreviewViewModel adoptUserPreviewViewModel */) {
        this.adoptUserPreviewViewModel = displayViewModel;
        this.viewManagerModel = viewManagerModel;
        // TODO: this.adoptUserPreviewViewModel = adoptUserPreviewViewModel;
    }
    public void prepareSuccessView(AdoptUserPreviewOutputData petData){
        AdoptUserPreviewState newState = adoptUserPreviewViewModel.getState();
        newState.setPet(petData.getPet());
        this.adoptUserPreviewViewModel.setState(newState);
        adoptUserPreviewViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(adoptUserPreviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String failmessage) {
        AdoptUserPreviewState displayState = adoptUserPreviewViewModel.getState();
        displayState.setSearchFailMessage(failmessage);
        adoptUserPreviewViewModel.firePropertyChanged();
    }
}
