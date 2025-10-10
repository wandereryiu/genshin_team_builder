package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;

public class TestTeamComposition {
    private TeamComposition testTeam;
    private Character testCharacter1;
    private Character testCharacter2;
    private Character testCharacter3;
    private Character testCharacter4;
    private Character testCharacter5;

    @BeforeEach
    void runBefore() {
        testTeam = new TeamComposition();
        testCharacter1 = new Character("Neuvilette", "Hydro");
        testCharacter1.addRole("On-Field DPS");
        testCharacter2 = new Character("Furina", "Hydro");
        testCharacter2.addRole("Off-Field DPS");
        testCharacter2.addRole("Buffer");
        testCharacter3 = new Character("Kazuha","Anemo");
        testCharacter3.addRole("Support");
        testCharacter4 = new Character("Xilonen", "Geo");
        testCharacter4.addRole("Support");
        testCharacter5 = new Character("Ineffa","Electro");
        testCharacter5.addRole("Off-Field DPS");
    }

    @Test
    void testAddCharacter() {
        assertTrue(testTeam.addCharacter(testCharacter1));
        assertEquals(1, testTeam.size());
    }

    @Test
    void testAddDuplicateCharacter() {
        assertTrue(testTeam.addCharacter(testCharacter1));
        assertFalse(testTeam.addCharacter(testCharacter1));
        assertEquals(1, testTeam.size());
    }

    @Test
    void testAddMultipleCharacters() {
        assertTrue(testTeam.addCharacter(testCharacter1));
        assertTrue(testTeam.addCharacter(testCharacter2));
        assertTrue(testTeam.addCharacter(testCharacter3));
        assertTrue(testTeam.addCharacter(testCharacter4));
        assertEquals(4, testTeam.size());
    }

    @Test
    void testExceedCharacterLimit() {
        assertTrue(testTeam.addCharacter(testCharacter1));
        assertTrue(testTeam.addCharacter(testCharacter2));
        assertTrue(testTeam.addCharacter(testCharacter3));
        assertTrue(testTeam.addCharacter(testCharacter4));
        assertFalse(testTeam.addCharacter(testCharacter5));
        assertEquals(4, testTeam.size());
    }

    @Test
    void testRemoveCharacter() {
        assertTrue(testTeam.addCharacter(testCharacter1));
        assertEquals(1, testTeam.size());
        assertTrue(testTeam.removeCharacter(testCharacter1));
        assertEquals(0, testTeam.size());
    }

    @Test
    void testRemoveNonexistentCharacter() {
        assertFalse(testTeam.removeCharacter(testCharacter1));
    }

    @Test
    void testGetRolesPresent() {
        assertTrue(testTeam.addCharacter(testCharacter1));
        assertTrue(testTeam.addCharacter(testCharacter2));
        assertTrue(testTeam.addCharacter(testCharacter3));
        assertTrue(testTeam.addCharacter(testCharacter4));
        Set<String> testRolesPresent = testTeam.getRolesPresent();
        assertEquals(4, testRolesPresent.size());
        assertTrue(testRolesPresent.contains("On-Field DPS"));
        assertTrue(testRolesPresent.contains("Off-Field DPS"));
        assertTrue(testRolesPresent.contains("Buffer"));
        assertTrue(testRolesPresent.contains("Support"));
    }

    @Test
    void testGetElementsPresent() {
        assertTrue(testTeam.addCharacter(testCharacter1));
        assertTrue(testTeam.addCharacter(testCharacter2));
        assertTrue(testTeam.addCharacter(testCharacter3));
        assertTrue(testTeam.addCharacter(testCharacter4));
        Set<String> testElementsPresent = testTeam.getElementsPresent();
        assertTrue(testElementsPresent.contains("Hydro"));
        assertTrue(testElementsPresent.contains("Anemo"));
        assertTrue(testElementsPresent.contains("Geo"));
    }


    @Test
    void testElectroHydroReactionReaction() {
        assertTrue(testTeam.addCharacter(testCharacter1));
        testCharacter5.addRole("Off-Field DPS");
        assertTrue(testTeam.addCharacter(testCharacter5));

        Set<String> reactions = testTeam.getElementalReactions();
        assertTrue(reactions.contains("Electro-charged"));
        assertEquals(1, reactions.size());
    }

    @Test
    void testMulipleCharacterReactions() {
        assertTrue(testTeam.addCharacter(testCharacter1));
        assertTrue(testTeam.addCharacter(testCharacter3));
        assertTrue(testTeam.addCharacter(testCharacter5));

        Set<String> reactions = testTeam.getElementalReactions();
        assertTrue(reactions.contains("Electro-charged"));
        assertEquals(1, reactions.size());
    }

    @Test
    void testNoReactions() {
        assertTrue(testTeam.addCharacter(testCharacter3));
        assertTrue(testTeam.addCharacter(testCharacter4));

        Set<String> reactions = testTeam.getElementalReactions();
        assertTrue(reactions.isEmpty());
    }

    @Test
    void testGetAllCharacters() {
        assertTrue(testTeam.addCharacter(testCharacter1));
        assertTrue(testTeam.addCharacter(testCharacter2));
    
        ArrayList<Character> teamMembers = testTeam.getAllCharacters();
        assertEquals(2, teamMembers.size());
        assertTrue(teamMembers.contains(testCharacter1));
        assertTrue(teamMembers.contains(testCharacter2));
    }
}
