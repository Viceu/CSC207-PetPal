package interface_adaptor.edit;

import interface_adaptor.ViewManagerModel;
import interface_adaptor.home.HomeState;
import interface_adaptor.home.HomeViewModel;
import use_case.edit.EditOutputBoundary;
import use_case.edit.EditOutputData;

public class EditPresenter implements EditOutputBoundary {

    private final EditViewModel editViewModel;
    private final HomeViewModel homeViewModel;
    private ViewManagerModel viewManagerModel;

    public EditPresenter(ViewManagerModel viewManagerModel, EditViewModel editViewModel, HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.editViewModel = editViewModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(EditOutputData edited) {
        // on success, switch to the home page
        HomeState homeState = homeViewModel.getState();
        homeState.setUser(edited.getUser());
        this.homeViewModel.setState(homeState);
        this.homeViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(homeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String editFail) {
        EditState editState = editViewModel.getState();
        editState.setPetnameError(editFail);
        editViewModel.firePropertyChanged();
    }
}
