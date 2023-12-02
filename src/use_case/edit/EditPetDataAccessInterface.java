package use_case.edit;

import entities.Pet;
import entities.PetFactory;
import entities.User;

import java.io.IOException;
import java.util.Map;

public interface EditPetDataAccessInterface {
    boolean existsByName(String identifier);
    // identifier = pet name
    Pet getPet(String petname);
    Map<String, Pet> getPetsbyOwner(String owner)throws IOException;

    void addPet(String name, String bio, String owner) throws IOException;

    void save(Pet pet);
}
