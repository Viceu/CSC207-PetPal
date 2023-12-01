package interface_adaptor.home;
import interface_adaptor.ViewManagerModel;
import use_case.home.HomeOutputBoundary;
import use_case.home.HomeOutputData;

public class HomePresenter implements HomeOutputBoundary{

    private final HomeViewModel homeViewModel;

    private final ViewManagerModel viewManagerModel;

    //private final DisplayViewModel resultViewModel;

    public HomePresenter(HomeViewModel homeViewModel,
                           ViewManagerModel viewManagerModel) { // add parameter DisplayViewModel resultViewModel
        this.homeViewModel = homeViewModel;
        this.viewManagerModel = viewManagerModel;
        //this.resultViewModel = resultViewModel
    }

    @Override
    public void prepareSuccessView(HomeOutputData result) {
        // on success, switch to the respective next view

        // all this but for resultViewModel
        HomeState homeState = homeViewModel.getState();
        homeState.setResults(result.getResults());
        this.homeViewModel.setState(homeState);
        homeViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String failmessage) {
        HomeState homeState = homeViewModel.getState();
        homeState.setHomeFailMessage(failmessage);
        homeViewModel.firePropertyChanged();
    }
}