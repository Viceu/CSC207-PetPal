package entities;

import java.time.LocalDateTime;
import java.util.Map;

class PersonalUser implements User{

    private final String name;
    private String password;
    private Map<String, Pet> pets; // unique pet names to pet objects
    private String bio;
    private LocalDateTime ltd;
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */

    public PersonalUser(String name, String password, String bio, LocalDateTime ltd) {
        this.name = name;
        this.password = password;
        this.ltd = ltd;
        bio = bio;
    }

    @Override
    public String getName() {
        return name;
    }
    // user name works as an identifier to user, so it needs to be unique

    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        bio = bio;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return ltd;
    }

}