package view;

import entities.Pet;

import java.util.HashMap;
import java.util.*;
import javax.swing.*;

public class DisplayState {
    private String failMessage = null;
    private HashMap<String, String> requirements = new HashMap<String, String>();

    public Map<String, String> getRequirements() {
        return requirements;
    }

    public void addRequirement(String req, String input) {
        requirements.put(req, input);
    }
    public void setSearchFailMessage(String errorMessage) {
        failMessage = errorMessage;
    }

    public Object getRequirementsError() {
        return failMessage;
    }
    public void setRequirements(Map<Integer, Pet> requirements) {
    }
}
