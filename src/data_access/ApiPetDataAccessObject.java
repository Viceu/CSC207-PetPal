package data_access;

import entity.Pet;
import entity.PetFactory;
import use_case.SearchPetDataAccessInterface;

import java.io.*;
import java.util.*;

public class ApiPetDataAccessObject implements SearchPetDataAccessInterface {

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<Integer, Pet> profiles = new HashMap<>();

    private PetFactory petFactory;

    public ApiPetDataAccessObject(HashMap<String, String> params, PetFactory petFactory) throws IOException {

        List<Pet> listPets = new ArrayList<Pet>();
        this.petFactory = petFactory;

        String jsonString;
        JSONObject jsonObject;

        // Read API retrieved results (List of String)
        List<String> apiResults = ApiResults.getAnimals(params);

        for (String petInfo:apiResults) {

            // Construct a JSONObject using above string
            JSONObject petJson = new JSONObject(petInfo);

            myObj = JSON.parse(petJson);
            animals = myObj.animals[0];
            // animals is a JSONObject

            Integer petID = animals.id;
            String organizationID = animals.organization_id;
            String profileURL = animals.url;
            String species = animals.species;
            JSONObject breedsRow = animals.breeds;
            Map<String, String> breeds;
            for (int i = 0; i < breedsRow.length(); i++) {
                breeds.put(breedsRow.getString(i))

            }
            /**
             * https://www.geeksforgeeks.org/how-to-convert-json-array-to-string-array-in-java/
             * https://www.w3schools.com/js/js_json_arrays.asp
             * https://stackoverflow.com/questions/42726232/how-convert-jsonobject-to-arraylist
             */


            String colors = animals.colors;
            // make List<String>
            String age = animals.age;
            String gender = animals.gender;
            String size = animals.size;
            String coat = animals.coat;
            // make List<String>
            String attributes = animals.attributes;
            // make Map<String, Boolean>
            String environment = animals.environment;
            // Map<String, Boolean>
            String name = animals.name;
            String description = animals.description;
            String adoptable = animals.status;
            // Boolean
            String contact = animals.contact;
            // Map<String, String>
            Pet pet = petFactory.create(Integer petID, String organizationID, String profileURL, String name, List < String > colors,
                    Map < String, String > breed, String species, List < String > coat, String age, Map < String,
                    Boolean > attributes, Map < String, Boolean > environment, String description, Boolean adoptable,
                    Map < String, String > contact, String gender, String size);

            profiles.put(username, user);


            // 3: Fetching JSON Array test from JSON Object
            JSONArray docs
                    = jsonObject.getJSONArray("animals");

            List<String> exampleList = new ArrayList<String>();

            for (int i = 0; i < exampleArray.length; i++) {
                exampleList.add(exampleArray.getString(i));
            }

            if (exampleList.length() == 0) {
                save();
            } else {
                TODO//
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
                        pet.getPetID(), pet.getOrganizationID(), pet.getURL(), pet.getName(), pet.getColors(),
                        pet.getBreed(), pet.getSpecies(), pet.getCoat(), pet.getAge(), pet.getAttributes(),
                        pet.getEnvironment(), pet.getDescription(), pet.getAdoptable(), pet.getContact(),
                        pet.getGender(), pet.getSize());
                /** how would Integer and Map format
                + change getter if that changes (attributes and environments)
                **/
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByName(Integer id) {
        return profiles.containsKey(id);
    }

    @Override
    public void deleteAll() {
        profiles.clear();
        this.save();
    }

    @Override
    public List<Integer> getIDs() {
        return new ArrayList<Integer>(profiles.keySet());
        }

    @Override
    public List<Pet> getPets() {
        return profiles
    }

}
