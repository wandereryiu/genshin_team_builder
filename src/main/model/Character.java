package model;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;
import persistence.Writable;

// Represents a character that has a name, element and role(s)
public class Character implements Writable {
    private String name; // character name
    private String element; // one of: Anemo, Geo, Electro, Dendro, Hydro, Pyro, Cryo
    private Set<String> roles = new HashSet<>(); // Main DPS, Off-field DPS, Support, Healer, etc.

    // EFFECTS: Creates a new character with a name and element that has an empty
    // set of roles
    public Character(String name, String element) {
        this.name = name;
        this.element = element;
        this.roles = new HashSet<>();

        EventLog.getInstance().logEvent(
                new Event(
                        this.name + "with Element:" + this.element + " and Roles:" + this.roles + "has been created!"));
    }

    public String getName() {
        return name;
    }

    public String getElement() {
        return element;
    }

    // EFFECTS: returns a copy of the set of roles a given character has
    public Set<String> getRoles() {
        return new HashSet<>(roles);
    }

    // REQUIRES: role must not be an empty string
    // MODIFIES: this
    // EFFECTS: adds role to the set of roles if it is not already in the set
    public void addRole(String role) {
        if (role == null || role.isEmpty()) {
            return;
        }
        if (roles.add(role)) {
            EventLog.getInstance().logEvent(new Event(role + " has been added as a role for " + this.name));
        }
    }

    // REQUIRES: role must not be an empty string
    // MODIFIES: this
    // EFFECTS: removes the role from existing set of roles if it is present
    public void removeRole(String role) {
        if (role == null || role.isEmpty()) {
            return;
        }
        roles.remove(role);
    }

    // REQUIRES: role must not be an empty string
    // EFFECTS: returns true if the character already has the specified role,
    // otherwise false
    public boolean hasRole(String role) {
        if (role == null || role.isEmpty()) {
            return false;
        }
        return roles.contains(role);
    }

    // Referenced from JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("element", element);
        json.put("roles", roles);
        return json;
    }
}
