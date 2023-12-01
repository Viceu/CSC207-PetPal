package use_case.edit;

import entities.Pet;
import entities.PetFactory;
import entities.User;

public interface EditPetDataAccessInterface {
    boolean existsByName(String identifier);
    // identifier = pet name
    Pet getPet(String petname);

    void addPet(Pet pet, PetFactory petFactory, String name, String bio, String owner);

    void save(Pet pet);
}
