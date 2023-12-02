package use_case.adopt_user_preview;

import data_access.EditInMemoryUserDataAccessObject;
import data_access.FileOrganizationsDataAccessObject;
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


    public AdoptUserPreviewInteractor(AdoptUserPreviewOutputBoundary adoptUserPreviewOutputBoundary, AdoptUserPreviewDataAccessInterface userDAO, OrganizationsFactory orgFactory) {
        this.adoptUserPreviewPresenter = adoptUserPreviewOutputBoundary;
        this.userDataAccessObject = userDAO;
        this.orgFactory = orgFactory;
    }

    @Override
    public void execute(AdoptUserPreviewInputData adoptUserPreviewInputData) {
        Pet thisPet = adoptUserPreviewInputData.getPet();
        String userMessage = adoptUserPreviewInputData.getUserMessage();
        String username = adoptUserPreviewInputData.getUsername();

        if (!thisPet.getAdoptable()) {
            adoptUserPreviewPresenter.prepareFailView("This pet is no longer adoptable.");
        } else {
            String orgId = thisPet.getOrganizationID();

            // Assumign we have the dao

            if (userDataAccessObject.existsByName(orgId)) {
                Organizations petOrg = userDataAccessObject.getUsername(orgId);
                EditInMemoryUserDataAccessObject newUser = new EditInMemoryUserDataAccessObject();
                Requests newRequest = new Requests(thisPet, newUser.getUser(username), userMessage, petOrg);
                petOrg.addRequest(newRequest);
                // If the user also has one of these, add later
            }
            else {
                // use the dao here, placeholders for now
                Organizations petOrg = orgFactory.create(orgId, "1234", "We love pets!", null);
                userDataAccessObject.save(petOrg);
                EditInMemoryUserDataAccessObject newUser = new EditInMemoryUserDataAccessObject();
                Requests newRequest = new Requests(thisPet, newUser.getUser(username), userMessage, petOrg);
                petOrg.addRequest(newRequest);
            }

            AdoptUserPreviewOutputData adoptUserPreviewOutputData = new AdoptUserPreviewOutputData(thisPet, false);
            adoptUserPreviewPresenter.prepareSuccessView(adoptUserPreviewOutputData);
        }
    }
}
