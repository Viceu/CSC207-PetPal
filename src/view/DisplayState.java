package view;

import entities.Pet;

import java.util.HashMap;
import java.util.*;
import javax.swing.*;
import entities.Pet;

public class DisplayState {
    private String failMessage = null;
    private Map<Integer, Pet> pets;

    public Map<String, Pet> getPets() {
        return pets;
    }

    public void setSearchFailMessage(String errorMessage) {
        failMessage = errorMessage;
    }

    public Object getRequirementsError() {
        return failMessage;
    }
  
    public void setResults(Map<Integer, Pet> results) {
        this.pets = results;
    }
}
