package use_case.search;

import entities.Pet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SearchPetDataAccessInterface {
    void save(Pet pet);

    Pet getPet(Integer id);

    boolean existsByName(Integer id);

    Map<Integer, Pet> accessApi(Map<String, String> params);

    void deleteAll();

    List<Pet> getPets();

    List<Integer> getIDs();
}