package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Referenced from W3Schools:
// https://www.w3schools.com/java/java_hashmap.asp

// Helper class for elemental reactions
public class ElementalReactions {
    // Key is the set of elements
    // Value is the reactions that can result from it
    private static final Map<Set<String>, String> ELEMENTAL_REACTIONS = new HashMap<>();

    static {
        ELEMENTAL_REACTIONS.put(Set.of("Dendro", "Hydro"), "Bloom");
        ELEMENTAL_REACTIONS.put(Set.of("Dendro", "Hydro", "Electro"), "Hyperbloom");
        ELEMENTAL_REACTIONS.put(Set.of("Dendro", "Hydro", "Pyro"), "Burgeon");
        ELEMENTAL_REACTIONS.put(Set.of("Dendro", "Pyro"), "Burning");
        ELEMENTAL_REACTIONS.put(Set.of("Dendro", "Electro"), "Aggravate/Quicken");
        ELEMENTAL_REACTIONS.put(Set.of("Hydro", "Pyro"), "Vaporize");
        ELEMENTAL_REACTIONS.put(Set.of("Hydro", "Electro"), "Electro Charged");
        ELEMENTAL_REACTIONS.put(Set.of("Hydro", "Cryo"), "Freeze");
        ELEMENTAL_REACTIONS.put(Set.of("Cryo", "Pyro"), "Melt");
        ELEMENTAL_REACTIONS.put(Set.of("Cryo", "Electro"), "Superconduct");
        ELEMENTAL_REACTIONS.put(Set.of("Pyro", "Electro"), "Overloaded");
    }
    
    private ElementalReactions() {
    }
    

    // EFFEECTS: returns a set of reactions possible in the form of strings
    //           based off all elemental combinations possible in a given team
    public static Set<String> getReactions(Set<String> elements) {
        Set<String> elementalReactions = new HashSet<String>();

        // Referenced from Java docs:
        // https://docs.oracle.com/javase/8/docs/api/java/util/Map.Entry.html
        // https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html

        for (Map.Entry<Set<String>, String> entry: ELEMENTAL_REACTIONS.entrySet()) {
            if (elements.containsAll(entry.getKey())) {
                elementalReactions.add(entry.getValue());
            }
        }
        
        return elementalReactions;
    }
}
