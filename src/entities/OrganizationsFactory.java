package entities;

import java.time.LocalDateTime;

public class OrganizationsFactory implements UserFactory {
    /**
     * Requires:
     * @param name
     * @param password
     * @return
     */
    @Override
    public Organizations create(String name, String password, String bio, LocalDateTime date) {
        return new Organizations(name, password, bio);
    }
}
