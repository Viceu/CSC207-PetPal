package interface_adaptor.org_adopt;

import entities.Requests;
import use_case.org_adopt.OrgHomeInputBoundary;
import use_case.org_adopt.OrgHomeInputData;

public class OrgHomeController {
    private final OrgHomeInputBoundary orgHomeInteractor;
    public OrgHomeController(OrgHomeInputBoundary orgHomeInteractor) {
        this.orgHomeInteractor = orgHomeInteractor;
    }

    public void execute(String viewname, Requests request, Integer optionChosen) {
        OrgHomeInputData orgHomeInputData = new OrgHomeInputData(viewname, request, optionChosen);

        orgHomeInteractor.execute(orgHomeInputData);
    }
}
