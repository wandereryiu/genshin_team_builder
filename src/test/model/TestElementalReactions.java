package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class TestElementalReactions {
    private Set<String> testElements;

    @BeforeEach
    void runBefore() {
        testElements = new HashSet<String>();
    }
    
    @Test
    void testDendroHydroReaction() {
        testElements.add("Dendro");
        testElements.add("Hydro");

        Set<String> testReactions = ElementalReactions.getReactions(testElements);
        assertTrue(testReactions.contains("Bloom"));    
    }

    @Test
    void testDendroPyroReaction() {
        testElements.add("Dendro");
        testElements.add("Pyro");

        Set<String> testReactions = ElementalReactions.getReactions(testElements);
        assertTrue(testReactions.contains("Burning"));    
    }

    @Test
    void testDendroElectroReaction() {
        testElements.add("Dendro");
        testElements.add("Electro");

        Set<String> testReactions = ElementalReactions.getReactions(testElements);
        assertTrue(testReactions.contains("Quicken"));    
    }

    @Test
    void testDendrElectroWithoutHydroReaction() {
        testElements.add("Dendro");
        testElements.add("Electro");

        Set<String> testReactions = ElementalReactions.getReactions(testElements);
        assertTrue(testReactions.contains("Quicken"));
        assertFalse(testReactions.contains("Hyperbloom"));    
    }

    @Test
    void testDendroPyroWithoutHydroReaction() {
        testElements.add("Dendro");
        testElements.add("Pyro");

        Set<String> testReactions = ElementalReactions.getReactions(testElements);
        assertTrue(testReactions.contains("Burning"));
        assertFalse(testReactions.contains("Burgeon"));    
    }

    @Test
    void testPyroHydroReaction() {
        testElements.add("Pyro");
        testElements.add("Hydro");

        Set<String> testReactions = ElementalReactions.getReactions(testElements);
        assertTrue(testReactions.contains("Vaporize"));    
    }

    @Test
    void testPyroCryoReaction() {
        testElements.add("Pyro");
        testElements.add("Cryo");

        Set<String> testReactions = ElementalReactions.getReactions(testElements);
        assertTrue(testReactions.contains("Melt"));
    }

    
    @Test
    void testPyroElectroReaction() {
        testElements.add("Pyro");
        testElements.add("Electro");

        Set<String> testReactions = ElementalReactions.getReactions(testElements);
        assertTrue(testReactions.contains("Overloaded")); 
    }

    @Test
    void testHydroElectroReaction() {
        testElements.add("Hydro");
        testElements.add("Electro");

        Set<String> testReactions = ElementalReactions.getReactions(testElements);
        assertTrue(testReactions.contains("Electro-charged")); 
    }

    @Test
    void testSingleRandomElement() {
        testElements.add("Geo");
        Set<String> reactions = ElementalReactions.getReactions(testElements);
        assertTrue(reactions.isEmpty());
    }

    @Test
    void testCryoHydroReaction() {
        testElements.add("Cryo");
        testElements.add("Hydro");

        Set<String> testReactions = ElementalReactions.getReactions(testElements);
        assertTrue(testReactions.contains("Freeze")); 
    }

    @Test
    void testRedundancy() {
        testElements.add("Cryo");
        testElements.add("Hydro");
        testElements.add("Hydro");
        testElements.add("Hydro");

        Set<String> testReactions = ElementalReactions.getReactions(testElements);
        assertTrue(testReactions.contains("Freeze")); 
    }

    @Test
    void testMonoTeam() {
        testElements.add("Hydro");
        testElements.add("Hydro");
        testElements.add("Hydro");
        
        Set<String> testReactions = ElementalReactions.getReactions(testElements);
        assertTrue(testReactions.isEmpty());
    }

    @Test
    void testMultipleReactions() {
        testElements.add("Dendro");
        testElements.add("Pyro");
        testElements.add("Hydro");
        testElements.add("Electro");

        Set<String> testReactions = ElementalReactions.getReactions(testElements);
        assertTrue(testReactions.contains("Burning"));
        assertTrue(testReactions.contains("Vaporize"));
        assertTrue(testReactions.contains("Electro-charged")); 
    }

    @Test
    void testNoReactions() {
        testElements.add("Anemo");
        testElements.add("Geo");

        Set<String> testReactions = ElementalReactions.getReactions(testElements);
        assertTrue(testReactions.isEmpty());
    }

    @Test
    void testNoElementsPresent() {
        Set<String> testReactions = ElementalReactions.getReactions(testElements);
        assertTrue(testReactions.isEmpty());
    }

}
