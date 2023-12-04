package data_access;

import entities.Pet;
import entities.PetFactory;
import use_case.edit.EditPetDataAccessInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditInMemoryPetDataAccessObject implements EditPetDataAccessInterface {
    private final Map<String, Pet> pets = new HashMap<>();
    // unique pet name to Pet
    private PetFactory petFactory;
    @Override
    public boolean existsByName(String identifier) {
        return pets.containsKey(identifier);
    }

    @Override
    public void save(Pet pet) {

    }
}
