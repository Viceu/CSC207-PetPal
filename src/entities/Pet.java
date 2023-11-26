package entities;

import java.util.List;
import java.util.Map;

public interface Pet {

    Integer getPetID();

    String getOrganizationID();

    String getURL();

    String getName();

    List<String> getColors();

    Map<String, String> getBreed();

    String getSpecies();

    String getCoat();

    String getAge();

    Map<String, Boolean> getAttributes();

    Map<String, Boolean> getEnvironment();

    String getDescription();

    Boolean getAdoptable();

    Map<String, String> getContact();

    String getGender();

    String getSize();

    Map<String, String> getAll();
}
