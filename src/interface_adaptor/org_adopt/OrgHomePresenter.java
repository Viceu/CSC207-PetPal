package interface_adaptor.org_adopt;

import interface_adaptor.ViewManagerModel;
import interface_adaptor.login.LoginState;
import interface_adaptor.login.LoginViewModel;
import use_case.org_adopt.OrgHomeOutputBoundary;
import use_case.org_adopt.OrgHomeOutputData;

public class OrgHomePresenter implements OrgHomeOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final OrgHomeViewModel orgViewModel;
    private final LoginViewModel loginViewModel;

    public OrgHomePresenter(ViewManagerModel viewManagerModel, OrgHomeViewModel orgViewModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.orgViewModel = orgViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(OrgHomeOutputData result) {
        if (result.getViewName() == "log in") {
            this.viewManagerModel.setActiveView(loginViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
        else {
            OrgHomeState orgHomeState = orgViewModel.getState();
            this.orgViewModel.setState(orgHomeState);
            this.orgViewModel.firePropertyChanged();

            this.viewManagerModel.setActiveView(orgViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
    }
    @Override
    public void prepareFailView(String failMessage) {
        OrgHomeState orgState = orgViewModel.getState();
        orgState.setOrgHomeFailMessage(failMessage);
        orgViewModel.firePropertyChanged();
    }
}
