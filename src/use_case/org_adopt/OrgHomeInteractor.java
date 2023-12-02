package use_case.org_adopt;

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

        if (request == null) {
            orgHomePresenter.prepareFailView("There are no requests to view right now");
        } else {
            if (optionChosen == JOptionPane.YES_OPTION) {

            }
            else if (optionChosen == JOptionPane.NO_OPTION) {

            }
            else {
                // return to the login screen
            }

            OrgHomeOutputData orgHomeOutputData = new OrgHomeOutputData( request,false);
            orgHomePresenter.prepareSuccessView(orgHomeOutputData);
        }
    }
}
