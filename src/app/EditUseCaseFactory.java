package app;

import entities.CommonPetFactory;
import entities.PetFactory;
import interface_adaptor.ViewManagerModel;
import interface_adaptor.edit.EditController;
import interface_adaptor.edit.EditPresenter;
import interface_adaptor.edit.EditViewModel;
import interface_adaptor.home.HomeViewModel;
import use_case.edit.*;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.EditView;
import view.LoginView;
import view.ViewManager;

import javax.swing.*;
import java.io.IOException;

public class EditUseCaseFactory {
    private EditUseCaseFactory() {
    }

    public static EditView create(ViewManagerModel viewManagerModel,
                                  EditViewModel editViewModel,
                                  HomeViewModel homeViewModel,
                                  EditPetDataAccessInterface petDataAccessObject) {
        try {
            EditController editController = createEditUseCase(viewManagerModel, editViewModel,
                    homeViewModel, petDataAccessObject);
            return new EditView(petDataAccessObject, editViewModel, editController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;

    }

    private static EditController createEditUseCase(ViewManagerModel viewManagerModel,
                                                    EditViewModel editViewModel,
                                                    HomeViewModel homeViewModel,
                                                    EditPetDataAccessInterface petDataAccessObject) {

        EditOutputBoundary editOutputBoundary = new EditPresenter(viewManagerModel, editViewModel, homeViewModel);
        PetFactory petFactory = new CommonPetFactory();
        EditInputBoundary editInteractor = new EditInteractor(petDataAccessObject, editOutputBoundary, petFactory);

        return new EditController(editInteractor);
    }
}