package entities;

import java.util.Map;

class PersonalUser implements User{

    private final String Name;
    private final String password;
    private final Map<String, Pet> Pets;
    private final String Bio;
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */

    public PersonalUser(String name, String password, Map<String, Pet> pets, String bio) {
        Name = name;
        this.password = password;
        Pets = pets;
        Bio = bio;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Map<String, Pet> getPets() {
        return Pets;
    }

    @Override
    public void add_pets(Pet pet) {
        Pets.put(pet.getName(), pet);
    }

    @Override
    public String getBio() {
        return Bio;
    }
}
