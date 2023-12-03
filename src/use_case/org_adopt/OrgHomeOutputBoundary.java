package use_case.org_adopt;

public interface OrgHomeOutputBoundary {
    void prepareSuccessView(OrgHomeOutputData result);
    void prepareFailView(String failMessage);
}
