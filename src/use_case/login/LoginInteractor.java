package use_case.login;

import entities.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
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
                    LoginOutputData loginOutputData = new LoginOutputData(user.getName(), "organization", false);
                    loginPresenter.prepareSuccessView(loginOutputData);

                } else {
                    LoginOutputData loginOutputData = new LoginOutputData(user.getName(), "user", false);
                    loginPresenter.prepareSuccessView(loginOutputData);

                }
            }

        }
    }
}
