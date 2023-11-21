package use_case.display;

import entities.Pet;

import java.util.Map;

public class DisplayInputData {
    final private Pet thisPet;


    public DisplayInputData(Pet thisPet) {
        this.thisPet = thisPet;
    }

    Pet getPet() {
        return thisPet;
    }
}
