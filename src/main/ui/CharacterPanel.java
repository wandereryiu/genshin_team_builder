package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.Character;
import model.CharacterArchive;

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

public class CharacterPanel extends JPanel {
    private CharacterArchive archive;
    private JButton addButton;
    private JButton removeButton;
    private JTextField nameField;
    private JTextField elementField;
    private JTextField rolesField;
    private JComboBox<String> removeBox;

    // EFFECTS: constructs new panel for given Character Archive and includes 
    //          add and remove functionality
    public CharacterPanel(CharacterArchive archive) {
        this.archive = archive;
        setLayout(new BorderLayout());

        add(createAddPanel(), BorderLayout.NORTH);
        add(createRemovePanel(), BorderLayout.SOUTH);

        attachListeners();
    }

    
    // MODIFIES: this
    // EFFECTS: constructs a JPanel for entering new character information
    private JPanel createAddPanel() {
        JPanel addPanel = new JPanel(new GridLayout());
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

        addPanel.add(new JLabel("Add another character"));
        addPanel.add(new JLabel());
        addPanel.add(addButton);

        return addPanel;
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
    // EFFECTS: adds character with the given name, elements and roles to the archive
    private void addCharacter() {
        String name = nameField.getText().trim();
        String element = elementField.getText().trim();
        String rolesText = rolesField.getText().trim();

        if (name.isEmpty() || element.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all the fields!");
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

        updateRemoveBox();

        nameField.setText("");
        elementField.setText("");
        rolesField.setText("");
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

    // EFFECTS: responds to add and remove buttons if user clicks on either
    private void attachListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCharacter();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCharacter();
            }
        });
    }

}
