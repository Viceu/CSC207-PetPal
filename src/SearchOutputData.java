import entities.Pet;

import java.util.Map;

public class SearchOutputData {

    private final Map<Integer, Pet> results;

    private boolean searchFail;

    public SearchOutputData(Map<Integer, Pet> results, boolean searchFail) {
        this.results = results;
        this.searchFail = searchFail;
    }

    public Map<Integer, Pet> getResults() {
        return results;
    }
}
