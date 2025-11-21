package ui;

import model.Character;
import model.CharacterArchive;

import javax.swing.*;
import java.awt.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

/*
*
* References:
* https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html\
* https://docs.oracle.com/javase/tutorial/java/data/buffers.html
* 
*/

// Represents a display for the Character Archive 
@ExcludeFromJacocoGeneratedReport
public class CharacterArchivePanel extends JPanel {
    private JTextArea display;

    // EFFECTS: constructs new panel which displays all existing characters in
    // Character Archive
    public CharacterArchivePanel(CharacterArchive archive) {
        setLayout(new BorderLayout());
        display = new JTextArea();
        display.setEditable(false);
        display.setFont(new Font("Dialog", Font.PLAIN, 14));
        add(new JScrollPane(display), BorderLayout.EAST);

        updateDisplay(archive);
    }

    // MODIFIES: this
    // EFFECTS: updates Charcater Archive display everytime a change is made to it
    public void updateDisplay(CharacterArchive archive) {
        StringBuilder characterInfo = new StringBuilder();

        for (Character c : archive.getAllCharacters()) {
            characterInfo.append(c.getName()).append(" | Element: ").append(c.getElement())
                    .append(" | Roles: ").append(c.getRoles()).append("\n");
        }

        display.setText(characterInfo.toString());
    }
}
