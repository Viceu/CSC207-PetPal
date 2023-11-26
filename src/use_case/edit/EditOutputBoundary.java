package use_case.edit;

import use_case.search.SearchOutputData;

public interface EditOutputBoundary {
    void prepareSuccessView(EditOutputData edited);

    void prepareFailView(String editFail);
}
