package viviansong;

import entities.Pet;

import java.util.*;

public class SearchInteractor implements SearchInputBoundary {
    final SearchUserDataAccessInterface userDataAccessObject;
    final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchUserDataAccessInterface userDataAccessInterface,
                            SearchOutputBoundary searchOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.searchPresenter = searchOutputBoundary;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        Map<String, String> requirements = searchInputData.getRequirements();

        // getting pet objects
        List<Pet> masterPetList = new ArrayList<>(userDataAccessObject.getPets);
        Map<Integer, Pet> displaypetList;
        for (Pet pet : masterPetList) {
            if (!pet.getAdoptable()) {
                continue;
            } else if (masterPetList.contains(pet)) {
                continue;
            }
            for (String req : requirements) {
                if (pet.req != req.getValue) {
                    masterPetList.remove(pet);
                }
            }
        }

        // getting ids

        if (masterPetList.isEmpty()) {
            searchPresenter.prepareFailView("No search results.");
        } else {
            SearchOutputData searchOutputData = new SearchOutputData(..., true);
            searchPresenter.prepareSuccessView(searchOutputData);
        }


        /* PSEUDO-CODE:
        List<Pet> masterPetList = userDataAccessObject.getPets.copy();
        Map<Integer, Pet> displayPetList;
        for pet in masterPetList:
            if !pet.getAdoptable():
                pass;
            if pet in masterPetList.values():
                pass;
            for req in requirements:
                if pet.req != req.value:
                    masterPetList.remove(pet);
        if masterPetList.empty():
            searchPresenter.prepareFailView("No search results");
        else:
            # NOTE - create User entity?
            User user = userDataAccessObject.get(searchInputData.getUsername());
            SearchOutputData searchOutputData = new SearchOutputData()
            searchPresenter.prepareSuccessView(searchOutputData);

            # --- From CACoding:
            LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
            loginPresenter.prepareSuccessView(loginOutputData);
        * */

    }


}
