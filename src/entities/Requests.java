package entities;

import java.util.Objects;

public class Requests {
    //Since most of the traits are TBD will have to be updated when it's decided
    private final Pet pet;
    private final Organizations organization;
    private final User user;
    private final String userMessage;
    private String orgMessage;
    private String status;
    private boolean acceptedOrNot;
    public Requests(Pet pet, User user, String message, Organizations organization) {
        this.pet = pet;
        this.user = user;
        this.userMessage = message;
        this.orgMessage = "The organization has not reviewed this request yet.";
        this.status = "Unreviewed";
        this.acceptedOrNot = false;
        this.organization = organization;
    }
    public void setStatus(String newStatus) {
        this.status = newStatus;
        acceptedOrNot = newStatus.equals("Accepted");
    }
    public Pet getPet() {
        return pet;
    }
    public User getUser() {
        return user;
    }
    public Organizations getOrganization() {return organization;}
    public void setOrgMessage(String message) {
        this.orgMessage = message;
    }
    public String getStatus() {return status;}
    public String toString() {
        return "Pet: " + pet.getName() + "\n" + "Organization: " + organization.getName() + "\n" + "User: " + user.getName() + "\n" + "User's message: " + userMessage + "\n" + "Organization's message: " + orgMessage;
    }
}
