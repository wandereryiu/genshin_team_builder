package persistence;

import model.Character;
import model.CharacterArchive;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// Referenced from JSonSerializationDemo

@ExcludeFromJacocoGeneratedReport
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistentFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test 
    void testReaderEmptyCharacterArchive() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyArchive.json");
        try {
            CharacterArchive archive = reader.read();
            assertEquals(0, archive.numCharacters());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralArchive() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralArchive.json");
        try {
            CharacterArchive archive = reader.read();
            List<Character> characters = archive.getAllCharacters();
            assertEquals(3, characters.size());

            // Neuvilette
            Set<String> neuviletteRoles = new HashSet<>();
            neuviletteRoles.add("Main DPS");
            checkCharacter("Neuvilette", "Hydro", neuviletteRoles, characters.get(0));

            // Furina
            Set<String> furinaRoles = new HashSet<>();
            furinaRoles.add("Off-Field DPS");
            furinaRoles.add("Support");
            checkCharacter("Furina", "Hydro", furinaRoles, characters.get(1));

            // Qiqi
            Set<String> qiqiRoles = new HashSet<>();
            assertTrue(qiqiRoles.isEmpty());
            checkCharacter("Qiqi", "Cryo", qiqiRoles, characters.get(2));
            
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
