package app;

import entities.OrganizationsFactory;
import interface_adaptor.ViewManagerModel;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewController;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewPresenter;
import interface_adaptor.adopt_user_preview.AdoptUserPreviewViewModel;
import interface_adaptor.home.HomeViewModel;
import interface_adaptor.login.LoginViewModel;
import interface_adaptor.org_adopt.OrgHomeController;
import interface_adaptor.org_adopt.OrgHomePresenter;
import interface_adaptor.org_adopt.OrgHomeViewModel;
import use_case.adopt_user_preview.AdoptUserPreviewDataAccessInterface;
import use_case.adopt_user_preview.AdoptUserPreviewInputBoundary;
import use_case.adopt_user_preview.AdoptUserPreviewInteractor;
import use_case.adopt_user_preview.AdoptUserPreviewOutputBoundary;
import use_case.org_adopt.OrgHomeInputBoundary;
import use_case.org_adopt.OrgHomeInteractor;
import use_case.org_adopt.OrgHomeOutputBoundary;
import view.AdoptUserPreviewView;
import view.OrgHomeView;

import javax.swing.*;
import java.io.IOException;

public class OrgHomeUseCaseFactory {
    private OrgHomeUseCaseFactory() {}
    public static OrgHomeView create(
            ViewManagerModel viewManagerModel, OrgHomeViewModel orgHomeViewModel, LoginViewModel loginViewModel) {

        try {
            OrgHomeController orgHomeController = createOrgHomeUseCase(viewManagerModel, orgHomeViewModel, loginViewModel);
            return new OrgHomeView(orgHomeController, orgHomeViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not send adoption request.");
        }
        return null;
    }
    private static OrgHomeController createOrgHomeUseCase(ViewManagerModel viewManagerModel, OrgHomeViewModel orgHomeViewModel,
                                                          LoginViewModel loginViewModel) throws IOException {

        OrgHomeOutputBoundary orgHomeOutputBoundary = new OrgHomePresenter(viewManagerModel, orgHomeViewModel, loginViewModel);

        OrgHomeInputBoundary orgHomeInteractor = new OrgHomeInteractor(orgHomeOutputBoundary) {
        };

        return new OrgHomeController(orgHomeInteractor);
    }
}
