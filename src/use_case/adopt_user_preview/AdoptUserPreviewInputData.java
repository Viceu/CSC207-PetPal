package use_case.adopt_user_preview;

import entities.Pet;

public class AdoptUserPreviewInputData {
    final private Pet thisPet;
    final private String userMessage;
    public AdoptUserPreviewInputData(Pet thisPet, String userMessage) {
        this.thisPet = thisPet;
        this.userMessage = userMessage;
    }
    public Pet getPet() {
        return thisPet;
    }
    public String getUserMessage() {
        return userMessage;
    }
}
