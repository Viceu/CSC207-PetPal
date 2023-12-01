package use_case.home;

public interface HomeOutputBoundary {

    void prepareSuccessView(HomeOutputData result);

    void prepareFailView(String failMessage);
}
