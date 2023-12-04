package use_case.edit;

import entities.Pet;
import entities.PetFactory;
import entities.User;

import java.io.IOException;
import java.util.Map;

public interface EditPetDataAccessInterface {
    boolean existsByName(String identifier);
    // identifier = pet name

    void save(Pet pet);
}
