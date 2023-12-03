package use_case.login;

import entities.Pet;
import entities.PetFactory;
import entities.User;
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

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary, SearchPetDataAccessInterface petDataAccessObject, PetFactory petFactory) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        this.petDataAccessObject = petDataAccessObject;
        this.petFactory = petFactory;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        Map<Integer, Pet> displayPetMap = new HashMap<>();
        Map<Integer, Pet> resultPetMap = petDataAccessObject.accessApi(new HashMap<>());
        for (Integer id : resultPetMap.keySet()) {
            Pet pet = resultPetMap.get(id);
            if (!pet.getAdoptable()) {
                continue;
            }
            displayPetMap.put(id, pet);
            petDataAccessObject.save(pet);
        }

        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else { //if the account exists:
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else { // if the account exists and the password is correct:

                User user = userDataAccessObject.get(loginInputData.getUsername());

                // checks if the username is an organizationID
                String regex = "[0-9]+";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(username);
                if (m.matches()) {
                    LoginOutputData loginOutputData = new LoginOutputData(user.getName(), "organization", false, null, user);
                    loginPresenter.prepareSuccessView(loginOutputData);

                } else {
                    LoginOutputData loginOutputData = new LoginOutputData(user.getName(), "user", false, displayPetMap, user);
                    loginPresenter.prepareSuccessView(loginOutputData);

                }
            }

        }
    }
}
