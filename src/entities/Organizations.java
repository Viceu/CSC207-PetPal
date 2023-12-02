package entities;

import java.util.ArrayList;
import java.util.Map;

public class Organizations implements User {
    private final String name;
    private final Map<String, Pet> pets;
    private String bio;
    private final String id;
    private String password;
    private ArrayList<Requests> requestList = new ArrayList<Requests>();

    public Organizations(String name, Map<String, Pet> pets, String bio) {
        this.name = name;
        this.pets = pets;
        this.bio = bio;
        this.password = "1234";
        this.id = getOrgID(pets);
    }
    private String getOrgID(Map<String, Pet> pets) {
        Pet firstPet = (Pet) pets.values().toArray()[0];
        return firstPet.getOrganizationID();
    }
    public void add_pets(Pet pet) {
        pets.put(pet.getName(), pet);
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

    @Override
    public Map<String, Pet> getPets() {
        return pets;
    }

    @Override
    public String getPetsNames() {
        String petNames = "";
        for (Map.Entry<String, Pet> entry : pets.entrySet()) {
            petNames += entry.getValue().getName();
        }
        return petNames;
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
