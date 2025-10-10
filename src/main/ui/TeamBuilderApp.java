package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.Character;
import model.CharacterArchive;
import model.TeamComposition;

/*
 * 
 * 
 * 
 * Code from TellerApp provided in EdX Project Phase 1 page
 * 
 * 
 * 
 */

// Team builder application
public class TeamBuilderApp {
    private CharacterArchive archive;
    private TeamComposition team;
    private Scanner input;

    // EFFECTS: runs the team builder application
    public TeamBuilderApp() {
        runTeamBuilder();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTeamBuilder() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThe application will now end.");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addCharacterToArchive();
        } else if (command.equals("r")) {
            removeCharacterFromArchive();
        } else if (command.equals("v")) {
            viewAllCharacters();
        } else if (command.equals("f")) {
            filterCharacters();
        } else if (command.equals("t")) {
            buildTeam();
        } else {
            System.out.println("Invalid Selection");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes archive, team, and scanner
    private void init() {
        archive = new CharacterArchive();
        team = new TeamComposition();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    // EFFECTS: displays menu to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add character to Character Archive");
        System.out.println("\tr -> Remove character from Character Archive");
        System.out.println("\tv -> View all characters in Character Archive");
        System.out.println("\tf -> Filter characters by element or role");
        System.out.println("\tt -> Build a team");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a character to the archive
    private void addCharacterToArchive() {
        System.out.print("Enter character name: ");
        String name = input.next();
        System.out.print("Enter element (Anemo, Geo, Electro, Dendro, Hydro, Pyro, Cryo): ");
        String element = input.next();

        Character character = new Character(name, element);

        System.out.print("Enter roles (separated by commas): ");
        String rolesLine = input.next();
        String[] roles = rolesLine.split(",");
        for (String role : roles) {
            character.addRole(role.trim());
        }

        if (archive.addCharacter(character)) {
            System.out.println(name + " added to Character Archive.");
        } else {
            System.out.println("Character already exists in Character Archive.");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a character from the archive
    private void removeCharacterFromArchive() {
        System.out.print("Enter character name to remove: ");
        String name = input.next();

        Character toRemove = new Character(name, "");
        boolean removed = archive.removeCharacter(toRemove);

        if (removed) {
            System.out.println(name + " was removed from Character Archive.");
        } else {
            System.out.println(name + " not found in Character Archive.");
        }
    }

    // EFFECTS: displays all characters in Character Archive
    private void viewAllCharacters() {
        System.out.println("\nCharacters in archive:");
        for (Character c : archive.getAllCharacters()) {
            System.out.println(c.getName() + " || Element: " + c.getElement() + " || Role(s): " + c.getRoles());
        }
    }

    // EFFECTS: filters characters by element or role
    private void filterCharacters() {
        System.out.print("Filter by element: ");
        String element = input.next();
        System.out.print("Filter by role: ");
        String role = input.next();

        ArrayList<Character> filteredCharacters = archive.getAllCharacters();

        if (!element.isEmpty()) {
            filteredCharacters = archive.filterByElement(element);
        } else if (!role.isEmpty()) {
            filteredCharacters = archive.filterByRole(role);
        } else {
            filteredCharacters = archive.getAllCharacters();
        }

        System.out.println("\n Characters: \n");
        for (Character c: filteredCharacters) {
            System.out.println(c.getName() + " || Element: " + c.getElement() + " || Roles: " + c.getRoles());
        }
    }

    // MODIFIES: this
    // EFFECTS: builds a team and shows elemental reactions and roles present
    private void buildTeam() {
        team = new TeamComposition();
        System.out.println("Build a team:");

        addCharactersToTeam();
        viewTeamComposition();
        viewRolesPresent();
        viewElementalReactions();
    }

    // MODIFIES: this
    // EFFECTS: adds character to team if the team is not yet full and if the
    //          character exists in the Character Archive
    private void addCharactersToTeam() {
        while (team.size() < 4) {
            System.out.print("Enter character name to add (or 'done' to finish): ");
            String name = input.next();
            if (name.equalsIgnoreCase("done")) {
                break;
            }

            Character selected = findCharacterInArchive(name);
            if (selected != null) {
                if (team.addCharacter(selected)) {
                    System.out.println(selected.getName() + " added to team.");
                } else {
                    System.out.println("Character already in team or team is full.");
                }
            } else {
                System.out.println("Character not found in Character Archive.");
            }
        }
    }

    // EFFECTS: returns character in Character archive if it currently exists,
    // otherwise returns nothing
    private Character findCharacterInArchive(String name) {
        for (Character c : archive.getAllCharacters()) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    // EFFECTS: prints out all chararacters and their name, element, and assigned roles
    private void viewTeamComposition() {
        System.out.println("\nCurrent Team:");
        for (Character c : team.getAllCharacters()) {
            System.out.println(c.getName() + " ~ Element: " + c.getElement() + " ~ Roles: " + c.getRoles());
        }
    }

    // EFFECTS: prints out all characters' roles in the team, without duplicates
    private void viewRolesPresent() {
        System.out.println("\nRoles present:");
        for (String role : team.getRolesPresent()) {
            System.out.println("- " + role);
        }
    }

    // EFFECTS: prints out all elements in the team, without duplicates
    private void viewElementalReactions() {
        System.out.println("\nElemental reactions possible:");
        for (String reaction : team.getElementalReactions()) {
            System.out.println("- " + reaction);
        }
    }
}
