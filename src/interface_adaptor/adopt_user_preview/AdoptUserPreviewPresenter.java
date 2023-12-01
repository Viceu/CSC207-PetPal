package interface_adaptor.adopt_user_preview;

import interface_adaptor.ViewManagerModel;
import interface_adaptor.home.HomeState;
import interface_adaptor.home.HomeViewModel;
import use_case.adopt_user_preview.AdoptUserPreviewOutputBoundary;
import use_case.adopt_user_preview.AdoptUserPreviewOutputData;

public class AdoptUserPreviewPresenter implements AdoptUserPreviewOutputBoundary {
    private final AdoptUserPreviewViewModel adoptUserPreviewViewModel;
    private final ViewManagerModel viewManagerModel;
    private HomeViewModel homeViewModel;

    public AdoptUserPreviewPresenter(AdoptUserPreviewViewModel adoptUserPreviewViewModel, ViewManagerModel viewManagerModel, HomeViewModel homeViewModel) {
        this.adoptUserPreviewViewModel = adoptUserPreviewViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }
    public void prepareSuccessView(AdoptUserPreviewOutputData petData){
        HomeState newState = homeViewModel.getState();
        this.homeViewModel.setState(newState);
        homeViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String failmessage) {
        HomeState homeState = homeViewModel.getState();
        // homeState.setSearchFailMessage(failmessage);
        homeViewModel.firePropertyChanged();
    }
}
