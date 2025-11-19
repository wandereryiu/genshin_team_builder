package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

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
    private JPanel mainPanel;

    // EFFECTS: sets up window in which Genshin Impact Team Builder will execute
    public TeamBuilderGUI() throws FileNotFoundException {
        super("Genshin Impact Team Builder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setSize(getPreferredSize());
        setLayout(new BorderLayout());
        setVisible(true);
        setLocationRelativeTo(null);

        add(menuPanel(), BorderLayout.SOUTH);
        add(mainPanel(), BorderLayout.CENTER);
    }

    // EFFECTS: creates a menu panel with buttons
    private JPanel menuPanel() {
        JPanel panel = new JPanel();

        JButton loadButton = new JButton("Load saved file");
        JButton saveButton = new JButton("Save Character Archive");
        JButton viewButton = new JButton("View Character Archive");
        JButton addButton = new JButton("Add a character");
        JButton removeButton = new JButton("Remove character");
        JButton buildButton = new JButton("Build a team composition");

        return panel;
    }

    // EFFECTS: creates the main panel where all content will be displayed
    private JPanel mainPanel() {
        mainPanel = new JPanel();

        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
