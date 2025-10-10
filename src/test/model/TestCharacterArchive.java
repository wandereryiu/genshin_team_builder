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
        testCharacter2 = new Character("Clorinde", "Electro");
        testCharacter3 = new Character("Arlecchino", "Pyro");
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
}
