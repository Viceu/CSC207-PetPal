package use_case.edit;

import entities.Pet;
import entities.PetFactory;

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
        if (petDataAccessObject.existsByName(editInputData.getPetname())) {
            petPresenter.prepareFailView("Pet Name already exists.");
        } else {
            Pet pet = petFactory.create(null, null, null, editInputData.getPetname(), null, null,
                    null, null, null, null, null, null, false,
                    null, null, null, editInputData.getPet_bio(), editInputData.getOwner());
            petDataAccessObject.save(pet);

            EditOutputData editOutputData = new EditOutputData(pet.getName(), pet.getBio(), pet.getOwner(), false);
            petPresenter.prepareSuccessView(editOutputData);
        }
    }
}
