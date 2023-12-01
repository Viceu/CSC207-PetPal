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
    final PetFactory petFactory;


    public HomeInteractor(SearchPetDataAccessInterface petDataAccessInterface,
                            HomeOutputBoundary homeOutputBoundary,
                          PetFactory petFactory) {
        this.petDataAccessObject = petDataAccessInterface;
        this.homePresenter = homeOutputBoundary;
        this.petFactory = petFactory;
    }

    @Override
    public void execute(HomeInputData homeInputData) {
        Map<Integer, Pet> displayPetMap = new HashMap<>();
        Map<Integer, Pet> resultPetMap = petDataAccessObject.accessApi(null);

        for (Integer id : resultPetMap.keySet()) {
            Pet pet = resultPetMap.get(id);
            if (!pet.getAdoptable()) {
                continue;
            }
            // only add first 5 results to display
            if(displayPetMap.size() < 5) {
                displayPetMap.put(id, pet);
                petDataAccessObject.save(pet);
            }
        }

        if (displayPetMap.isEmpty()) {
            homePresenter.prepareFailView("There are no pets to adopt right now, please check back later.");
        } else {
            HomeOutputData homeOutputData = new HomeOutputData(displayPetMap, false);
            homePresenter.prepareSuccessView(homeOutputData);
        }

    }
}
