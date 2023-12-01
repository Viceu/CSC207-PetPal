package use_case.adopt_user_preview;
import entities.Organizations;

public interface AdoptUserPreviewDataAccessInterface {
    boolean existsByName(String identifier);
    void save(Organizations organizations);
    Organizations getUsername(String username);

    Organizations getPassword(String password);

    Organizations getBio(String bio);
}
