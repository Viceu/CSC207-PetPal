package interface_adaptor.adopt_user_preview;

import entities.Pet;

public class AdoptUserPreviewState {
    private Pet thisPet;
    private String failMessage = null;
    private String userMessage;
    private String username;

    public void setPet(Pet pet) {
        thisPet = pet;
    }
    public Pet getPet() {
        return thisPet;
    }
    public void setSearchFailMessage(String errorMessage) {
        failMessage = errorMessage;
    }
    public Object getPetError() {
        return failMessage;
    }

    public void addUserMessage(String text) {
        userMessage = text;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getRequirementsError() {
        return failMessage;
    }

    public void addUsername(String text) {
        this.username = text;
    }
    public String getUsername() {
        return username;
    }
}
