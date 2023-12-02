package interface_adaptor.home;

import entities.Pet;

import java.util.Map;

public class HomeState {
    private String username = "";
    private String bio = "";

    private String failMessage = null;
    private Map<Integer, Pet> pets;

    public HomeState() {}

    public String getUsername() {
        return username;
    }
    public String getBio() {
        return bio;
    }
    public Map<Integer, Pet> getPets() {
        return pets;
    }

    // TODO: call set()s from LoginPresenter
    public void setUsername(String username) {
        this.username = username;
    }
    public void setUserBio(String bio) {
        this.bio = bio;
    }
    public void setResults(Map<Integer, Pet> results) {this.pets = results;}

    public void setHomeFailMessage(String failMessage) {this.failMessage = failMessage;}
    public Object getFetchError() {
        return failMessage;
    }

}
