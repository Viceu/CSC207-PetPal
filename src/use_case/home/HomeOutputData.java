package use_case.home;
import entities.Pet;

import java.util.Map;

public class HomeOutputData {
    private final String nextView;
    private final Map<Integer, Pet> results;

    private boolean searchFail;

    public HomeOutputData(String nextView, Map<Integer, Pet> results, boolean searchFail) {
        this.nextView = nextView;
        this.results = results;
        this.searchFail = searchFail;
    }

    public Map<Integer, Pet> getResults() {
        return results;
    }

    public String getNextView() {
        return nextView;
    }

//    private final Pet thisPet;
//    public Pet getPet() {return thisPet;}
//
//    private boolean searchFail;
//
//    public HomeOutputData(Pet thisPet, boolean searchFail) {
//        this.thisPet = thisPet;
//        this.searchFail = searchFail;
//    }
}
