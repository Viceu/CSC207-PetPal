package use_case.adopt_user_preview;

import data_access.FileOrganizationsDataAccessObject;
import entities.*;
import use_case.display.DisplayInputData;
import use_case.display.DisplayOutputBoundary;
import use_case.display.DisplayOutputData;
import use_case.search.SearchPetDataAccessInterface;

public class AdoptUserPreviewInteractor implements AdoptUserPreviewInputBoundary {
    final AdoptUserPreviewOutputBoundary adoptUserPreviewPresenter;
    // TODO: final AdoptUserPreviewDataAccessInterface userDataAccessObject;
    final OrganizationsFactory orgFactory;


    public AdoptUserPreviewInteractor(AdoptUserPreviewOutputBoundary adoptUserPreviewOutputBoundary /*, AdoptUserPreviewDataAccessInterface userDAO,*/, OrganizationsFactory orgFactory) {
        this.adoptUserPreviewPresenter = adoptUserPreviewOutputBoundary;
        //this.userDataAccessObject = userDAO;
        this.orgFactory = orgFactory;
    }

    @Override
    public void execute(AdoptUserPreviewInputData adoptUserPreviewInputData) {
        Pet thisPet = adoptUserPreviewInputData.getPet();
        String userMessage = adoptUserPreviewInputData.getUserMessage();

        if (!thisPet.getAdoptable()) {
            adoptUserPreviewPresenter.prepareFailView("This pet is no longer adoptable.");
        } else {
            String orgId = thisPet.getOrganizationID();

            // Assumign we have the dao

            FileOrganizationsDataAccessObject orgData = new FileOrganizationsDataAccessObject();
            if (orgData.existsByName(orgId)) {
                Organizations petOrg = orgData.getUsername(orgId);
                Requests newRequest = new Requests(thisPet, /*TODO: check if this is how Cailyn is implementing it and if I can actually use this to get the user that's currently logged in*/ userDAO.getUser(), userMessage, petOrg);
                petOrg.addRequest(newRequest);
                // If the user also has one of these, add later
            }
            else {
                // use the dao here, placeholders for now
                Organizations petOrg = orgFactory.create(orgId, "1234", "We love pets!");
                orgData.save(petOrg);
                Requests newRequest = new Requests(thisPet, /*TODO: check if this is how Cailyn is implementing it and if I can actually use this to get the user that's currently logged in*/ userDAO.getUser(), userMessage, petOrg);
                petOrg.addRequest(newRequest);
            }

            AdoptUserPreviewOutputData adoptUserPreviewOutputData = new AdoptUserPreviewOutputData(thisPet, false);
            adoptUserPreviewPresenter.prepareSuccessView(adoptUserPreviewOutputData);
        }
    }
}
