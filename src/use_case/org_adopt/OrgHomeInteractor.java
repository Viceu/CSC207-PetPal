package use_case.org_adopt;

import entities.Organizations;
import entities.Requests;
import use_case.home.HomeOutputData;

import javax.swing.*;

public class OrgHomeInteractor implements OrgHomeInputBoundary {
    private final OrgHomeOutputBoundary orgHomePresenter;

    public OrgHomeInteractor(OrgHomeOutputBoundary orgHomeOutputBoundary) {
        this.orgHomePresenter = orgHomeOutputBoundary;
    }
    @Override
    public void execute(OrgHomeInputData orgHomeInputData) {
        Integer optionChosen = orgHomeInputData.getChosenOption();
        Requests request = orgHomeInputData.getRequest();
        if (request == null && optionChosen != null) {
            orgHomePresenter.prepareFailView("There are no requests to view right now");
        } else {
            OrgHomeOutputData orgHomeOutputData = new OrgHomeOutputData(request, false);

            if (optionChosen == null) {
                orgHomeOutputData.setViewName("log in");
            }
            else {
                Organizations org = request.getOrganization();

                if (optionChosen == JOptionPane.YES_OPTION) {
                    org.acceptRequest(request, "We've accepted your adoption request :) !");
                    orgHomeOutputData.setViewName("org home");
                } else if (optionChosen == JOptionPane.NO_OPTION) {
                    org.rejectRequest(request, "We've decided to unfortunately reject your request :(");
                    orgHomeOutputData.setViewName("org home");
                }
            }

            orgHomePresenter.prepareSuccessView(orgHomeOutputData);
        }
    }
}
