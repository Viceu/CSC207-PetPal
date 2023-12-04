package use_case.search;

import entities.Pet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SearchPetDataAccessInterface {
    void save(Pet pet);

    Map<Integer, Pet> accessApi(Map<String, String> params);
}