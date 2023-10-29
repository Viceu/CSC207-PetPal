public class SearchOutputData {

    private String requirements = "";

    private boolean searchFail;

    public SearchOutputData(String requirements, boolean searchFail) {
        this.requirements = requirements;
        this.searchFail = searchFail;
    }

    public String getRequirements() {
        return requirements;
    }
}
