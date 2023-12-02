package entities;

import java.time.LocalDateTime;

public class PersonalUser implements User{

    private final String name;
    private String password;
    private String bio;
    private LocalDateTime ltd;
    /**
     * Requires: password is valid.
     *
     * @param name
     * @param password
     */

    public PersonalUser(String name, String password, String bio, LocalDateTime ltd) {
        this.name = name;
        this.password = password;
        this.ltd = ltd;
        this.bio = bio;
    }

    @Override
    public String getName() {
        return name;
    }
    // username works as an identifier to user, so it needs to be unique

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

    @Override
    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return ltd;
    }

}

