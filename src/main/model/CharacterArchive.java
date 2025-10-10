package model;

import java.util.ArrayList;

// Represents an arbitrary list of all attained characters 
// (called Character Archive to match in-game naming conventions)
public class CharacterArchive {
    private ArrayList<Character> characters = new ArrayList<Character>();

    // EFFECTS: returns a copy of all existing characters in the archive
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    // REQUIRES: character must not be null
    // MODIFIES: this
    // EFFECTS: adds character to the list and returns true if it does not already exist,
    //          otherwise returns false
    public boolean addCharacter(Character character) {
        for (Character c: characters) {
            if (c.getName().equalsIgnoreCase(character.getName())) {
                return false;
            }
        }
        characters.add(character);
        return true;
    }

    // REQUIRES: character must not be null
    // MODIFIES: this
    // EFFECTS: removes character from the list and returns true if it exists in the list,
    //          otherwise returns false
    public boolean removeCharacter(Character character) {
        for (Character c: characters) {
            if (c.getName().equalsIgnoreCase(character.getName())) {
                characters.remove(c);
                return true;
            }
        }
        return false;
    }

    // REQUIRES: element is not empty or null
    // EFFECTS: returns a filtered list of characters with the specified element
    public ArrayList<Character> filterByElement(String element) {
        ArrayList<Character> filteredElementList = new ArrayList<Character>();
        for (Character c: characters) {
            if (c.getElement().equalsIgnoreCase(element)) {
                filteredElementList.add(c);
            }
        }
        return filteredElementList;
    }

    // REQUIRES: role is not empty or null
    // EFFECTS: returns a filtered list of characters with the specified role
    public ArrayList<Character> filterByRole(String role) {
        ArrayList<Character> filteredRoleList = new ArrayList<Character>();
        for (Character c: characters) {
            if (c.hasRole(role)){
                filteredRoleList.add(c);
            }
        }
        return filteredRoleList;
    }
}
