package model;

import java.util.ArrayList;
import java.util.List;

public class CharacterArchive {
    private List<Character> characters = new ArrayList<Character>();


    // EFFECTS: returns a copy of all existing characters in the archive
    public List<Character> getAllCharacters() {
        return new ArrayList<>();
    }

    // REQUIRES: character must not be null
    // MODIFIES: this
    // EFFECTS: adds character to the list if it does not already exist,
    //          otherwise returns false
    public boolean addCharacter(Character character) {
        return false;
    }

    // REQUIRES: character must not be null
    // MODIFIES: this
    // EFFECTS: removes character from the list if it does not already exist,
    //          otherwise returns false
    public boolean removeCharacter(Character character) {
        return false;
    }
}
