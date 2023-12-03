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
import use_case.signup.SignupUserDataAccessInterface;

import java.time.LocalDateTime;

public class AdoptUserPreviewInteractor implements AdoptUserPreviewInputBoundary {
    final AdoptUserPreviewOutputBoundary adoptUserPreviewPresenter;
    final AdoptUserPreviewDataAccessInterface orgDataAccessObject;
    final OrganizationsFactory orgFactory;


    public AdoptUserPreviewInteractor(AdoptUserPreviewOutputBoundary adoptUserPreviewOutputBoundary,
                                      AdoptUserPreviewDataAccessInterface orgDAO,
                                      OrganizationsFactory orgFactory) {
        this.adoptUserPreviewPresenter = adoptUserPreviewOutputBoundary;
        this.orgDataAccessObject = orgDAO;
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
            Organizations petOrg;

            if (orgDataAccessObject.existsByName(orgId)) {
                petOrg = orgDataAccessObject.getUsername(orgId);
                Requests newRequest = new Requests(thisPet, userMessage, petOrg);
                petOrg.addRequest(newRequest);
                // If the user also has one of these, add later
            }
            else {
                // use the dao here, placeholders for now
                petOrg = orgFactory.create(orgId, "1234", "We love pets!", LocalDateTime.now());
                orgDataAccessObject.save(petOrg);
                Requests newRequest = new Requests(thisPet, userMessage, petOrg);
                petOrg.addRequest(newRequest);
            }

            AdoptUserPreviewOutputData adoptUserPreviewOutputData = new AdoptUserPreviewOutputData(thisPet, false, petOrg);
            adoptUserPreviewPresenter.prepareSuccessView(adoptUserPreviewOutputData);
        }
    }
}
