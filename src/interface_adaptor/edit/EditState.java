package interface_adaptor.edit;

import entities.User;

public class EditState {
    private User user;

    private String petname = "";
    // view: petname (input data)-> controller -> Interactor
    // Interactor -> output -> presenter -> state

    private String petnameError = null;

    // pass this User to the view model and view model controls view (user's screen)

    public EditState(EditState copy) {
        this.user = user;
        petname = copy.petname;
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

    public String getPetnameError() {
        return petnameError;
    }

    public void setPetnameError(String petnameError) {
        this.petnameError = petnameError;
    }
    // get state from view model

}
