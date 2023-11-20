package entities;

import java.util.Map;

abstract class User {

    private final String Name;

    private final Map<String, Pet> Pets;

    private final String Bio;

    public User(String name, Map<String, Pet> pets, String bio) {
        Name = name;
        Pets = pets;
        Bio = bio;
    }

    public void add_pets(Pet pet){
        Pets.put(pet.getName(), pet);
    }

}
