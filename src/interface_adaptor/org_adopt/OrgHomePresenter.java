package interface_adaptor.org_adopt;

import use_case.org_adopt.OrgHomeOutputBoundary;
import use_case.org_adopt.OrgHomeOutputData;

public class OrgHomePresenter implements OrgHomeOutputBoundary {

    @Override
    public void prepareSuccessView(OrgHomeOutputData result) {

    }
    @Override
    public void prepareFailView(String failMessage) {

    }
}
