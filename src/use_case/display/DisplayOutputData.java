package use_case.display;

import entities.Pet;

public class DisplayOutputData {

    private final Pet thisPet;
    private boolean displayFail;
    public DisplayOutputData(Pet thisPet, boolean failureMessage) {
        this.thisPet = thisPet;
        this.displayFail = failureMessage;
    }

    public Pet getPet() {return thisPet;}

}
