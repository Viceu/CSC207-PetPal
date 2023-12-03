package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class Organizations implements User {
    private final String name;
    private String password;
    private String bio;
    private ArrayList<Requests> requestList = new ArrayList<Requests>();
    private ArrayList<Requests> deniedRequests = new ArrayList<Requests>();
    private ArrayList<Requests> acceptedRequests = new ArrayList<Requests>();
    private ArrayList<Requests> pendingRequests = new ArrayList<Requests>();

    private LocalDateTime date;

    public Organizations(String name, String password, String bio, LocalDateTime date) {
        this.name = name;
        this.password = password; // org is automatically created with password: "1234"
        this.bio = bio;
        this.date = date;
    }

    public void addRequest(Requests request) {
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
    public String getName() {
        return name;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String getBio() {
        return bio;
    }
    @Override
    public void setBio(String bio) {
        this.bio = bio;
    }
    @Override
    public LocalDateTime getCreationTime() {
        return date;
    }
    public void acceptRequest(Requests request, String message) {
        request.setStatus("Accepted");
        request.getPet().setAdoptable(false);
        request.getPet().setOwner(request.getUser().getName());
        request.setOrgMessage(message);
    }
    public void rejectRequest(Requests request, String message) {
        request.setStatus("Denied");
        request.setOrgMessage(message);
    }
}
