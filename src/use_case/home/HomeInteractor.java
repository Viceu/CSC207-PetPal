package use_case.home;
import entities.Pet;
import entities.User;

public class HomeInteractor implements HomeInputBoundary{
    final HomeOutputBoundary homePresenter;

    /**
     * Construct interactor using given presenter
     * @param homeOutputBoundary
     */
    public HomeInteractor(HomeOutputBoundary homeOutputBoundary) {
        this.homePresenter = homeOutputBoundary;
    }

    /**
     * Process input data, generate output data based on indicated next view, then prepare view in presenter
     * @param homeInputData
     */
    @Override
    public void execute(HomeInputData homeInputData) {
        String nextView = homeInputData.getNextView();
        if (homeInputData.getPet() != null) {
            Pet thisPet = homeInputData.getPet();
            if (!thisPet.getAdoptable()) {
                homePresenter.prepareFailView("This pet is no longer adoptable, sorry.");
            } else {
                HomeOutputData homeOutputData = new HomeOutputData(nextView, thisPet, null, false);
                homePresenter.prepareSuccessView(homeOutputData);
            }
        }
        else if (nextView.equals("edit")) {
            HomeOutputData homeOutputData = new HomeOutputData(nextView, null, homeInputData.getUser(), false);
            homePresenter.prepareSuccessView(homeOutputData);
        }
        else{
            if(nextView.equals("search") || nextView.equals("logOut")) {
                HomeOutputData homeOutputData = new HomeOutputData(nextView, null, null, false);
                homePresenter.prepareSuccessView(homeOutputData);
            } else{
                homePresenter.prepareFailView("Invalid next view");
            }
        }
    }
}
