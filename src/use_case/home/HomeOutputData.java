package use_case.home;
import entities.Pet;
import entities.User;

import java.util.Map;

public class HomeOutputData {
    private final String nextView;
    private final Pet thisPet;
    private final User thisUser;
    private boolean searchFail;

    public HomeOutputData(String nextView, Pet thisPet, User thisUser, boolean searchFail) {
        this.nextView = nextView;
        this.thisPet = thisPet;
        this.thisUser = thisUser;
        this.searchFail = searchFail;
    }

    public Pet getPet() {return thisPet;}

    public String getNextView() {
        return nextView;
    }

    public User getUser() {return thisUser;}
}
