package model;

import java.util.HashSet;
import java.util.Set;

// Helper class for elemental reactions
public class ElementalReactions {
    private ElementalReactions() {
    }
    
    @SuppressWarnings("methodlength")

    // EFFEECTS: returns a set of reactions possible in the form of strings
    //           based off all elemental combinations possible in a given team
    public static Set<String> getReactions(Set<String> elements) {
        Set<String> elementalReactions = new HashSet<String>();

        if (elements.contains("Dendro") && elements.contains("Hydro")) {
            elementalReactions.add("Bloom");
        }
        if (elements.contains("Dendro") && elements.contains("Pyro")) {
            elementalReactions.add("Burning");
        }
        if (elements.contains("Dendro") && elements.contains("Electro")) {
            elementalReactions.add("Quicken");
        }
        if (elements.contains("Pyro") && elements.contains("Hydro")) {
            elementalReactions.add("Vaporize");
        }
        if (elements.contains("Pyro") && elements.contains("Cryo")) {
            elementalReactions.add("Melt");
        }
        if (elements.contains("Pyro") && elements.contains("Electro")) {
            elementalReactions.add("Overloaded");
        }
        if (elements.contains("Hydro") && elements.contains("Electro")) {
            elementalReactions.add("Electro-charged");
        }
        if (elements.contains("Cryo") && elements.contains("Hydro")) {
            elementalReactions.add("Freeze");
        }

        return elementalReactions;
    }
}
