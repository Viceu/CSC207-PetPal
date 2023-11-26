package entities;

import java.util.List;
import java.util.Map;

public class CommonPetFactory implements PetFactory{
    public Pet create(Integer petID, String organizationID, String URL, String name, List<String> colors,
                      Map<String, String> breed, String species, String coat, String age, Map<String,
            Boolean> attributes, Map<String, Boolean> environment, String description, Boolean adoptable,
                      Map<String, String> contact, String gender, String size){
        return new CommonPet(petID, organizationID, URL, name, colors, breed, species, coat, age, attributes,
                environment, description, adoptable, contact, gender, size);
    }
}
