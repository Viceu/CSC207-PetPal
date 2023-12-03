package use_case;

import entities.CommonPet;
import entities.Pet;
import entities.PersonalUser;
import entities.User;
import org.junit.jupiter.api.Test;
import use_case.home.*;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HomeInteractorTest {
    // This tests for a success view for the search case (user wants to search)
    @Test
    void successSearchTest(){
        HomeInputData inputData = new HomeInputData("search", null, null);
        HomeOutputBoundary successPresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(HomeOutputData outputData) {
                // things to check: the output data is correct, and the pet has been created in the DAO.
                assertEquals("search", outputData.getNextView());
                assertNull(outputData.getPet());
                assertNull(outputData.getUser());
            }

            @Override
            public void prepareFailView(String homeFail) {
                fail("Use case failure is unexpected.");
            }
        };

        HomeInputBoundary interactor = new HomeInteractor(successPresenter);
        interactor.execute(inputData);
    }

    // This tests for a success view for the edit case (user wants to edit their own profile)
    @Test
    void successEditTest(){
        User user = new PersonalUser("Tname", "Tpw", "TBio", LocalDateTime.now();)
        HomeInputData inputData = new HomeInputData("edit", null, user);
        HomeOutputBoundary successPresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(HomeOutputData outputData) {
                // things to check: the output data is correct, and the pet has been created in the DAO.
                assertEquals("edit", outputData.getNextView());
                assertNull(outputData.getPet());
                assertNotNull(outputData.getUser());
                assertEquals("Tname", outputData.getUser().getName())
                assertEquals("TBio", outputData.getUser().getBio())
            }

            @Override
            public void prepareFailView(String homeFail) {
                fail("Use case failure is unexpected.");
            }
        };

        HomeInputBoundary interactor = new HomeInteractor(successPresenter);
        interactor.execute(inputData);
    }

    // This tests for a success view for the logout case (user get send back to login)
    @Test
    void successLogoutTest(){
        HomeInputData inputData = new HomeInputData("Logout", null, null);
        HomeOutputBoundary successPresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(HomeOutputData outputData) {
                // things to check: the output data is correct, and the pet has been created in the DAO.
                assertEquals("logout", outputData.getNextView());
                assertNull(outputData.getPet());
                assertNull(outputData.getUser());
            }

            @Override
            public void prepareFailView(String homeFail) {
                fail("Use case failure is unexpected.");
            }
        };

        HomeInputBoundary interactor = new HomeInteractor(successPresenter);
        interactor.execute(inputData);
    }

    // This tests for a success view for the adopt case (user wants to adopt a pet)
    @Test
    void successAdoptTest(){
        Pet pet = new CommonPet(null, null, null, "TPName", null, null,
                null, null, null, null, null, null, true,
                null, null, null, "I am a dog.", "Vivian");
        HomeInputData inputData = new HomeInputData("adopt", null, user);
        HomeOutputBoundary successPresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(HomeOutputData outputData) {
                // things to check: the output data is correct, and the pet has been created in the DAO.
                assertEquals("adopt", outputData.getNextView());
                assertNotNull(outputData.getPet());
                assertNull(outputData.getUser());
                assertEquals("TPName", outputData.getPet().getName());
            }

            @Override
            public void prepareFailView(String homeFail) {
                fail("Use case failure is unexpected.");
            }
        };

        HomeInputBoundary interactor = new HomeInteractor(successPresenter);
        interactor.execute(inputData);
    }

    // This tests for a fail view for the adopt case when no pets are adoptable in system
    @Test
    void failPetExistTest() {
        Pet pet = new CommonPet(null, null, null, "TPName", null, null,
                null, null, null, null, null, null, false,
                null, null, null, "I am a dog.", "Vivian");

        HomeInputData inputData = new HomeInputData("adopt", pet, null);

        // This creates a presenter that tests whether the test case is as we expect.
        HomeOutputBoundary failurePresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(HomeOutputData outputData) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String homeFail) {
                assertEquals("This pet is no longer adoptable, sorry.", homeFail);
            }
        };

        HomeInputBoundary interactor = new HomeInteractor(successPresenter);
        interactor.execute(inputData);
    }

    // This tests for a fail view for when input data gives an invalid NextView name
    @Test
    void failNextViewTest() {
        HomeInputData inputData = new HomeInputData("next", null, null);

        // This creates a presenter that tests whether the test case is as we expect.
        HomeOutputBoundary failurePresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(HomeOutputData outputData) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String homeFail) {
                assertEquals("Invalid next view", homeFail);
            }
        };

        HomeInputBoundary interactor = new HomeInteractor(successPresenter);
        interactor.execute(inputData);
    }
}
