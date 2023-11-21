package interface_adaptor.display;

import entities.Pet;
import use_case.display.DisplayInputBoundary;
import use_case.display.DisplayInputData;

import java.util.Map;

public class DisplayController {
    final DisplayInputBoundary displayUseCaseInteractor;
    public DisplayController(DisplayInputBoundary displayUseCaseInteractor) {
        this.displayUseCaseInteractor = displayUseCaseInteractor;
    }

    public void execute(Pet thisPet) {
        DisplayInputData displayInputData = new DisplayInputData(thisPet);

        displayUseCaseInteractor.execute(displayInputData);
    }
}
