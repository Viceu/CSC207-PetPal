package use_case.display;

import entities.Pet;
import use_case.search.*;

import java.util.*;

public class DisplayInteractor implements DisplayInputBoundary {
    final DisplayOutputBoundary displayPresenter;

    public DisplayInteractor(DisplayOutputBoundary displayOutputBoundary) {
        this.displayPresenter = displayOutputBoundary;
    }

    @Override
    public void execute(DisplayInputData displayInputData) {
        Pet thisPet = displayInputData.getPet();

        if (!thisPet.getAdoptable()) {
            displayPresenter.prepareFailView("This pet is no longer adoptable.");
        } else {
            DisplayOutputData displayOutputData = new DisplayOutputData(thisPet, false);
            displayPresenter.prepareSuccessView(displayOutputData);
        }
    }
}
