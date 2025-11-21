package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.CharacterArchive;
import model.TeamComposition;

// Represents the window where user will build their team composition
@ExcludeFromJacocoGeneratedReport
public class TeamBuilderDisplay extends JPanel {
    private CharacterArchive archive;
    private TeamComposition team;
    private JComboBox<String> characterBox;
    private JButton addButton;
    private JButton removeButton;
    private JTextArea teamComp;

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
        JPanel panel = new JPanel(new FlowLayout());

        characterBox = new JComboBox<>();
        panel.add(characterBox);

        addButton = new JButton("Add to team");
        removeButton = new JButton("Remove from team");


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

    }

    // MODIFIES: this
    // EFFECTS: adds selected character to current team composition
    private void addCharacterToTeam() {

    }

    // MODIFIES: this
    // EFFECTS: removes selected character from current team composition
    private void removeCharacterFromTeam() {

    }

    // MODIFIES: this
    // EFFECTS: updates display to show current team composition
    private void updateTeam() {

    }





}
