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
    private ArrayList<Requests> deniedRequests = new ArrayList<Requests>();
    private ArrayList<Requests> acceptedRequests = new ArrayList<Requests>();
    private ArrayList<Requests> pendingRequests = new ArrayList<Requests>();

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
    private void sortRequests() {
        for (Requests req: requestList) {
            if (req.getAcceptedOrNot()) {
                acceptedRequests.add(req);
            }
            else {
                if (req.getStatus().equals("Denied")) {
                    deniedRequests.add(req);
                }
                if (req.getStatus().equals("Unreviewed")) {
                    pendingRequests.add(req);
                }
            }
        }
    }
    public ArrayList<Requests> getDeniedRequests() {
        sortRequests();
        return deniedRequests;
    }
    public ArrayList<Requests> getAcceptedRequests() {
        sortRequests();
        return acceptedRequests;
    }
    public ArrayList<Requests> getPendingRequests() {
        sortRequests();
        return pendingRequests;
    }
    public ArrayList<Requests> getRequestList() {
        return requestList;
    }
}
