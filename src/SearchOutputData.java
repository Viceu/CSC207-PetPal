import entities.Pet;

import java.util.Map;

public class SearchOutputData {

    private final Map<Integer, Pet> requirements;

    private boolean searchFail;

    public SearchOutputData(Map<Integer, Pet> requirements, boolean searchFail) {
        this.requirements = requirements;
        this.searchFail = searchFail;
    }

    public Map<Integer, Pet> getRequirements() {
        return requirements;
    }
}
