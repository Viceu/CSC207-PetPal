package entity;

import entities.Organizations;
import entities.Requests;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class OrganizationsTest {
    private Organizations organizations;
    private ArrayList<Requests> requestList = new ArrayList<Requests>();

    @BeforeEach
    void init() {
        organizations = new Organizations("fightingchanceranchandrecuse", "1234", "I love pets", LocalDateTime.now());
    }

    @Test
    void getUsernameTest() {
        assertEquals("fightingchanceranchandrecuse", organizations.getName());
    }

    @Test
    void getPasswordTest() {
        assertEquals("1234", organizations.getPassword());
    }

    @Test
    void getBioTest() {
        assertEquals("I love pets", organizations.getBio());
    }

    @Test
    void addRequestTest() {

    }

    @Test
    void acceptRequestTest() {

    }

    @Test
    void rejectRequestTest() {

    }

}