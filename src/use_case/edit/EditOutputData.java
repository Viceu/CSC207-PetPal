package use_case.edit;

import entities.Pet;
import entities.User;

import java.util.Map;

public class EditOutputData {

    final private String petname;
    // info regarding new pet passed out to presenter
    final private String pet_bio;
    final private String owner;
    final private User user;
    final private Pet pet;

    private  boolean editFail;

    public EditOutputData(User user, String petname, String pet_bio, String owner, Pet pet, boolean editFail) {
        this.petname = petname;
        this.pet_bio = pet_bio;
        this.owner = owner;
        this.editFail = editFail;
        this.pet = pet;
        this.user = user;

        System.out.println("EditOutput runs");
    }

    public String getPetname() {
        return petname;
    }

    public String getPet_bio() {
        return pet_bio;
    }
    public Pet getPet() {return pet;}

    public String getOwner() {
        return owner;
    }
    public User getUser() {
        return user;
    }

}
