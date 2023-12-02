package use_case.org_adopt;

import entities.Requests;

public class OrgHomeInputData {
    final private Requests request;
    final private String viewname;
    final private Integer chosenOption;
    public OrgHomeInputData(String viewname, Requests req, Integer chosenOption) {
        this.request = req;
        this.viewname = viewname;
        this.chosenOption = chosenOption;
    }
    public Requests getRequest() {
        return request;
    }
    public String getViewname() {return viewname;}

    public Integer getChosenOption() {
        return chosenOption;
    }
}
