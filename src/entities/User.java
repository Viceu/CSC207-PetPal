package entities;

import java.util.Map;

public interface User {
    String getName();
    String getPassword();
    void setPassword(String password);
    String getBio();
    void setBio(String bio);
}
