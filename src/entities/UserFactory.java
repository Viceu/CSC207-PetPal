package entities;

import java.time.LocalDateTime;
import java.util.Map;

public interface UserFactory {
    /** Requires: password is valid. */
    User create(String name, String password, Map<String, Pet> pets, String bio, LocalDateTime ltd);
}
