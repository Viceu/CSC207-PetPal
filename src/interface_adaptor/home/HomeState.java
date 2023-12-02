package interface_adaptor.home;

import entities.Pet;
import entities.User;

import java.util.Map;

public class HomeState {
    private User user;

    private String failMessage = null;
    private Map<Integer, Pet> pets;

    public HomeState() {}

    public String getUsername() {
        return user.getName();
    }
    public String getBio() {
        return user.getBio();
    }
    public Map<Integer, Pet> getPets() {
        return pets;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setResults(Map<Integer, Pet> results) {this.pets = results;}

    public void setHomeFailMessage(String failMessage) {this.failMessage = failMessage;}
    public Object getFetchError() {
        return failMessage;
    }

}
