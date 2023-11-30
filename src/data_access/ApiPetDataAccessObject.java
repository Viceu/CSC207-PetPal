package data_access;

import entities.Pet;
import entities.PetFactory;
import api.ApiResults;

import java.io.*;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import use_case.search.SearchPetDataAccessInterface;

public class ApiPetDataAccessObject implements SearchPetDataAccessInterface {

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<Integer, Pet> profiles = new HashMap<>();

    private PetFactory petFactory;

    public ApiPetDataAccessObject(HashMap<String, String> params, String csvPath, PetFactory petFactory) throws IOException {
        this.petFactory = petFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("creation_time", 2);


        List<Pet> listPets = new ArrayList<Pet>();

        String jsonString;
        JSONObject jsonObject;

        // Read API retrieved results (List of String)
        List<String> apiResults = ApiResults.getAnimals(params);

        for (String petInfo:apiResults) {

            // Construct a JSONObject using above string for easier parsing
            JSONObject petJson = new JSONObject(petInfo);

            // store all needed data points for construction a Pet object
            Integer petID = Integer.valueOf(String.valueOf(petJson.get("id")));
            String organizationID = String.valueOf(petJson.get("organization_id"));
            String profileURL = String.valueOf(petJson.get("url"));
            String species = String.valueOf(petJson.get("species"));
            String age = String.valueOf(petJson.get("age"));
            String gender = String.valueOf(petJson.get("gender"));
            String size = String.valueOf(petJson.get("size"));
            String name = String.valueOf(petJson.get("name"));
            String description = String.valueOf(petJson.get("description"));
            String status = String.valueOf(petJson.get("status"));
            Boolean adoptable = status.equals("adoptable");
            String coat = String.valueOf(petJson.get("coat"));

            Map<String, String> colorsMap = toMapSS(petJson, "colors");
            List<String> colors = new ArrayList<>(colorsMap.values());
            Map<String, Boolean> attributes = toMapSB(petJson, "attributes");
            Map<String, Boolean> environment = toMapSB(petJson, "environment");
            JSONObject contactRow = (JSONObject) petJson.get("contact");
            Map<String, String> contact = new HashMap<>();
            if(contactRow.get("email")==JSONObject.NULL) {
                contact.put("email", null);
            }
            else {
                contact.put("email", (String) contactRow.get("email"));
            }
            if(contactRow.get("phone")==JSONObject.NULL) {
                contact.put("phone", null);
            }
            else {
                contact.put("phone", (String) contactRow.get("phone"));
            }

            JSONObject BreedsRow = (JSONObject) petJson.get("breeds");
            Map<String, String> breed = new HashMap<>();
            for (int i = 0; i < BreedsRow.names().length (); i++) {
                String key = BreedsRow.names().getString (i);
                if(BreedsRow.get(key)==JSONObject.NULL) {
                    breed.put(key, null);
                }
                else if(BreedsRow.get(key) instanceof Boolean){
                    Boolean boolValue = (Boolean) BreedsRow.get(key);
                    String value = "";
                    if(boolValue){
                        value = "true";
                    }
                    else{
                        value = "false";
                    }
                    breed.put(key, value);
                }
                else {
                    String value = (String) BreedsRow.get(key);
                    breed.put(key, value);
                }
            }

            Pet pet = petFactory.create(petID, organizationID, profileURL, name, colors,
                    breed, species, coat, age, attributes, environment, description, adoptable,
                    contact, gender, size);

            profiles.put(petID, pet);
        }
    }

    // helper function to transform certain pet data points to Map<String, String>
    public Map<String, String> toMapSS(JSONObject petJson, String param){
        JSONObject row = (JSONObject) petJson.get(param);
        JSONArray keys = row.names();

        Map<String, String> mapped = new HashMap<>();
        for (int i = 0; i < keys.length (); i++) {
            String key = keys.getString (i);
            if(row.get(key)==JSONObject.NULL) {
                mapped.put(key, null);
            }
            else {
                String value = (String) row.get(key);
                mapped.put(key, value);
            }
        }
        return mapped;
    }
    // transform pet data to Map(String, Boolean)
    public Map<String, Boolean> toMapSB(JSONObject petJson, String param){
        JSONObject row = (JSONObject) petJson.get(param);
        JSONArray keys = row.names();

        Map<String, Boolean> mapped = new HashMap<>();
        for (int i = 0; i < keys.length (); i++) {
            String key = keys.getString (i);
            if(row.get(key)==JSONObject.NULL) {
                mapped.put(key, null);
            }
            else {
                Boolean value = (Boolean) row.get(key);
                mapped.put(key, value);
            }
        }
        return mapped;
    }

    @Override
    public void save(Pet pet) {
        profiles.put(pet.getPetID(), pet);
        this.save();
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Pet pet : profiles.values()) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
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
    public Pet getPet(Integer id) {
        assert existsByName(id);
        return profiles.get(id);
    }

    @Override
    public void accessApi() {

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
        return (List<Pet>) profiles.values();
    }

}