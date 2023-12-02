package entities;

import java.util.ArrayList;
import java.util.Map;

public class Organizations implements User {
    private final String name;

    private String password;
    private String bio;

    private ArrayList<Requests> requestList = new ArrayList<Requests>();

    public Organizations(String name, String password, String bio) {
        this.name = name;
        this.password = password; // org is automatically created with password: "1234"
        this.bio = bio;
    }
    private String getOrgID(Map<String, Pet> pets) {
        Pet firstPet = (Pet) pets.values().toArray()[0];
        return firstPet.getOrganizationID();
    }

    public void addRequest(Requests request) {
        requestList.add(request);
    }
    public ArrayList<Requests> getRequests() {
        return requestList;
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

    public void acceptRequest(Requests request, String message) {
        request.setStatus("Accepted"); // sets the status of the request as accepted
        request.getPet().setAdoptable(false); // set the pet as no longer adoptable
        request.setMessage(message);
        // this should return a message to the user that tells them that their request was accepted,
        // but I'm not sure if this should be done through the interactor or through this method
    }
    public void rejectRequest(Requests request, String message) {
        request.setStatus("Denied");
        request.setMessage(message);
        // TODO: request.getUser().deleteRequest(); // delete the request from the user's list automatically?
        // should also return a failure message, but not sure if done through here or not
    }
}
