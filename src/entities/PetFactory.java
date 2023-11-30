package entities;

import java.util.List;
import java.util.Map;

public interface PetFactory {
        Pet create(Integer petID, String organizationID, String URL, String name, List<String> colors,
                   Map<String, String> breed, String species, String coat, String age, Map<String,
                Boolean> attributes, Map<String, Boolean> environment, String description, Boolean adoptable,
                   Map<String, String> contact, String gender, String size, String bio, String owner);
}
