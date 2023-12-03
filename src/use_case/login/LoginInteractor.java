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
        if (!userDataAccessObject.existsByName(username) && !orgDAO.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else { //if the account exists:
            String pwd;
            if (userDataAccessObject.get(username) != null) {
                pwd = userDataAccessObject.get(username).getPassword();
            }
            else {
                pwd = "1234";
            }
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else { // if the account exists and the password is correct:
                User user = userDataAccessObject.get(loginInputData.getUsername());
                // checks if the username is an organizationID
                String regex = "[A-Z][A-Z][0-9]+";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(username);
                if (m.matches()) {
                    Organizations org = orgDAO.getUsername(username);
                    LoginOutputData loginOutputData = new LoginOutputData(org.getName(), "organization", false, null, org);
                    loginPresenter.prepareSuccessView(loginOutputData);

                } else {
                    LoginOutputData loginOutputData = new LoginOutputData(user.getName(), "user", false, displayPetMap, user);
                    loginPresenter.prepareSuccessView(loginOutputData);

                }
            }

        }
    }
}
