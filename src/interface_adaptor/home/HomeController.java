package interface_adaptor.home;
import entities.Pet;
import entities.User;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInputData;

public class HomeController {
    final HomeInputBoundary homeUseCaseInteractor;

    /**
     * Construct controller with given interactor
     * @param homeUseCaseInteractor
     */
    public HomeController(HomeInputBoundary homeUseCaseInteractor) {
        this.homeUseCaseInteractor = homeUseCaseInteractor;
    }

    /**
     * Given infromation, build an input data and pass onto interactor to execute
     * @param viewName
     * @param thisUser
     * @param thisPet
     */
    public void execute(String viewName, User thisUser, Pet thisPet) {
        HomeInputData homeInputData = new HomeInputData(viewName,thisPet, thisUser);
        homeUseCaseInteractor.execute(homeInputData);
    }

}