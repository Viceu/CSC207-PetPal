import entity.Pet;

public interface SearchPetDataAccessInterface {
    void accessApi();

    boolean existsByName(String identifier);

    void save(Pet pet);

    void deleteAll();
}
