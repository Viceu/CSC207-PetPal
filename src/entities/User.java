package entities;

import java.util.Map;

public interface User {
    String getName();
    String getPassword();
    String getBio();
    Map<String, Pet> getPets();

    public void add_pets(Pet pet);
}
