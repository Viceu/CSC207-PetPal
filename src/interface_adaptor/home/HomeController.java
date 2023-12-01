package interface_adaptor.home;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInputData;

import java.util.Map;

public class HomeController {
    final HomeInputBoundary homeUseCaseInteractor;
    public HomeController(HomeInputBoundary homeUseCaseInteractor) {
        this.homeUseCaseInteractor = homeUseCaseInteractor;
    }

    public void execute(String viewName) {
        HomeInputData homeInputData = new HomeInputData();

        homeUseCaseInteractor.execute(homeInputData);
    }
}