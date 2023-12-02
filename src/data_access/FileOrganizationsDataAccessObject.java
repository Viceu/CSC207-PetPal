package data_access;

import entities.Organizations;
import entities.OrganizationsFactory;
import entities.Pet;
import use_case.adopt_user_preview.AdoptUserPreviewDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileOrganizationsDataAccessObject implements AdoptUserPreviewDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Organizations> orgAccounts = new HashMap<>();

    private OrganizationsFactory organizationsFactory;

    public FileOrganizationsDataAccessObject(File csvFile) {
        this.csvFile = csvFile;
    }

    public FileOrganizationsDataAccessObject(String csvPath, OrganizationsFactory organizationsFactory) throws IOException {
        this.organizationsFactory = organizationsFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("bio", 2);

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,bio");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String bio = String.valueOf(col[headers.get("bio")]);
                    Organizations organizations = (Organizations) organizationsFactory.create(username, password, bio);
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
    public Organizations getUsername(String username) {
        return orgAccounts.get(username);
    }

    @Override
    public Organizations getPassword(String password) {
        return orgAccounts.get(password);
    }

    @Override
    public Organizations getBio(String bio) {
        return orgAccounts.get(bio);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Organizations organizations : orgAccounts.values()) {
                String line = String.format("%s,%s,%s",
                        organizations.getName(), organizations.getPassword(), organizations.getBio());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
