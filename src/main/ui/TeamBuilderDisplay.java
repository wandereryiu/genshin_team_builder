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
    private TeamComposition team;
    private JComboBox<String> characterBox;
    private JButton addButton;
    private JButton removeButton;
    private JTextArea teamComp;

    public TeamBuilderDisplay(CharacterArchive archive, TeamComposition team) {
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
        JPanel panel = new JPanel(new FlowLayout());

        characterBox = new JComboBox<>();
        panel.add(characterBox);

        addButton = new JButton("Add to team");
        removeButton = new JButton("Remove from team");

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

        for (Character c: team.getAllCharacters()) {
            if (!(team.getAllCharacters().contains(c))) {
                characterBox.addItem(c.getName());
            }
        }
        addButton.setEnabled(characterBox.getItemCount() > 0 && team.size() < 4);
        removeButton.setEnabled(team.size() > 0);

    }

    // MODIFIES: this
    // EFFECTS: adds selected character to current team composition
    private void addCharacterToTeam() {
        String name = (String) characterBox.getSelectedItem();

        if (name == null) {
            return;
        }

        Character selected = null;
        for (Character c: team.getAllCharacters()) {
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
        String name = (String) characterBox.getSelectedItem();

        if (name == null) {
            return;
        }

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
            teamInfo.append(c.getName()).append(" ⋆˚࿔ Element: ")
                    .append(" ⋆˚࿔ Roles: ").append(c.getRoles())
                    .append("\n");
        }

        teamInfo.append("\nRoles Present:\n");
        for (String role: team.getRolesPresent()) {
            teamInfo.append(" ᛝ ").append(role).append(" ᛝ ");
        }

        teamInfo.append("\nPossible Elemental Reactions:\n");
        Set<String> elementalReactions = team.getElementalReactions();
        if (elementalReactions.isEmpty()) {
            teamInfo.append("There are no possible elemental reactions in this team.\n");
        } else {
            for (String reaction: elementalReactions) {
                teamInfo.append(" ⟡ ").append(reaction).append(" ⟡ ");
            }
        }

        teamComp.setText(teamInfo.toString());

    }
}
