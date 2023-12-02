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
    public Pet getPet(String petname) {
        return pets.get(petname);
    }

    @Override
    public Map<String, Pet> getPetsbyOwner(String owner) {
        Map<String, Pet> allPets = new HashMap<>();
        for (Pet pet: pets.values()){
            if (owner == pet.getOwner()){
                allPets.put(pet.getName(), pet);
            } else {
                // If the pet's owner does not match the input owner, do nothing
            }
        }
        return allPets;
    }

    @Override
    public void addPet(String name, String bio, String owner) throws IOException {
        Pet new_pet = petFactory.create(null, null, null, name, null, null,
                null, null, null, null, null, null, false,
                null, null, null, bio, owner);

        pets.put(name, new_pet);
    }


    @Override
    public void save(Pet pet) {

    }
}
