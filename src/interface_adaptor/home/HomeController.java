package interface_adaptor.home;
import entities.Pet;
import entities.User;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInputData;

public class HomeController {
    final HomeInputBoundary homeUseCaseInteractor;
    public HomeController(HomeInputBoundary homeUseCaseInteractor) {
        this.homeUseCaseInteractor = homeUseCaseInteractor;
    }

    // displayController has (Pet thisPet) in parameter and creates InputData(thisPet)
    public void execute(String viewName, User thisUser, Pet thisPet) {
        HomeInputData homeInputData = new HomeInputData(viewName,thisPet, thisUser);
        homeUseCaseInteractor.execute(homeInputData);
    }

}