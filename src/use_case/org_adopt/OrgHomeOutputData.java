package use_case.org_adopt;

import entities.Requests;

public class OrgHomeOutputData {
    private final Requests request;
    private boolean searchFail;
    private String viewName;

    public OrgHomeOutputData(Requests request, boolean searchFail) {
        this.request = request;
        this.searchFail = searchFail;
    }

    public Requests getRequest() {
        return request;
    }

    public void setViewName(String orgHome) {
        this.viewName = orgHome;
    }
    public String getViewName() {
        return viewName;
    }
}
