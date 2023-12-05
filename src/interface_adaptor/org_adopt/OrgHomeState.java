package interface_adaptor.org_adopt;

import entities.Organizations;
import entities.Requests;
import java.util.ArrayList;

public class OrgHomeState {
    private String id = "";
    private String failMessage = null;
    private Organizations org;
    private ArrayList<Requests> requestList = new ArrayList<Requests>();
    public OrgHomeState() {}
    public void setOrgHomeFailMessage(String failmessage) {this.failMessage = failmessage;}
    public Object getRequestError() {
        return failMessage;
    }
    public void setOrg(Organizations newOrg) {this.org = newOrg;}
    public Organizations getOrg() {return org;}
}
