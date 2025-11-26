package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents an arbitrary list of all attained characters 
// (called Character Archive to match in-game naming conventions)
public class CharacterArchive implements Writable {
    private ArrayList<Character> characters = new ArrayList<Character>();

    // EFFECTS: returns a copy of all existing characters in the archive
    public ArrayList<Character> getAllCharacters() {
        EventLog.getInstance().logEvent(new Event("Viewed all characters in the Character Archive."));
        
        return new ArrayList<>(characters);
    }

    // REQUIRES: character must not be null
    // MODIFIES: this
    // EFFECTS: adds character to the list and returns true if it does not already exist,
    //          otherwise returns false
    public boolean addCharacter(Character character) {
        for (Character c : characters) {
            if (c.getName().equalsIgnoreCase(character.getName())) {
                return false;
            }
        }
        EventLog.getInstance().logEvent(new Event(character.getName() + "successfuly added to archive."));
        characters.add(character);
        return true;
    }

    // REQUIRES: character must not be null
    // MODIFIES: this
    // EFFECTS: removes character from the list and returns true if it exists in the
    // list,
    // otherwise returns false
    public boolean removeCharacter(Character character) {
        for (Character c : characters) {
            if (c.getName().equalsIgnoreCase(character.getName())) {
                characters.remove(c);
                EventLog.getInstance().logEvent(new Event(c.getName() + "successfully removed from archive."));
                return true;
            }
        }
        return false;
    }

    // REQUIRES: element is not empty or null
    // EFFECTS: returns a filtered list of characters with the specified element
    public ArrayList<Character> filterByElement(String element) {
        ArrayList<Character> filteredElementList = new ArrayList<Character>();
        for (Character c : characters) {
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
        for (Character c : characters) {
            if (c.hasRole(role)) {
                filteredRoleList.add(c);
            }
        }
        return filteredRoleList;
    }

    // EFFECTS: returns the number of characters in this Character Archive
    public int numCharacters() {
        return characters.size();
    }

    // Referenced from JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("characters", charactersToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray charactersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Character c : characters) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
