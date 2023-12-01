package interface_adaptor.org_adopt;

import entities.Pet;
import entities.Requests;
import interface_adaptor.home.HomeState;

import java.util.ArrayList;
import java.util.Map;

public class OrgHomeState {
    private String id = "";
    private String failMessage = null;
    private ArrayList<Requests> requestList = new ArrayList<Requests>();

    public OrgHomeState(OrgHomeState copy) {
        id = copy.id;
        requestList = copy.requestList;
    }
    public OrgHomeState() {}
    public String getID() {
        return id;
    }
    public void setOrgHomeFailMessage(String failmessage) {this.failMessage = failmessage;}
    public Object getRequestError() {
        return failMessage;
    }
    public void updateRequestList(Requests request) {
        requestList.add(request);
    }
    public ArrayList<Requests> getRequestList() {
        return requestList;
    }
}
