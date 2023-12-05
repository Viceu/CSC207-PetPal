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

    public EditState() {
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
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

}
