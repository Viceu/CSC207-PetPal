package use_case.adopt_user_preview;

import entities.Pet;

public class AdoptUserPreviewOutputData {
    private final Pet thisPet;
    private boolean displayFail;
    public AdoptUserPreviewOutputData(Pet thisPet, boolean failureMessage) {
        this.thisPet = thisPet;
        this.displayFail = failureMessage;
    }

    public Pet getPet() {return thisPet;}
}
