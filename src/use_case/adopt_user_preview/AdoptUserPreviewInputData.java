package use_case.adopt_user_preview;

import entities.Pet;

public class AdoptUserPreviewInputData {
    final private Pet thisPet;
    final private String userMessage;

    final private String username;
    public AdoptUserPreviewInputData(Pet thisPet, String userMessage, String username) {
        this.thisPet = thisPet;
        this.userMessage = userMessage;
        this.username = username;
    }
    public Pet getPet() {
        return thisPet;
    }
    public String getUserMessage() {
        return userMessage;
    }

    public String getUsername() {
        return username;
    }
}
