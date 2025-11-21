package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.CharacterArchive;
import model.TeamComposition;
import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * 
 * References:
 * https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
 * https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html
 * https://docs.oracle.com/javase/7/docs/api/java/awt/BorderLayout.html
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
 * https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
 * https://stackoverflow.com/questions/2935232/show-animated-gif
 * 
 */

@ExcludeFromJacocoGeneratedReport
public class TeamBuilderGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/archive.json";
    private CharacterArchive archive;
    private TeamComposition team;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel mainPanel;
    // private JLabel label;
    // private ImageIcon image;

    private JButton loadButton;
    private JButton saveButton;
    private JButton viewButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton buildButton;

    // EFFECTS: sets up window in which Genshin Impact Team Builder will execute
    public TeamBuilderGUI() throws FileNotFoundException {
        super("Genshin Impact Team Builder");

        archive = new CharacterArchive();
        team = new TeamComposition();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setSize(1200, 800);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        // TODO: Add welcome screen visuals
        // image = new ImageIcon();
        // label = new JLabel();
        // label.setText("");
        // label.setIcon(image);
        // add(label, BorderLayout.NORTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        add(menuPanel(), BorderLayout.SOUTH);
        add(mainPanel(), BorderLayout.CENTER);

        showHomeDisplay();

        setVisible(true);
        // repaint();
        // revalidate();
    }

    // EFFECTS: creates a menu panel with buttons
    private JPanel menuPanel() {
        JPanel panel = new JPanel();

        loadButton = new JButton("Load saved file");
        saveButton = new JButton("Save Character Archive");
        viewButton = new JButton("View Character Archive");
        addButton = new JButton("Add character");
        removeButton = new JButton("Remove character");
        buildButton = new JButton("Build a team composition");

        panel.add(loadButton);
        panel.add(saveButton);
        panel.add(viewButton);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(buildButton);

        loadButton.addActionListener(this);
        saveButton.addActionListener(this);
        viewButton.addActionListener(this);
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        buildButton.addActionListener(this);

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: changes display
    private void switchDisplay(JPanel screen) {
        mainPanel.removeAll();
        mainPanel.add(screen, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: changes display back to default home screen
    private void showHomeDisplay() {
        switchDisplay(new JPanel());
    }

    // EFFECTS: creates the main panel where all content will be displayed
    private JPanel mainPanel() {
        mainPanel = new JPanel();

        return mainPanel;
    }

    // EFFECTS: performs action when user selects corresponding button
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // if (archive.getAllCharacters().isEmpty()) {
        //     buildButton.setEnabled(false);
        //     removeButton.setEnabled(false);
        //     viewButton.setEnabled(false);
        // }

        if (source == loadButton) {
            loadCharacterArchive();
            team = new TeamComposition();
            switchDisplay(new CharacterArchivePanel(archive));
        } else if (source == saveButton) {
            saveCharacterArchive();
        } else if (source == viewButton) {
            switchDisplay(new CharacterArchivePanel(archive));
        } else if (source == buildButton) {
            switchDisplay(new TeamBuilderDisplay(archive, team));
        } else if (source == addButton || source == removeButton) {
            switchDisplay(new CharacterPanel(archive));
        }
    }

    // EFFECTS: saves the current Character Archive to file
    private void saveCharacterArchive() {
        try {
            jsonWriter.open();
            jsonWriter.write(archive);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Character archive has been saved.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads previously saved Character Archive from file
    private void loadCharacterArchive() {
        try {
            archive = jsonReader.read();
            team = new TeamComposition();
            switchDisplay(new CharacterArchivePanel(archive));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
    }

}
