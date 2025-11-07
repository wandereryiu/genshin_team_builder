package persistence;

import model.Character;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// Referenced from JSonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    protected void checkCharacter(String name, String element, Set<String> roles, Character character) {
        assertEquals(name, character.getName());
        assertEquals(element, character.getElement());
        assertEquals(roles, character.getRoles());
    }
}
