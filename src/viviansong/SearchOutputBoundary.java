package viviansong;

public interface SearchOutputBoundary {

    void prepareSuccessView(SearchOutputData result);

    void prepareFailView(String searchFail);
}
