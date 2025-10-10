package model;

import java.util.HashSet;
import java.util.Set;

// Represents a character that has a name, element, role(s), and required ascension materials
public class Character {
    private String name; // character name
    private String element; // one of: Anemo, Geo, Electro, Dendro, Hydro, Pyro, Cryo
    private Set<String> roles = new HashSet<>(); // Main DPS, Off-field DPS, Support, Healer, etc.

    // EFFECTS: Creates a new character with a name and element that has an empty set of roles
    public Character(String name, String element) {
        //stub
    }

    public String getName() {
        return "";
    }

    public String getElement() {
        return "";
    }

    // EFFECTS: returns a copy of the set of roles a given character has
    public Set<String> getRoles() {
        return null;
    }

    // REQUIRES: role must not be an empty string
    // MODIFIES: this
    // EFFECTS: adds role to the set of roles if it is not already in the set
    public void addRole(String role) {
        //stub
    }

    // REQUIRES: role must not be an empty string
    // MODIFIES: this 
    // EFFECTS: removes the role from existing set of roles if it is present
    public void removeRole(String role) {
        //stub
    }

    // REQUIRES: role must not be an empty string
    // EFFECTS: returns true if the character already has the specified role, 
    //          otherwise false
    public boolean hasRole(String role) {
        return false;
    }

    
}
