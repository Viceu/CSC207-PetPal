package interface_adaptor.home;

import entities.Pet;

import java.util.Map;

public class HomeState {
    private String username = "";

    private String failMessage = null;
    private Map<Integer, Pet> pets;

    public HomeState(HomeState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public HomeState() {}

    public String getUsername() {
        return username;
    }

    // TODO: call from LoginPresenter
    public void setUsername(String username) {
        this.username = username;
    }

    public void setHomeFailMessage(String failmessage) {this.failMessage = failmessage;}
    public Object getFetchError() {
        return failMessage;
    }
    public Map<Integer, Pet> getPets() {
        return pets;
    }
    public void setResults(Map<Integer, Pet> results) {
    }
}
