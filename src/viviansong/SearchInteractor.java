package viviansong;

import data_access.ApiPetDataAccessObject;
import data_access.SearchPetDataAccessInterface;
import entities.Pet;

import java.util.*;

public class SearchInteractor implements SearchInputBoundary {
    final SearchPetDataAccessInterface userDataAccessObject;
    final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchPetDataAccessInterface userDataAccessInterface,
                            SearchOutputBoundary searchOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.searchPresenter = searchOutputBoundary;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        Map<String, String> requirements = searchInputData.getRequirements();

        // getting pet objects
        List<Pet> masterPetList = new ArrayList<>(userDataAccessObject.getPets());
        Map<Integer, Pet> displayPetMap = new HashMap<>();
        for (Pet pet : masterPetList) {
            if (!pet.getAdoptable()) {
                continue;
            } else if (masterPetList.contains(pet)) {
                continue;
            }
            for (Map.Entry<String, String> req : requirements.entrySet()) {
                if ((req.equals("name")) && !(pet.getName().equals(req.getValue()))) {
                    masterPetList.remove(pet);
                } else if ((req.equals("colors")) && !(pet.getColors().equals(req.getValue()))) {
                    masterPetList.remove(pet);
                } else if ((req.equals("breed")) && !(pet.getBreed().equals(req.getValue()))) {
                    masterPetList.remove(pet);
                } else if ((req.equals("species")) && !(pet.getSpecies().equals(req.getValue()))) {
                    masterPetList.remove(pet);
                } else if ((req.equals("coat")) && !(pet.getCoat().equals(req.getValue()))) {
                    masterPetList.remove(pet);
                } else if ((req.equals("age")) && !(pet.getAge().equals(req.getValue()))) {
                    masterPetList.remove(pet);
                } else if ((req.equals("attributes")) && !(pet.getAttributes().equals(req.getValue()))) {
                    masterPetList.remove(pet);
                } else if ((req.equals("environment")) && !(pet.getEnvironment().equals(req.getValue()))) {
                    masterPetList.remove(pet);
                } else if ((req.equals("description")) && !(pet.getDescription().equals(req.getValue()))) {
                    masterPetList.remove(pet);
                } else if ((req.equals("contact")) && !(pet.getContact().equals(req.getValue()))) {
                    masterPetList.remove(pet);
                } else if ((req.equals("gender")) && !(pet.getGender().equals(req.getValue()))) {
                    masterPetList.remove(pet);
                } else if ((req.equals("size")) && !(pet.getSize().equals(req.getValue()))) {
                    masterPetList.remove(pet);
                }
            }
        }
        // putting pet and id in hashmap
        for (Pet pet1 : masterPetList) {
            displayPetMap.put(pet1.getPetID(), pet1);
        }

        if (masterPetList.isEmpty()) {
            searchPresenter.prepareFailView("No search results.");
        } else {
            SearchOutputData searchOutputData = new SearchOutputData(displayPetMap, true);
            searchPresenter.prepareSuccessView(searchOutputData);
        }
    }
}

