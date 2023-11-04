public interface SearchOutputBoundary {

    void prepareSuccessView(SearchOutputData result);

    void prepareFailView(boolean searchFail);
}
