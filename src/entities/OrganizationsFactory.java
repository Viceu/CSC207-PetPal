package entities;

import java.util.Map;

public class OrganizationsFactory implements UserFactory {
    /**
     * Requires:
     * @param name
     * @param password
     * @return
     */
    @Override
    public User create(String name, String password, String bio) {
        return new Organizations(name, password, bio);
    }
}
