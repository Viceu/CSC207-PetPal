package entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CommonPet implements Pet {
    private final Integer petID;
    private final String organizationID;
    private final String URL;
    private final String name;
    private final List<String> colors;
    private final Map<String, String> breed;
    private final String species;
    private final List<String> coat;
    private final String age;
    private final Map<String,Boolean> attributes;
    private final Map<String, Boolean> environment;
    private final String description;
    private final Boolean adoptable;
    private final Map<String, String> contact;
    private final String gender;
    private final String size;

    CommonPet(Integer petID, String organizationID, String URL, String name, List<String> colors,
              Map<String, String> breed, String species, List<String> coat, String age, Map<String,
            Boolean> attributes, Map<String, Boolean> environment, String description, Boolean adoptable,
              Map<String, String> contact, String gender, String size)
    {
        this.petID = petID;
        this.organizationID = organizationID;
        this.URL = URL;
        this.name = name;
        this.colors = colors;
        this.breed = breed;
        this.species = species;
        this.coat = coat;
        this.age = age;
        this.attributes = attributes;
        this.environment = environment;
        this.description = description;
        this.adoptable = adoptable;
        this.contact = contact;
        this.gender = gender;
        this.size = size;
    }

    public Integer getPetID(){
        return petID;
    }
    public String getOrganizationID(){
        return organizationID;
    }
    public String getURL() {
        return URL;
    }
    public String getName(){
        return name;
    }
    public List<String> getColors(){
        return colors;
    }
    public Map<String, String> getBreed(){
        return breed;
    }
    public String getSpecies(){
        return species;
    }
    public List<String> getCoat(){
        return coat;
    }
    public String getAge(){
        return age;
    }
    public Map<String, Boolean> getAttributes(){
        return attributes;
    }
    public Map<String, Boolean> getEnvironment(){
        return environment;
    }
    public String getDescription(){
        return description;
    }
    public Boolean getAdoptable(){
        return adoptable;
    }
    public Map<String, String> getContact(){
        return contact;
    }
    public String getGender(){
        return gender;
    }
    public String getSize(){
        return size;
    }
    public Map<String, String> getAll()
        // Return all attributes for a pet inside a hashmap
    {
        HashMap<String, String> all = new HashMap<String, String>();

        all.put("petID", getPetID().toString());
        all.put("organizationID", getOrganizationID());
        all.put("URL", getURL());
        all.put("name", getName());
        all.put("colors", convertList(getColors()));
        all.put("breed", convertMap(getBreed()));
        all.put("species", getSpecies());
        all.put("coat", convertList(getCoat()));
        all.put("age", getAge());
        all.put("attributes", convertMap(getAttributes()));
        all.put("environment", convertMap(getEnvironment()));
        all.put("description", getDescription());
        all.put("adoptable", getAdoptable().toString());
        all.put("contact", convertMap(getContact()));
        all.put("gender", getGender());
        all.put("size", getSize());

        return all;
    }

    private String convertList(List<String> list)
    {
        /* converts from a list to a string in the following format:
        Example:
        List: [Orange, white, black]
        String: "Orange, white, black"
        This is used for the following attributes: colors, coat
         */
        String string_list = list.toString();
        string_list = string_list.replace("[", "");
        string_list = string_list.replace("]", "");
        return string_list;
    }

    private String convertMap(Map map)
    {
        /*
        converts from a map to a string in the following format:
        Map:  {
                "primary": "Akita",
                "secondary": Shiba Inu,
                "mixed": false,
                "unknown": false
            },
        String: "primary: Akita, secondary: Shiba Inu, mixed: no, unknown: no"
        Used for the following attributes: breed, attribute, environment, contact
         */
        String string_map = map.toString();
        string_map = string_map.replace("{", "");
        string_map = string_map.replace("}", "");
        string_map = string_map.replace("True", "Yes");
        string_map = string_map.replace("False", "No");
        return string_map;
    }
}