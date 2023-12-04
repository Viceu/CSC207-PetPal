package use_case.login;

import entities.Organizations;
import entities.Pet;
import entities.PetFactory;
import entities.User;
import use_case.adopt_user_preview.AdoptUserPreviewDataAccessInterface;
import use_case.search.SearchPetDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;
    final SearchPetDataAccessInterface petDataAccessObject;
    final PetFactory petFactory;
    private final AdoptUserPreviewDataAccessInterface orgDAO;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface, AdoptUserPreviewDataAccessInterface orgDAO,
                           LoginOutputBoundary loginOutputBoundary, SearchPetDataAccessInterface petDataAccessObject, PetFactory petFactory) {
        this.userDataAccessObject = userDataAccessInterface;
        this.orgDAO = orgDAO;
        this.loginPresenter = loginOutputBoundary;
        this.petDataAccessObject = petDataAccessObject;
        this.petFactory = petFactory;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        Map<Integer, Pet> displayPetMap = new HashMap<>();
        Map<Integer, Pet> resultPetMap = petDataAccessObject.accessApi(new HashMap<>());
        for (int i = 0; i < 5; i++) {
            Map.Entry<Integer, Pet> petEntry = (Map.Entry<Integer, Pet>) resultPetMap.entrySet().toArray()[i];
            displayPetMap.put(petEntry.getKey(), petEntry.getValue());
            petDataAccessObject.save(petEntry.getValue());
        }

        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username) && !orgDAO.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else { //if the account exists:
            String pwd;
            if (userDataAccessObject.get(username) != null) {
                pwd = userDataAccessObject.get(username).getPassword();
            }
            else {
                pwd = orgDAO.get(username).getPassword();
            }
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else { // if the account exists and the password is correct:
                if (orgDAO.get(username) != null) {
                    Organizations org = orgDAO.get(username);
                    LoginOutputData loginOutputData = new LoginOutputData(org.getName(), "organization", false, null, org);
                    loginPresenter.prepareSuccessView(loginOutputData);

                } else {
                    User user = userDataAccessObject.get(loginInputData.getUsername());
                    LoginOutputData loginOutputData = new LoginOutputData(user.getName(), "user", false, displayPetMap, user);
                    loginPresenter.prepareSuccessView(loginOutputData);

                }
            }

        }
    }
}
