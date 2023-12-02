package use_case.home;

import entities.Pet;
import entities.User;

public class HomeInputData {
    final private Pet thisPet;
    final private User thisUser;

    final private String nextView;

    public HomeInputData(String nextView, Pet thisPet, User thisUser) {
        this.nextView = nextView;
        this.thisPet = thisPet;
        this.thisUser = thisUser;
    }

    Pet getPet() {
        return thisPet;
    }

    String getNextView() {
        return nextView;
    }

    User getUser() {return thisUser;}
}
