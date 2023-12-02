package use_case.home;
import entities.Pet;
import entities.PetFactory;
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
        String nextView = homeInputData.getNextView();

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
        HomeOutputData homeOutputData = new HomeOutputData(nextView, displayPetMap, false);
        homePresenter.prepareSuccessView(homeOutputData);

    }
}
