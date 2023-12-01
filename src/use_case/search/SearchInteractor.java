package use_case.search;

import app.SearchUseCaseFactory;
import data_access.ApiPetDataAccessObject;
import entities.CommonPetFactory;
import entities.Pet;
import entities.PetFactory;

import java.util.*;

public class SearchInteractor implements SearchInputBoundary {
    final SearchPetDataAccessInterface userDataAccessObject;
    final SearchOutputBoundary searchPresenter;

    final PetFactory petFactory;

    public SearchInteractor(SearchPetDataAccessInterface userDataAccessInterface,
                            SearchOutputBoundary searchOutputBoundary,
                            PetFactory petFactory) {
        this.userDataAccessObject = userDataAccessInterface;
        this.searchPresenter = searchOutputBoundary;
        this.petFactory = petFactory;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        Map<String, String> requirements = searchInputData.getRequirements();
        Map<Integer, Pet> displayPetMap = new HashMap<>();

        Map<Integer, Pet> resultPetMap = userDataAccessObject.accessApi(requirements);
        for (Integer id : resultPetMap.keySet()) {
            Pet pet = userDataAccessObject.getPet(id);
            if (!pet.getAdoptable()) {
                continue;
            }
            displayPetMap.put(id, pet);
            userDataAccessObject.save(pet);
        }

        if (displayPetMap.isEmpty()) {
            searchPresenter.prepareFailView("No search results.");
        } else {
            SearchOutputData searchOutputData = new SearchOutputData(displayPetMap, true);
            searchPresenter.prepareSuccessView(searchOutputData);

        }
    }
}