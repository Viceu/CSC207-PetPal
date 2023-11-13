package entities;

import java.util.List;
import java.util.Map;

public class Pet {
    private Integer petID;
    private String organizationID;
    private String URL;
    private String name;
    private List<String> colors;
    private Map<String, String> breed;
    private String species;
    private List<String> coat;
    private String age;
    private Map<String, Boolean> attributes;
    private Map<String, Boolean> environment;
    private String description;
    private Boolean adoptable;
    private Map<String, String> contact;
    private String gender;
    private String size;

    public Integer getPetID(){ return petID; }

    public String getOrganizationID(){ return organizationID; }

    public String getURL(){ return URL; }

    public String getName(){ return name; }

    public List<String> getColors(){ return colors; }

    public Map<String, String> getBreed() {return breed;}

    public String getSpecies(){ return species; }

    public List<String> getCoat(){ return coat; }

    public String getAge() { return age; }

    public Map<String, Boolean> getAttributes(){ return attributes; }

    public Map<String, Boolean> getEnvironment(){ return environment; }

    public String getDescription() {return description;}

    public Boolean getAdoptable() {return adoptable;}

    public Map<String, String> getContact() {return contact;}

    public String getGender() {return gender;}

    public String getSize() {return size;}
}
