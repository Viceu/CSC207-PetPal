package interface_adaptor.home;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInputData;

import java.util.Map;

public class HomeController {
    final HomeInputBoundary homeUseCaseInteractor;
    public HomeController(HomeInputBoundary homeUseCaseInteractor) {
        this.homeUseCaseInteractor = homeUseCaseInteractor;
    }

    // displayController has (Pet thisPet) in parameter and creates InputData(thisPet)
    public void execute(String viewName) {
        HomeInputData homeInputData = new HomeInputData();

        homeUseCaseInteractor.execute(homeInputData);
    }
}