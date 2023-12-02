package interface_adaptor.edit;

import use_case.edit.EditInputBoundary;
import use_case.edit.EditInputData;
import use_case.signup.SignupInputData;

public class EditController {
    final EditInputBoundary userEditCaseInteractor;

    public EditController(EditInputBoundary userEditCaseInteractor) {
        this.userEditCaseInteractor = userEditCaseInteractor;
    }

    public void execute(String petname, String pet_bio, String owner) {
        EditInputData editInputData = new EditInputData(
                petname, pet_bio, owner);

        userEditCaseInteractor.execute(editInputData);
    }
}
