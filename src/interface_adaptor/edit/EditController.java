package interface_adaptor.edit;

import entities.Pet;
import entities.User;
import use_case.edit.EditInputBoundary;
import use_case.edit.EditInputData;
import use_case.signup.SignupInputData;

public class EditController {
    final EditInputBoundary userEditCaseInteractor;

    public EditController(EditInputBoundary userEditCaseInteractor) {
        this.userEditCaseInteractor = userEditCaseInteractor;
    }

    public void execute(User user, String petname, String pet_bio, String owner) {
        EditInputData editInputData = new EditInputData(user,
                petname, pet_bio, owner);

        System.out.println("Edit controller executes");

        userEditCaseInteractor.execute(editInputData);
    }
}
