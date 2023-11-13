package view;

import java.util.HashMap;
import java.util.*;
import javax.swing.*;
import entities.Pet;

public class DisplayState {
    private String failMessage = null;
    private HashMap<String, Pet> pets = new HashMap<String, Pet>();

    public Map<String, Pet> getPets() {
        return pets;
    }

    public void setRequirements(HashMap<String, Pet> petAttributes) {
        pets = petAttributes;
    }

    public void setSearchFailMessage(String errorMessage) {
        failMessage = errorMessage;
    }

    public Object getRequirementsError() {
        return failMessage;
    }
}
