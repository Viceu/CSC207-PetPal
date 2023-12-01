package use_case.home;
import entities.Pet;

import java.util.Map;

public class HomeOutputData {
    private final Map<Integer, Pet> results;

    private boolean searchFail;

    public HomeOutputData(Map<Integer, Pet> results, boolean searchFail) {
        this.results = results;
        this.searchFail = searchFail;
    }

    public Map<Integer, Pet> getResults() {
        return results;
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
