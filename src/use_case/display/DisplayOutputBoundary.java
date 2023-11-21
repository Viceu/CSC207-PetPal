package use_case.display;

public interface DisplayOutputBoundary {
    void prepareFailView(String message);
    void prepareSuccessView(DisplayOutputData displayOutputData);
}
