package use_case.home;

import entities.Pet;

public class HomeInputData {
    final private Pet thisPet;

    final private String nextView;

    public HomeInputData(String nextView, Pet thisPet) {
        this.nextView = nextView;
        this.thisPet = thisPet;
    }

    Pet getPet() {
        return thisPet;
    }

    String getNextView() {
        return nextView;
    }
}
