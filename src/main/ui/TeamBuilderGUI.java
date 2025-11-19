package ui;

import java.awt.BorderLayout;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

/*
 * References:
 * https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
 * 
 * 
 */

@ExcludeFromJacocoGeneratedReport
public class TeamBuilderGUI extends JFrame {
    private JPanel mainPanel;

    // EFFECTS: sets up window in which Genshin Impact Team Builder will execute
    public TeamBuilderGUI() {
        super("Genshin Impact Team Builder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setSize(getPreferredSize());
        setLayout(new BorderLayout());
        setVisible(true);

        add(menuPanel(), BorderLayout.SOUTH);
        add(mainPanel(), BorderLayout.CENTER);
    }

    // EFFECTS: creates a menu panel with buttons 
    private JPanel menuPanel() {
        JPanel panel = new JPanel();

        return panel;
    }

    //EFFECTS: creates the main panel where all content will be displayed
    private JPanel mainPanel() {
        mainPanel = new JPanel();

        return mainPanel;
    }

    
}
