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

// Represents panel where user can remove or add character
@ExcludeFromJacocoGeneratedReport
public class AddCharacter extends JPanel {
    private CharacterArchive archive;
    private JButton addButton;
    private JTextField nameField;
    private JTextField elementField;
    private JTextField rolesField;

    // EFFECTS: constructs new panel for add and remove functionality
    public AddCharacter(CharacterArchive archive) {
        this.archive = archive;
        setLayout(new BorderLayout());

        addButton = new JButton("Add character");

        add(createAddPanel(), BorderLayout.NORTH);

        attachListeners();
    }

    
    // MODIFIES: this
    // EFFECTS: constructs a JPanel for entering new character information
    private JPanel createAddPanel() {
        JPanel addPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        addPanel.setBorder(BorderFactory.createTitledBorder("Add characters"));

        addPanel.add(new JLabel("Character Name:"));
        nameField = new JTextField();
        addPanel.add(nameField);

        addPanel.add(new JLabel("Element"));
        elementField = new JTextField();
        addPanel.add(elementField);

        addPanel.add(new JLabel("Roles: \n (separated by comma)"));
        rolesField = new JTextField();
        addPanel.add(rolesField);

        addPanel.add(new JLabel());
        addPanel.add(addButton);

        return addPanel;
    }

    // MODIFIES: this
    // EFFECTS: adds character with the given name, elements and roles to the archive
    private void addCharacter() {
        String name = nameField.getText().trim();
        String element = elementField.getText().trim();
        String rolesText = rolesField.getText().trim();

        if (name.isEmpty() || element.isEmpty() || rolesText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all the fields! <( •̀ᴖ•́)>");
            return;
        }

        Character character = new Character(name, element);

        String[] roles = rolesText.split(",");
        for (String role: roles) {
            character.addRole(role.trim());
        }

        boolean added = archive.addCharacter(character);

        if (added) {
            JOptionPane.showMessageDialog(this, name + " added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, name + " already exists in the archive.");
        }

        nameField.setText("");
        elementField.setText("");
        rolesField.setText("");
    }


    // EFFECTS: responds to add button once user clicks on it
    private void attachListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCharacter();
            }
        });
    }
}
