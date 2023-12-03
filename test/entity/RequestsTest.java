package entity;

import entities.Organizations;
import entities.Pet;
import entities.Requests;
import entities.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class RequestsTest {

    private Pet pet;
    private User user;
    private Organizations organization;

    private Requests request;

    @BeforeEach
    void init() {
        request = new Requests(pet, user, "I want to adopt a pet", organization);

    }

    @Test
    void getPetTest() {
        assertEquals("Paul", user.getName());
    }

    @Test
    void getUserTest() {
        assertEquals("password", user.getPassword());
    }

    @Test
    void getOrganizationTest() {
        assertEquals("password", user.getPassword());
    }

    @Test
    void getStatusTest() {
        assertEquals("password", user.getPassword());
    }

    @Test
    void getAcceptedOrNotTest() {
        assertEquals("password", user.getPassword());
    }
}
