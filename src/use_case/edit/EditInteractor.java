package use_case.edit;

import entities.Pet;
import entities.PetFactory;
import entities.User;

public class EditInteractor implements EditInputBoundary{
    final EditPetDataAccessInterface petDataAccessObject;
    final EditOutputBoundary petPresenter;
    final PetFactory petFactory;

    public EditInteractor(EditPetDataAccessInterface petDataAccessObject, EditOutputBoundary petPresenter,
                          PetFactory petFactory) {
        this.petDataAccessObject = petDataAccessObject;
        this.petPresenter = petPresenter;
        this.petFactory = petFactory;
    }

    @Override
    public void execute(EditInputData editInputData) {
        System.out.println("EditInteractor executes");
        if (petDataAccessObject.existsByName(editInputData.getPetname())) {
            petPresenter.prepareFailView("Pet Name already exists.");
        } else {
            EditOutputData editOutputData;

            if (editInputData.getPetname() != null) {
                Pet pet = petFactory.create(null, null, null, editInputData.getPetname(), null, null,
                        null, null, null, null, null, null, false,
                        null, null, null, editInputData.getPet_bio(), editInputData.getOwner());
                petDataAccessObject.save(pet);

                User user = editInputData.getUser();
                editOutputData = new EditOutputData(user, pet.getName(), pet.getBio(), pet.getOwner(), pet, false);
            }
            else {
                editOutputData = null;
            }

            petPresenter.prepareSuccessView(editOutputData);
        }
    }
}
