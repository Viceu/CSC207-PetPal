package data_access;

import entities.Pet;
import entities.PetFactory;
import api.ApiResults;

import java.io.*;
import java.util.*;

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

            Map<String, String> breed = toMapSS(petJson, "breeds");
            Map<String, String> colorsMap = toMapSS(petJson, "colors");
            List<String> colors = new ArrayList<>(colorsMap.values());
            Map<String, String> coatMap = toMapSS(petJson, "coat");
            List<String> coat = new ArrayList<>(coatMap.values());
            Map<String, String> attributesSS = toMapSS(petJson, "attributes");
            Map<String, Boolean> attributes = new HashMap<>();
            for (String key: attributesSS.keySet()) {
                String value = attributesSS.get(key);
                if(value.equals("false")) {
                    attributes.put(key, Boolean.FALSE);
                }
                else if(value.equals("true")) {
                    attributes.put(key, Boolean.TRUE);
                }
                else{
                    attributes.put(key, null);
                }
            }
            Map<String, String> environmentSS = toMapSS(petJson, "environment");
            Map<String, Boolean> environment = new HashMap<>();
            for (String key: environmentSS.keySet()) {
                String value = environmentSS.get(key);
                if(value.equals("false")) {
                    environment.put(key, Boolean.FALSE);
                }
                else if(value.equals("true")) {
                    environment.put(key, Boolean.TRUE);
                }
                else{
                    environment.put(key, null);
                }
            }
            Map<String, String> contact = toMapSS(petJson, "contact");

            Pet pet = petFactory.create(petID, organizationID, profileURL, name, colors,
                    breed, species, coat, age, attributes, environment, description, adoptable,
                    contact, gender, size);

            profiles.put(petID, pet);
        }
    }

    // helper function to transform certain pet data points to Map<String, String>
    public Map<String, String> toMapSS(JSONObject petJson, String param){
        JSONObject row = new JSONObject(petJson.get(param)); // changed from List<String> to Map<String, Object>
        Map<String, Object> mapSO = row.toMap();
        Map<String, String> mapSS = new HashMap<String, String>();
        for (String key: mapSO.keySet()) {
            String value = "False";
            if (mapSO.get(key) == null) {
                value = "null";
            }
            else {
                value = mapSO.get(key).toString();
            }
            mapSS.put(key, value);
        }
        return mapSS;
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