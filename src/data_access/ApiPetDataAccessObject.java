package data_access;

import entity.Pet;
import entity.PetFactory;
import use_case.SearchPetDataAccessInterface;

import java.io.*;
import java.util.*;

public class ApiPetDataAccessObject implements SearchPetDataAccessInterface {

    private final File csvFile; // get api access instead of file name later

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<Integer, Pet> profiles = new HashMap<>();

    private PetFactory petFactory;

    public ApiPetDataAccessObject(String csvPath, PetFactory petFactory) throws IOException {
        this.petFactory = petFactory;

        //response should also include a header containing a status code (200 for OK; others to indicate errors)
        // need read status code, make sure 200, then procede to read repsonse array
        csvFile = new File(csvPath);
        headers.put("id", 0);
        headers.put("organization_id", 1);
        headers.put("url", 2);
        headers.put("type", 3);
        headers.put("species", 4);
        headers.put("breeds", 5);
        headers.put("colors", 6);
        headers.put("age", 7);
        headers.put("gender", 8);
        headers.put("size", 9);
        headers.put("coat", 10);
        headers.put("attributes", 11);
        headers.put("environment", 12);
        headers.put("tags", 13);
        headers.put("name", 14);
        headers.put("description", 15);
        headers.put("photos", 16);
        headers.put("videos", 17);
        headers.put("status", 18);
        headers.put("published_at", 19);
        headers.put("contact", 20);
        headers.put("_links", 21);


        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("id,organization_id,url,type,species,breeds,colors,age,gender," +
                        "size,coat,attributes,environment,tags,name,description,photos,videos,status," +
                        "published_at,contact,_links");

                https://www.geeksforgeeks.org/how-to-convert-json-array-to-string-array-in-java/
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    //id,organization_id,url,type,species,breeds,colors,age,gender,size,coat,attributes,
                    // environment,tags,name,description,photos,videos,status,published_at,contact,_links
                    Integer petID = Integer.valueOf(String.valueOf(col[headers.get("id")]));
                    String organizationID = String.valueOf(col[headers.get("organization_id")]);
                    String profileURL = String.valueOf(col[headers.get("url")]);
                    String species = String.valueOf(col[headers.get("species")]);
                    String breedsRow = String.valueOf(col[headers.get("breeds")]);
                    String[] breed = breedsRow.split(",").split(": ");
                    Map<String, String> breeds = new HashMap<>();
                    for(String[] a: breed)
                    String colors = String.valueOf(col[headers.get("colors")]);
                    // make List<String>
                    String age = String.valueOf(col[headers.get("age")]);
                    String gender = String.valueOf(col[headers.get("gender")]);
                    String size = String.valueOf(col[headers.get("size")]);
                    String coat = String.valueOf(col[headers.get("coat")]);
                    // make List<String>
                    String attributes = String.valueOf(col[headers.get("attributes")]);
                    // make Map<String, Boolean>
                    String environment = String.valueOf(col[headers.get("environment")]);
                    // Map<String, Boolean>
                    String name = String.valueOf(col[headers.get("name")]);
                    String description = String.valueOf(col[headers.get("description")]);
                    String adoptable = String.valueOf(col[headers.get("status")]);
                    // Boolean
                    String contact = String.valueOf(col[headers.get("contact")]);
                    // Map<String, String>
                    Pet pet = petFactory.create(Integer petID, String organizationID, String profileURL, String name, List<String> colors,
                            Map<String, String> breed, String species, List<String> coat, String age, Map<String,
                            Boolean> attributes, Map<String, Boolean> environment, String description, Boolean adoptable,
                            Map<String, String> contact, String gender, String size);

                    profiles.put(username, user);
                }
            }
        }
    }

    @Override
    public void save(Pet pet) {
        profiles.put(pet.getName(), pet);
        this.save();
    }

    @Override
    public Pet get(Integer id) {
        return profiles.get(id);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Pet pet : profiles.values()) {
                String line = String.format("%s,%s,%s",
                        pet.getName(), pet.getPassword(), pet.getCreationTime());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(Integer id) {
        return profiles.containsKey(id);
    }

    @Override
    public List<Integer> getUsers() {
        return new ArrayList<Integer>(profiles.keySet());
    }

    @Override
    public void deleteAll() {
        profiles.clear();
        this.save();
    }

    @Override
    public Pet retrieve(Integer id) {
        //Precondition already ran existsByName!!!!!!!
        return profiles.get(id);
    }

}
