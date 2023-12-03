package use_case.adopt_user_preview;

import entities.Organizations;
import entities.Pet;

public class AdoptUserPreviewOutputData {
    private final Pet thisPet;
    private boolean displayFail;
    private Organizations org;
    public AdoptUserPreviewOutputData(Pet thisPet, boolean failureMessage, Organizations org) {
        this.thisPet = thisPet;
        this.displayFail = failureMessage;
        this.org = org;
    }

    public Pet getPet() {return thisPet;}
    public Organizations getOrg() {return this.org;}
}
