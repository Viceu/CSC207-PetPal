package data_access;

import entities.Organizations;
import entities.OrganizationsFactory;
import entities.Pet;
import use_case.adopt_user_preview.AdoptUserPreviewDataAccessObject;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileOrganizationDataAccessObject implements AdoptUserPreviewDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Organizations> orgAccounts = new HashMap<>();

    private OrganizationsFactory organizationsFactory;

    public FileOrganizationDataAccessObject(File csvFile) {
        this.csvFile = csvFile;
    }

    public FileOrganizationDataAccessObject(String csvPath, OrganizationsFactory organizationsFactory) throws IOException {
        this.organizationsFactory = organizationsFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("pets", 2);
        headers.put("bio", 3);

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,pets,bio");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    Map<String, Pet> pets = col[headers.get("pets")];
                    String bio = String.valueOf(col[headers.get("bio")]);
                    Organizations organizations = organizationsFactory.create(username, password, pets, bio);
                    orgAccounts.put(username, organizations);
                }
            }
        }

    }

    @Override
    public boolean existsByName(String identifier) {
        return orgAccounts.containsKey(identifier);
    }

    @Override
    public void save(Organizations organizations) {
        orgAccounts.put(organizations.getName(), organizations);
        this.save();
    }

    @Override
    public Organizations get(String username) {
        return orgAccounts.get(username);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Organizations organizations : orgAccounts.values()) {
                String line = String.format("%s,%s,%s",
                        organizations.getName(), organizations.getPassword(), organizations.getPets(), organizations.getBio());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
