package model;

import java.util.ArrayList;
import java.util.HashSet;
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
        return team.size();
    }

    // REQUIRES: character is an existing object 
    // MODIFIES: this
    // EFFECTS: adds character if they are not already in the team and
    //          team does not exceed MAX_TEAM_SIZE, otherwise returns false
    public boolean addCharacter(Character character) {
        if (team.contains(character) || team.size() == MAX_TEAM_SIZE) {
            return false;
        }
        team.add(character);
        return true;
    }

    // REQUIRES: character is an existing object
    // MODIFIES: this
    // EFFECTS: removes character if present in the team, otherwise returns false
    public boolean removeCharacter(Character character) {
        return team.remove(character);
    }

    // EFFECTS: returns a set of all roles present in the team
    public Set<String> getRolesPresent() {
        Set<String> rolesPresent = new HashSet<String>();
        for (Character c: team) {
            rolesPresent.addAll(c.getRoles());
        }
        return rolesPresent;
    }

    // EFFECTS: returns a set of all elements present in the team
    public Set<String> getElementsPresent() {
        Set<String> elementsPresent = new HashSet<String>();
        for (Character c: team) {
            elementsPresent.add(c.getElement());
        }
        return elementsPresent;
    }
}
