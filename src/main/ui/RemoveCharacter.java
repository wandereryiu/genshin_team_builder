package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.Character;
import model.CharacterArchive;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

/*
 * 
 * References:
 * https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/JComboBox.html
 * https://docs.oracle.com/javase/7/docs/api/java/awt/BorderLayout.html
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
 * https://docs.oracle.com/javase/tutorial/uiswing/events/eventsandcomponents.html
 * https://stackoverflow.com/questions/69490364/how-to-use-my-own-method-for-event-listening-in-javax-swing
 * 
 */


// Represents a panel where the user can remove previously added characters\
@ExcludeFromJacocoGeneratedReport
public class RemoveCharacter extends JPanel {
    private CharacterArchive archive;
    private JButton removeButton;
    private JComboBox<String> removeBox;

    // EFFECTS: constructs a new panel for remove character functionality
    public RemoveCharacter(CharacterArchive archive) {
        this.archive = archive;
        setLayout(new BorderLayout());

        removeButton = new JButton("Remove character");

        add(createRemovePanel(), BorderLayout.SOUTH);

        attachListeners();
    }

    // MODIFIES: this
    // EFFECTS: creates a JPanel that displays all characters with removal functionality
    private JPanel createRemovePanel() {
        JPanel removePanel = new JPanel(new BorderLayout());
        removePanel.setBorder(BorderFactory.createTitledBorder("Remove Character"));

        removeBox = new JComboBox<>();
        updateRemoveBox();
        removePanel.add(removeBox, BorderLayout.CENTER);

        removeButton = new JButton("Remove Character");
        removePanel.add(removeButton, BorderLayout.SOUTH);

        return removePanel;
    }

    // MODIFIES: this
    // EFFECTS: removes selected character from archive
    private void removeCharacter() {
        String name = (String) removeBox.getSelectedItem();

        if (name == null) {
            return;
        }

        Character temp = new Character(name, "");

        boolean removed = archive.removeCharacter(temp);

        if (removed) {
            JOptionPane.showMessageDialog(this, name + " has been removed.");
        } else {
            JOptionPane.showMessageDialog(this, "Character not found.");
        }

        updateRemoveBox();

    }

    // EFFECTS: updates the list of available characters for removal
    private void updateRemoveBox() {
        removeBox.removeAllItems();

        for (Character c: archive.getAllCharacters()) {
            removeBox.addItem(c.getName());
        }
    }

    // EFFECTS: responds to remove button once user clicks on it
    private void attachListeners() {

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCharacter();
            }
        });
    }
    

}
