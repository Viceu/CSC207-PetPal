package use_case.home;
import entities.Pet;
import entities.PetFactory;
import entities.User;
import use_case.search.SearchPetDataAccessInterface;

import java.util.*;


public class HomeInteractor implements HomeInputBoundary{
    final HomeOutputBoundary homePresenter;

    public HomeInteractor(HomeOutputBoundary homeOutputBoundary) {
        this.homePresenter = homeOutputBoundary;
    }

    @Override
    public void execute(HomeInputData homeInputData) {
//        Map<Integer, Pet> displayPetMap = new HashMap<>();
//        Map<Integer, Pet> resultPetMap = petDataAccessObject.accessApi(null);
//        String nextView = homeInputData.getNextView();
//
//        for (Integer id : resultPetMap.keySet()) {
//            Pet pet = resultPetMap.get(id);
//            if (!pet.getAdoptable()) {
//                continue;
//            }
//            // only add first 5 results to display
//            if(displayPetMap.size() < 5) {
//                displayPetMap.put(id, pet);
//                petDataAccessObject.save(pet);
//            }
//        }
//        HomeOutputData homeOutputData = new HomeOutputData(nextView, displayPetMap, false);
//        homePresenter.prepareSuccessView(homeOutputData);

        Pet thisPet = homeInputData.getPet();
        if (!thisPet.getAdoptable()) {
            homePresenter.prepareFailView("This pet is no longer adoptable, sorry.");
        } else {
            User user = homeInputData.getUser();
            String nextView = homeInputData.getNextView();
            HomeOutputData homeOutputData = new HomeOutputData(nextView, thisPet, user, false);
            homePresenter.prepareSuccessView(homeOutputData);
        }
    }
}
