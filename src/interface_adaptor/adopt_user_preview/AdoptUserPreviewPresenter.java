package interface_adaptor.adopt_user_preview;

import interface_adaptor.ViewManagerModel;
import interface_adaptor.home.HomeState;
import interface_adaptor.home.HomeViewModel;
import interface_adaptor.org_adopt.OrgHomeState;
import interface_adaptor.org_adopt.OrgHomeViewModel;
import use_case.adopt_user_preview.AdoptUserPreviewOutputBoundary;
import use_case.adopt_user_preview.AdoptUserPreviewOutputData;

public class AdoptUserPreviewPresenter implements AdoptUserPreviewOutputBoundary {
    private final AdoptUserPreviewViewModel adoptUserPreviewViewModel;
    private final ViewManagerModel viewManagerModel;
    private HomeViewModel homeViewModel;
    private OrgHomeViewModel orgHomeViewModel;

    public AdoptUserPreviewPresenter(AdoptUserPreviewViewModel adoptUserPreviewViewModel, ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, OrgHomeViewModel orgHomeViewModel) {
        this.adoptUserPreviewViewModel = adoptUserPreviewViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.orgHomeViewModel = orgHomeViewModel;
    }
    public void prepareSuccessView(AdoptUserPreviewOutputData petData){
        OrgHomeState newState = orgHomeViewModel.getState();
        newState.setOrg(petData.getOrg());
        this.orgHomeViewModel.setState(newState);
        homeViewModel.firePropertyChanged();
        //orgHomeViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String failmessage) {
        HomeState homeState = homeViewModel.getState();
        // homeState.setSearchFailMessage(failmessage);
        homeViewModel.firePropertyChanged();
    }
}
