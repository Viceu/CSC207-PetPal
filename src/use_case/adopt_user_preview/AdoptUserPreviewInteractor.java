package use_case.adopt_user_preview;

import entities.*;
import entities.Organizations;
import entities.Pet;
import entities.Requests;
import use_case.display.DisplayInputData;
import use_case.display.DisplayOutputBoundary;
import use_case.display.DisplayOutputData;
import use_case.search.SearchPetDataAccessInterface;

public class AdoptUserPreviewInteractor implements AdoptUserPreviewInputBoundary {
    final AdoptUserPreviewOutputBoundary adoptUserPreviewPresenter;
    final AdoptUserPreviewDataAccessInterface userDataAccessObject;
    final OrganizationsFactory orgFactory;


    public AdoptUserPreviewInteractor(AdoptUserPreviewOutputBoundary adoptUserPreviewOutputBoundary, AdoptUserPreviewDataAccessInterface userDAO, OrganizationFactory orgFactory) {
        this.adoptUserPreviewPresenter = adoptUserPreviewOutputBoundary;
        this.userDataAccessObject = userDAO;
        this.orgFactory = orgFactory;
    }

    @Override
    public void execute(AdoptUserPreviewInputData adoptUserPreviewInputData) {
        Pet thisPet = adoptUserPreviewInputData.getPet();
        String userMessage = adoptUserPreviewInputData.getUserMessage();

        if (!thisPet.getAdoptable()) {
            adoptUserPreviewPresenter.prepareFailView("This pet is no longer adoptable.");
        } else {
            Organizations petOrg = userDataAccessObject.getOrg(thisPet);
            if (userDataAccessObject.getOrg(thisPet) == null) {
                Organizations newOrg = orgFactory.create(AdoptUserPreviewInputData.getID());
                userDataAccessObject.save(newOrg);
            }

            UserDataAcccessInterface userDAO = new UserDataAccessInterface();

            Requests newRequest = new Requests(thisPet, /*TODO: check if this is how Cailyn is implementing it and if I can actually use this to get the user that's currently logged in*/ userDAO.getUser(), userMessage, petOrg);
            petOrg.addRequest(newRequest);

            AdoptUserPreviewOutputData adoptUserPreviewOutputData = new AdoptUserPreviewOutputData(thisPet, false);
            adoptUserPreviewPresenter.prepareSuccessView(adoptUserPreviewOutputData);
        }
    }
}
