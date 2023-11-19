package use_case.search;

public interface SearchOutputBoundary {

    void prepareSuccessView(SearchOutputData result);

    void prepareFailView(String searchFail);
}
