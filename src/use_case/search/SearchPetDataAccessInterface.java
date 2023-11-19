package use_case.search;

import entities.Pet;

import java.util.List;

public interface SearchPetDataAccessInterface {
    void save(Pet pet);

    Pet getPet(Integer id);

    void accessApi();

    boolean existsByName(Integer id);

    void deleteAll();

    List<Pet> getPets();

    List<Integer> getIDs();
}