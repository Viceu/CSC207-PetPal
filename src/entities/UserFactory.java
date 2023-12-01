package entities;

import java.util.Map;

public interface UserFactory {
    /** Requires: password is valid. */
    User create(String name, String password, String bio);
}
