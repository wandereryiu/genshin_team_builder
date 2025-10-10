package model;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class TestCharacterArchive {
    private CharacterArchive testArchive;
    private Character testCharacter1;
    private Character testCharacter2;
    private Character testCharacter3;

    @BeforeEach
    void runBefore() {
        testCharacter1 = new Character("Lauma", "Dendro");
        testCharacter1.addRole("Off-Field Support");
        testCharacter2 = new Character("Clorinde", "Electro");
        testCharacter2.addRole("On-Field DPS");
        testCharacter3 = new Character("Arlecchino", "Pyro");
        testCharacter3.addRole("On-Field DPS");
        testArchive = new CharacterArchive();
    }

    @Test
    void testAddCharacter() {
        assertTrue(testArchive.addCharacter(testCharacter1));
    }

    @Test
    void testAddMultipleCharacters() {
        testArchive.addCharacter(testCharacter1);
        testArchive.addCharacter(testCharacter2);
        testArchive.addCharacter(testCharacter3);
        assertEquals(3, testArchive.getAllCharacters().size());
    }

    @Test
    void testAddDuplicateCharacter() {
        assertTrue(testArchive.addCharacter(testCharacter1));
        assertFalse(testArchive.addCharacter(testCharacter1));
    }

    @Test 
    void testAddCharacterWithSameName() {
        assertTrue(testArchive.addCharacter(testCharacter1));
        Character testCharacterCopy = new Character("Lauma", "Hydro");
        assertFalse(testArchive.addCharacter(testCharacterCopy));
    }

    @Test
    void testRemoveCharacter() {
        assertTrue(testArchive.addCharacter(testCharacter1));
        assertTrue(testArchive.removeCharacter(testCharacter1));
        assertEquals(0, testArchive.getAllCharacters().size());
    }

    @Test
    void testRemoveNonexistentCharacter() {
        assertFalse(testArchive.removeCharacter(testCharacter1));
    }

    @Test
    void testRemoveWrongCharacter() {
        assertTrue(testArchive.addCharacter(testCharacter1));
        Character testRandomCharacter = new Character("Bennett", "Pyro");
        assertFalse(testArchive.removeCharacter(testRandomCharacter));
    }

    @Test
    void testGetAllCharacters() {
        testArchive.addCharacter(testCharacter1);
        testArchive.addCharacter(testCharacter2);
        testArchive.addCharacter(testCharacter3);
        ArrayList<Character> testArchiveCopy = testArchive.getAllCharacters();
        assertEquals(3, testArchiveCopy.size());
    }

    @Test
    void testFilterByElement(){
        testArchive.addCharacter(testCharacter1);
        testArchive.addCharacter(testCharacter2);
        testArchive.addCharacter(testCharacter3);
        ArrayList<Character> testFilteredElement = testArchive.filterByElement("Dendro");
        assertEquals(1, testFilteredElement.size());
        assertTrue(testFilteredElement.contains(testCharacter1));
    }

    @Test
    void testFilterByNonExistingElement() {
        testArchive.addCharacter(testCharacter1);
        ArrayList<Character> testFilteredElement = testArchive.filterByElement("Pyro");
        assertEquals(0, testFilteredElement.size());
        assertFalse(testFilteredElement.contains(testCharacter1));
    }

    @Test
    void testFilterByRole() {
        testArchive.addCharacter(testCharacter1);
        testArchive.addCharacter(testCharacter2);
        testArchive.addCharacter(testCharacter3);
        ArrayList<Character> testFilteredRole = testArchive.filterByRole("Off-Field Support");
        assertEquals(1, testFilteredRole.size());
        assertTrue(testFilteredRole.contains(testCharacter1));
        assertFalse(testFilteredRole.contains(testCharacter2));
    }

    @Test
    void testFilterByNonExistingRole() {
        testArchive.addCharacter(testCharacter2);
        ArrayList<Character> testFilteredRole = testArchive.filterByRole("Off-Field DPS");
        assertEquals(0, testFilteredRole.size());
        assertFalse(testFilteredRole.contains(testCharacter2));
    }


}
