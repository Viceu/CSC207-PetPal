package interface_adaptor.adopt_user_preview;

import entities.Pet;
import use_case.adopt_user_preview.AdoptUserPreviewInputBoundary;
import use_case.adopt_user_preview.AdoptUserPreviewInputData;

public class AdoptUserPreviewController {
    final AdoptUserPreviewInputBoundary adoptUserPreviewUseCaseInteractor;
    public AdoptUserPreviewController(AdoptUserPreviewInputBoundary adoptUserPreviewUseCaseInteractor) {
        this.adoptUserPreviewUseCaseInteractor = adoptUserPreviewUseCaseInteractor;
    }

    public void execute(Pet thisPet, String userMessage) {

        AdoptUserPreviewInputData adoptUserPreviewInputData = new AdoptUserPreviewInputData(thisPet, userMessage);
        adoptUserPreviewUseCaseInteractor.execute(adoptUserPreviewInputData);
    }
}
