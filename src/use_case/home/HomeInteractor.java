package use_case.home;
import entities.Pet;
import entities.PetFactory;
import use_case.search.SearchInputData;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;
import use_case.search.SearchPetDataAccessInterface;

import java.util.*;


public class HomeInteractor implements HomeInputBoundary{
    final SearchPetDataAccessInterface petDataAccessObject;
    final HomeOutputBoundary homePresenter;


    public HomeInteractor(SearchPetDataAccessInterface petDataAccessInterface,
                            HomeOutputBoundary homeOutputBoundary) {
        this.petDataAccessObject = petDataAccessInterface;
        this.homePresenter = homeOutputBoundary;
    }

    @Override
    public void execute(HomeInputData homeInputData) {

        List<Integer> petIDs = petDataAccessObject.getIDs();
        Map<Integer, Pet> displayPetMap = new HashMap<>();

        for (Integer id : petIDs) {
            Pet pet = petDataAccessObject.getPet(id);
            displayPetMap.put(id, pet);
        }

        if (displayPetMap.isEmpty()) {
            homePresenter.prepareFailView("There are no pets to adopt right now, please check back later.");
        } else {
            HomeOutputData homeOutputData = new HomeOutputData(displayPetMap, false);
            homePresenter.prepareSuccessView(homeOutputData);
        }

    }
}
