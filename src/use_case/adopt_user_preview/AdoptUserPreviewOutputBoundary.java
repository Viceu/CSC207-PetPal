package use_case.adopt_user_preview;

public interface AdoptUserPreviewOutputBoundary {
    void prepareFailView(String message);
    void prepareSuccessView(AdoptUserPreviewOutputData adoptUserPreviewOutputData);
}
