package use_case.edit;

import entities.User;

import java.util.Map;

public class EditInputData {
    final private User user;
    final private String petname;
    // info regarding new pet passed in by the user
    final private String pet_bio;
    final private String owner;



    public EditInputData(User user, String petname, String pet_bio, String owner) {
        // if the user does not want to change certain info, the corresponding input will be null.
        this.user = user;
        this.petname = petname;
        this.pet_bio = pet_bio;
        this.owner = owner;

        System.out.println("EditInputData is called");
    }

    public String getPetname() {
        return petname;
    }

    public String getPet_bio() {
        return pet_bio;
    }

    public String getOwner() {
        return owner;
    }
    public User getUser() {
        return user;
    }

}
