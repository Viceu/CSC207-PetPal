package entities;

import java.time.LocalDateTime;
import java.util.Map;

public interface User {
    String getName();
    String getPassword();
    void setPassword(String password);
    String getBio();
    void setBio(String bio);
    Map<String, Pet> getPets();
    String getPetsNames();
    void add_pets(Pet pet);

    LocalDateTime getCreationTime();
}
