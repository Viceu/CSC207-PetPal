package data_access;

import entities.Pet;
import entities.PetFactory;
import use_case.edit.EditPetDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class EditInMemoryPetDataAccessObject implements EditPetDataAccessInterface {
    private final Map<String, Pet> pets = new HashMap<>();


    @Override
    public boolean existsByName(String identifier) {
        return pets.containsKey(identifier);
    }

    @Override
    public Pet getPet(String petname) {
        return pets.get(petname);
    }

    @Override
    public void addPet(Pet pet, PetFactory petFactory, String name, String bio, String owner) {
        // this pet is new to system

        Pet new_pet = petFactory.create(null, null, null, name, null, null,
                null, null, null, null, null, null, false,
                null, null, null, bio, owner);

        pets.put(name, new_pet);
    }

    @Override
    public void save(Pet pet) {

    }
}
