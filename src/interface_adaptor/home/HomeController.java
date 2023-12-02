package interface_adaptor.home;
import entities.Pet;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInputData;

import java.util.Map;

public class HomeController {
    final HomeInputBoundary homeUseCaseInteractor;
    public HomeController(HomeInputBoundary homeUseCaseInteractor) {
        this.homeUseCaseInteractor = homeUseCaseInteractor;
    }

    // displayController has (Pet thisPet) in parameter and creates InputData(thisPet)
    public void executeView(String viewName) {
        HomeInputData homeInputData = new HomeInputData(viewName,null);
        homeUseCaseInteractor.execute(homeInputData);
    }

    public void executeRec(Pet thisPet) {
        HomeInputData homeInputData = new HomeInputData("adopt", thisPet);
        homeUseCaseInteractor.execute(homeInputData);
    }
}