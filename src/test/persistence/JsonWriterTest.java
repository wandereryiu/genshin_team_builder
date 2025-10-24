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
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyArchive() {
        try {
            CharacterArchive archive = new CharacterArchive();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyArchive.json");
            writer.open();
            writer.write(archive);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyArchive.json");
            archive = reader.read();
            assertEquals(0, archive.numCharacters());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @SuppressWarnings("methodlength")
    @Test
    void testWriterGeneralArchive() {
        try {
            CharacterArchive archive = new CharacterArchive();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralArchive.json");

            Character kazuha = new Character("Kazuha", "Anemo");
            kazuha.addRole("Support");
            archive.addCharacter(kazuha);

            Character xilonen = new Character("Xilonen", "Geo");
            xilonen.addRole("Support");
            archive.addCharacter(xilonen);

            Character lyney = new Character("Lyney", "Pyro");
            lyney.addRole("Main DPS");
            archive.addCharacter(lyney);

            writer.open();
            writer.write(archive);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralArchive.json");
            archive = reader.read();

            List<Character> characters = archive.getAllCharacters();
            assertEquals(3, characters.size());

            Set<String> kazuhaRoles = new HashSet<>();
            kazuhaRoles.add("Support");
            checkCharacter("Kazuha", "Anemo", kazuhaRoles, characters.get(0));

            Set<String> xilonenRoles = new HashSet<>();
            xilonenRoles.add("Support");
            checkCharacter("Xilonen", "Geo", xilonenRoles, characters.get(1));

            Set<String> lyneyRoles = new HashSet<>();
            lyneyRoles.add("Main DPS");
            checkCharacter("Lyney", "Pyro", lyneyRoles, characters.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }
}
