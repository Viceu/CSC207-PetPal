package use_case;

import data_access.FileUserDataAccessObject;
import entities.PersonalUserFactory;
import org.junit.jupiter.api.Test;
import use_case.signup.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {

    @Test
    void successTest() throws IOException {
        SignupInputData inputData = new SignupInputData("test", "password", "password");
        SignupUserDataAccessInterface userRepository = new FileUserDataAccessObject("/users.csv", new PersonalUserFactory());

        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertEquals("test", user.getUsername());
                assertNotNull(user.getCreationTime());
                assertTrue(userRepository.existsByName("test"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new PersonalUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() throws IOException {
        SignupInputData inputData = new SignupInputData("test", "password", "hehe");
        SignupUserDataAccessInterface userRepository = new FileUserDataAccessObject("/users.csv", new PersonalUserFactory());

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new PersonalUserFactory());
        interactor.execute(inputData);
    }

}