package use_case.home;
import entities.Pet;
import entities.User;

public class HomeInteractor implements HomeInputBoundary{
    final HomeOutputBoundary homePresenter;

    public HomeInteractor(HomeOutputBoundary homeOutputBoundary) {
        this.homePresenter = homeOutputBoundary;
    }

    @Override
    public void execute(HomeInputData homeInputData) {
        if (homeInputData.getPet() != null) {
            Pet thisPet = homeInputData.getPet();
            if (!thisPet.getAdoptable()) {
                homePresenter.prepareFailView("This pet is no longer adoptable, sorry.");
            } else {
                String nextView = homeInputData.getNextView();
                HomeOutputData homeOutputData = new HomeOutputData(nextView, thisPet, null, false);
                homePresenter.prepareSuccessView(homeOutputData);
            }
        }
        else if (homeInputData.getUser() != null) {
            User user = homeInputData.getUser();
            String nextView = homeInputData.getNextView();
            HomeOutputData homeOutputData = new HomeOutputData(nextView, null, user, false);
            homePresenter.prepareSuccessView(homeOutputData);
        }
        else{
            String nextView = homeInputData.getNextView();
            HomeOutputData homeOutputData = new HomeOutputData(nextView, null, null, false);
            homePresenter.prepareSuccessView(homeOutputData);
        }
    }
}
