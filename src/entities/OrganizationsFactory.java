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
    public User create(String name, String password, Map<String, Pet> pets, String bio) {
        return new Organizations(name, password, pets, bio);
    }
}
