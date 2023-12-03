package interface_adaptor.edit;

import entities.Pet;
import entities.User;

import java.util.ArrayList;

public class EditState {
    private User user;

    private String petname = "";
    // view: petname (input data)-> controller -> Interactor
    // Interactor -> output -> presenter -> state
    private ArrayList<Pet> pet = new ArrayList<Pet>();

    private String petnameError = null;

    // pass this User to the view model and view model controls view (user's screen)

    public EditState(EditState copy, User user) {
        this.user = user;
        petname = copy.petname;
        pet = copy.pet;
        petnameError = copy.petnameError;
    }

    public EditState() {
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }
    public void addPet(Pet thisPet) {
        this.pet.add(thisPet);
    }
    public ArrayList<Pet> getPets() {
        return this.pet;
    }

    public String getPetnameError() {
        return petnameError;
    }

    public void setPetnameError(String petnameError) {
        this.petnameError = petnameError;
    }

    // get state from view model

}
