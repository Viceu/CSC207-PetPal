package data_access;

import api.ApiResults;
import entities.Organizations;
import entities.OrganizationsFactory;
import use_case.adopt_user_preview.AdoptUserPreviewDataAccessInterface;

import org.json.JSONObject;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

//
public class FileOrganizationsDataAccessObject implements AdoptUserPreviewDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Organizations> orgAccounts = new HashMap<>();

    private OrganizationsFactory organizationsFactory;

    /**
     * Create a DAO by saving the appropriate entity factory and creating a local file to keep track of entities
     * @param csvPath
     * @param organizationsFactory
     * @throws IOException
     */
    public FileOrganizationsDataAccessObject(String csvPath, OrganizationsFactory organizationsFactory) throws IOException {
        this.organizationsFactory = organizationsFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("bio", 2);
        headers.put("creation_time", 3);

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,bio,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String bio = String.valueOf(col[headers.get("bio")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    Organizations organizations = organizationsFactory.create(username, password, bio, ldt);
                    orgAccounts.put(username, organizations);
                }
            }
        }

    }


    @Override
    /**
     * Return if an organization of given id is in the system
     */
    public boolean existsByName(String identifier) {
        return orgAccounts.containsKey(identifier);
    }

    /**
     * Save an organization entity into system
     * @param organizations
     */
    @Override
    public void save(Organizations organizations) {
        orgAccounts.put(organizations.getName(), organizations);
        this.save();
    }

    /**
     * Return the organziation entity given its username
     * @param username
     * @return
     */
    @Override
    public Organizations get(String username) {
        return orgAccounts.get(username);
    }

    /**
     * Retrieve organization information from the API given the organization id, retrieved from its pet
     * @param orgID
     * @return
     */
    @Override
    public Organizations accessApi(String orgID) {
        String apiResult = ApiResults.getOrg(orgID);


        // Construct a JSONObject using above string for easier parsing
        JSONObject petJson = new JSONObject(apiResult);


        // store all needed data points for construction a Pet object
        String organizationID = String.valueOf(petJson.get("id"));
        String name = String.valueOf(petJson.get("name"));


        String bio = null;
        String password = organizationID;
        Organizations org = organizationsFactory.create(name, password, bio, LocalDateTime.now());


        return org;
    }


    /**
     * Save an organization to system file database
     */
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Organizations organizations : orgAccounts.values()) {
                String line = String.format("%s,%s,%s,%s",
                        organizations.getName(), organizations.getPassword(), organizations.getBio(), organizations.getCreationTime());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
