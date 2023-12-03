package entity;

import entities.PersonalUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonalUserTest {
    private PersonalUser user;

    @BeforeEach
    void init(){
        user = new PersonalUser("Jessica", "123", "cat person", LocalDateTime.now());
    }

    @Test
    void getNameTest() {
        assertEquals("Jessica", user.getName());
    }

    @Test
    void getPasswordTest() {
        assertEquals("123", user.getPassword());
    }

    @Test
    void setPasswordTest() {
        user.setPassword("000");
        assertEquals("000", user.getPassword());
    }

    @Test
    void getBioTest() {
        assertEquals("cat person", user.getBio());
    }

    @Test
    void setBioTest() {
        user.setBio("Hi");
        assertEquals("Hi", user.getBio());
    }

}
