package use_case;

import data_access.EditInMemoryPetDataAccessObject;
import data_access.EditInMemoryUserDataAccessObject;
import entities.*;
import org.junit.jupiter.api.Test;
import use_case.edit.*;
import use_case.signup.SignupInputData;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EditInteractorTest {
    @Test
    void successTest(){
        User user = new PersonalUser("Jess", "123", "cat person", LocalDateTime.now());
        EditInputData inputData = new EditInputData(user, "Coco13",
                "I am a rabbit.", "Jess");
        EditUserDataAccessInterface userRepository = new EditInMemoryUserDataAccessObject();
        EditPetDataAccessInterface petRepository = new EditInMemoryPetDataAccessObject();

        EditOutputBoundary successPresenter = new EditOutputBoundary() {
            @Override
            public void prepareSuccessView(EditOutputData pet) {
                // things to check: the output data is correct, and the pet has been created in the DAO.
                assertEquals("Coco13", pet.getPetname());
                assertEquals("I am a rabbit.", pet.getPet_bio());
                assertEquals("Jess", pet.getOwner());

                assertTrue(petRepository.existsByName("Coco13"));
            }

            @Override
            public void prepareFailView(String editFail) {
                fail("Use case failure is unexpected.");
            }
        };

        EditInputBoundary interactor = new EditInteractor(petRepository, successPresenter, new CommonPetFactory());
        interactor.execute(inputData);
    }

    @Test
    void failPetNameExistsTest() {
        User user = new PersonalUser("Jess", "123", "cat person", LocalDateTime.now());
        EditInputData inputData = new EditInputData(user, "Coco13",
                "I am a rabbit.", "Jess");
        EditPetDataAccessInterface petRepository = new EditInMemoryPetDataAccessObject();

        // Add Coco13 to the repo so that when we check later they already exist
        PetFactory factory = new CommonPetFactory();
        Pet pet = factory.create(null, null, null, "Coco13", null, null,
                null, null, null, null, null, null, false,
                null, null, null, "I am a rabbit.", "Jessica");
        petRepository.save(pet);

        // This creates a presenter that tests whether the test case is as we expect.
        EditOutputBoundary failurePresenter = new EditOutputBoundary() {
            @Override
            public void prepareSuccessView(EditOutputData pet) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String editFail) {
                assertEquals("Pet name already exists.", editFail);
            }
        };

        EditInputBoundary interactor = new EditInteractor(petRepository, failurePresenter, new CommonPetFactory());
        interactor.execute(inputData);
    }


}
