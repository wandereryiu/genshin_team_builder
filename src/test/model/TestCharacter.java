package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class TestCharacter {
    private Character testCharacter;
    
    @BeforeEach
    void runBefore() {
        testCharacter = new Character("Flins", "Electro");
    }

    @Test
    void testGetters() {
        assertEquals("Flins", testCharacter.getName());
        assertEquals("Electro", testCharacter.getElement());
    }

    @Test
    void testConstructor() {
        assertEquals("Flins", testCharacter.getName());
        assertEquals("Electro", testCharacter.getElement());
        assertTrue(testCharacter.getRoles().isEmpty());
    }

    @Test
    void testAddRole() {
        testCharacter.addRole("Main DPS");
        Set<String> roles = testCharacter.getRoles();
        assertEquals(1, roles.size());
        assertTrue(testCharacter.hasRole("Main DPS"));
    }

    @Test
    void testAddDuplicateRole() {
        testCharacter.addRole("Main DPS");
        testCharacter.addRole("Main DPS");
        Set<String> roles = testCharacter.getRoles();
        assertEquals(1, roles.size());
        assertTrue(testCharacter.hasRole("Main DPS"));
    }

    @Test
    void testRemoveRole() {
        testCharacter.addRole("Support");
        Set<String> roles = testCharacter.getRoles();
        assertTrue(roles.contains("Support"));
        testCharacter.removeRole("Support");
    }

    @Test 
    void testRemoveNonexistentRole() {
        testCharacter.addRole("Main DPS");
        testCharacter.removeRole("Healer");
        assertTrue(testCharacter.hasRole("Main DPS"));
    }

    @Test
    void testGetRoles() {
        testCharacter.addRole("Main DPS");
        testCharacter.addRole("Off-field DPS");
        Set<String> rolesCopy = testCharacter.getRoles();
        assertTrue(rolesCopy.contains("Main DPS"));
        assertTrue(rolesCopy.contains("Off-field DPS"));
        assertEquals(2, rolesCopy.size());
        rolesCopy.add("Healer");
        assertFalse(testCharacter.hasRole("Healer"));
        assertEquals(2, testCharacter.getRoles().size());
    }

    @Test
    void testAddEmptyRole() {
        testCharacter.addRole(null);
        testCharacter.addRole("");
        assertEquals(0, testCharacter.getRoles().size());
    }

    @Test
    void testRemoveEmptyRole() {
        testCharacter.addRole("Main DPS");
        testCharacter.removeRole(null);
        testCharacter.removeRole("");
        assertEquals(1, testCharacter.getRoles().size());
    }

    @Test
    void testHasEmptyRole() {
        testCharacter.addRole("Main DPS");
        assertFalse(testCharacter.hasRole(""));
        assertFalse(testCharacter.hasRole(null));
    }
}