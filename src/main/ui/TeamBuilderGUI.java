package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Character;
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

    // EFFECTS: sets up window in which Genshin Impact Team Builder will execute
    public TeamBuilderGUI() throws FileNotFoundException {
        super("Genshin Impact Team Builder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setSize(800,600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // TODO: Set aesthetics if there is time to do later 
        // image = new ImageIcon();
        // label = new JLabel();
        // label.setText("");
        // label.setIcon(image);
        // add(label, BorderLayout.NORTH);

        add(menuPanel(), BorderLayout.SOUTH);
        add(mainPanel(), BorderLayout.CENTER);

        setVisible(true);
        // repaint();
        // revalidate();
    }

    // EFFECTS: creates a menu panel with buttons 
    //          located at the bottom of the application
    private JPanel menuPanel() {
        JPanel panel = new JPanel();

        JButton loadButton = new JButton("Load saved file");
        JButton saveButton = new JButton("Save Character Archive");
        JButton viewButton = new JButton("View Character Archive");
        JButton addButton = new JButton("Add a character");
        JButton removeButton = new JButton("Remove character");
        JButton buildButton = new JButton("Build a team composition");

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

    // EFFECTS: creates the main panel where all content will be displayed
    private JPanel mainPanel() {
        mainPanel = new JPanel();

        return mainPanel;
    }

    // EFFECTS: performs action when user selects corresponding button
    @Override
    public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");

    }

     // EFFECTS: saves the current Character Archive to file
    private void saveCharacterArchive() {
        try {
            jsonWriter.open();
            jsonWriter.write(archive);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads previously saved Character Archive from file
    private void loadCharacterArchive() {
        try {
            archive = jsonReader.read();
            // System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            // System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
