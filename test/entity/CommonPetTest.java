package entity;

import entities.CommonPet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonPetTest {

    private CommonPet pet;
    @BeforeEach
    void init(){
        pet = new CommonPet(null, null, null, "Coco13", null, null,
                null, null, null, null, null, null, false,
                null, null, null, "I am a rabbit.", "Jessica");
    }
    @Test
    void getNameTest(){
        assertEquals("Coco13", pet.getName());
    }

    @Test
    void getBioTest() {
        assertEquals("I am a rabbit.", pet.getBio());
    }

    @Test
    void getOwnerTest() {
        assertEquals("Jessica", pet.getOwner());
    }
    @Test
    void getAdoptableTest() {
        assertEquals(false, pet.getAdoptable());
    }
    @Test
    void setAoptableTest() {
        pet.setAdoptable(true);
        assertEquals(true, pet.getAdoptable());
    }
    @Test
    void setOwnerTest() {
        pet.setOwner("Jess");
        assertEquals("Jess", pet.getOwner());
    }

    @Test
    void setBioTest() {
        pet.setBio("I am a ");
    }
}