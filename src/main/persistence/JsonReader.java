package persistence;

import model.Character;
import model.CharacterArchive;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;


// Referenced from JSonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads archive from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads CharacterArchive from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CharacterArchive read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCharacterArchive(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses CharacterArchive from JSON object and returns it
    private CharacterArchive parseCharacterArchive(JSONObject jsonObject) {
        CharacterArchive archive = new CharacterArchive();
        addCharacters(archive, jsonObject);
        return archive;
    }

    // MODIFIES: archive
    // EFFECTS: parses thingies from JSON object and adds them to archive
    private void addCharacters(CharacterArchive archive, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("characters");
        for (Object json : jsonArray) {
            JSONObject nextCharacter = (JSONObject) json;
            addCharacter(archive, nextCharacter);
        }
    }

    // MODIFIES: archive
    // EFFECTS: parses thingy from JSON object and adds it to archive
    private void addCharacter(CharacterArchive archive, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String element = jsonObject.getString("element");
        Character character = new Character(name, element);

        if (jsonObject.has("roles")) {
            JSONArray rolesArray = jsonObject.getJSONArray("roles");
            for (Object roleObj: rolesArray) {
                String role = (String) roleObj;
                character.addRole(role);
            }
        }

        archive.addCharacter(character);
    }
    
}
