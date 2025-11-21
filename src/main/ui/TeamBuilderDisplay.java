package ui;

import model.Character;
import model.CharacterArchive;
import model.TeamComposition;

import java.util.Set;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

/*
*
* References:
* https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html\
* https://docs.oracle.com/javase/tutorial/java/data/buffers.html
* 
*/

// Represents the window where user will build their team composition
@ExcludeFromJacocoGeneratedReport
public class TeamBuilderDisplay extends JPanel {
    private CharacterArchive archive;
    private TeamComposition team;
    private JComboBox<String> characterBox;
    private JComboBox<String> removeBox;
    private JButton addButton;
    private JButton removeButton;
    private JButton clearButton;
    private JTextArea teamComp;

    // EFFECTS: constructs a new panel where user can build their team composition
    public TeamBuilderDisplay(CharacterArchive archive, TeamComposition team) {
        this.archive = archive;
        this.team = team;

        setLayout(new BorderLayout());

        add(createSelectionPanel(), BorderLayout.NORTH);
        add(teamCompositionDisplay(), BorderLayout.CENTER);

        updateCharacterOptions();
        updateTeam();
    }

    // EFFECTS: creates panel containing options for useres to add or
    //          remove characters from current team composition
    public JPanel createSelectionPanel() {
        JPanel panel = new JPanel(new GridLayout(3,3, 5,5));


        characterBox = new JComboBox<>();
        panel.add(characterBox);

        addButton = new JButton("Add to team");
        removeButton = new JButton("Remove from team");

        removeBox = new JComboBox<>();
        panel.add(removeBox);
        
        clearButton = new JButton("Clear team");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCharacterToTeam();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCharacterFromTeam();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (!(team.getAllCharacters().isEmpty())) {
                    Character c = team.getAllCharacters().get(0);
                    team.removeCharacter(c);
                }
                updateCharacterOptions();
                updateTeam();
            }
        });

        panel.add(addButton);
        panel.add(removeButton);

        return panel;
    }

    // EFFECTS: displays current team composition
    private JScrollPane teamCompositionDisplay() {
        teamComp = new JTextArea();
        teamComp.setEditable(false);
        JScrollPane scroll = new JScrollPane(teamComp);
        scroll.setBorder(BorderFactory.createTitledBorder("Team Composition"));
        return scroll;
    }

    // MODIFIES: this
    // EFFECTS: updates character options that can be added or removed
    //          from the current team composition
    private void updateCharacterOptions() {
        characterBox.removeAllItems();
        removeBox.removeAllItems();

        for (Character c: archive.getAllCharacters()) {
            if (!(team.getAllCharacters().contains(c))) {
                characterBox.addItem(c.getName());
            }
        }

        for (Character c: team.getAllCharacters()) {
            removeBox.addItem(c.getName());
        }

        addButton.setEnabled(characterBox.getItemCount() > 0 && team.size() < 4);
        removeButton.setEnabled(team.size() > 0);

    }

    // MODIFIES: this
    // EFFECTS: adds selected character to current team composition
    private void addCharacterToTeam() {
        String name = (String) characterBox.getSelectedItem();

        Character selected = null;
        for (Character c: archive.getAllCharacters()) {
            if (c.getName().equals(name)) {
                selected = c;
                break;
            }
        }

        if (selected != null) {
            if (team.addCharacter(selected)) {
                updateCharacterOptions();
                updateTeam();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes selected character from current team composition
    private void removeCharacterFromTeam() {
        String name = (String) removeBox.getSelectedItem();

        Character toRemove = null;
        for (Character c: team.getAllCharacters()) {
            if (c.getName().equals(name)) {
                toRemove = c;
                break;
            }
        }

        if (toRemove != null) {
            team.removeCharacter(toRemove);
            updateCharacterOptions();
            updateTeam();
        }
    }

    // MODIFIES: this
    // EFFECTS: updates display to show current team composition
    private void updateTeam() {
        StringBuilder teamInfo = new StringBuilder();

        teamInfo.append("Current Team:\n");
        for (Character c : team.getAllCharacters()) {
            teamInfo.append(c.getName()).append(" | Element: ")
                    .append(" | Roles: ").append(c.getRoles())
                    .append("\n");
        }

        teamInfo.append("\nRoles Present:\n");
        for (String role: team.getRolesPresent()) {
            teamInfo.append(role).append("\n");
        }

        teamInfo.append("\nPossible Elemental Reactions:\n");
        Set<String> elementalReactions = team.getElementalReactions();
        if (elementalReactions.isEmpty()) {
            teamInfo.append("There are no possible elemental reactions in this team.\n");
        } else {
            for (String reaction: elementalReactions) {
                teamInfo.append(" ⟡ ").append(reaction).append(" ⟡ ").append("\n");
            }
        }

        teamComp.setText(teamInfo.toString());

    }
}
