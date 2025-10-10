package model;

import java.util.ArrayList;
import java.util.Set;

// Represents a valid team 
public class TeamComposition {
    private static final int MAX_TEAM_SIZE = 4;
    private ArrayList<Character> team;

    // EFFECTS: creates an list representing a team
    public TeamComposition() {
        team = new ArrayList<Character>();
    }

    // EFFECTS: returns the number of characters present in the team
    public int size() {
        return 0;
    }

    // REQUIRES: character is an existing object
    // MODIFIES: this
    // EFFECTS: adds character if they are not already in the team and
    //          team does not exceed MAX_TEAM_SIZE, otherwise returns false
    public boolean addCharacter(Character character) {
        return false;
    }

    // REQUIRES: character is an existing object
    // MODIFIES: this
    // EFFECTS: removes character if present in the team, otherwise returns false
    public boolean removeCharacter(Character character) {
        return false;
    }

    // EFFECTS: returns a set of all roles present in the team
    public Set<String> getRolesPresent() {
        return null;
    }

    // EFFECTS: returns a set of all elements present in the team
    public Set<String> getElementsPresent() {
        return null;
    }
}
