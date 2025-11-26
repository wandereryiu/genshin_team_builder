# My Personal Project

## Project Proposal
For CPSC 210, I want to focus my project on building a desktop application that focuses on team building mechanics in the popular game, *Genshin Impact*. In order to fully utilize every element of team building that exists in the game, the application should allow users to keep track of characters that they already have, and build teams based on elemental resonance and team compositions, which comprise of different roles. If possible, I would also like to implement a tracker for talent ascension materials that displays what days of the week they can be attained, and add a wishlist for any character(s) a user is thinking about obtaining in the future.

This application aims to help any veterans and new players to the game to make their gaming experience more enjoyable.


### *Personal Anecdote*
This project is of personal interest to me because Genshin Impact is a game I have been playing frequently for the past year, and having a tool that helps with team building would have made things a lot easier for me.

## User Stories
- As a user, I want to be able to add characters that I have obtained to my roster along with their assigned element and roles. 

- As a user, I want to be able to view all the characters that I currently have

- As a user, I want to be able to remove a character from my original list of attained characters

- As a user, I want to be able to filter characters based on their element or roles

- As a user, I want to be able to build team compositions based on elemental resonance and/or different roles

- As a user, I want to be able to save my Character Archive that contains all the characters I added 

- As a user, I want to be able to load my Character Archive from the last time I used it

### *Tentative To-Do List*
- As a user, I want to be able to add and keep track of what talent ascension materials I need, including what days of the week I can farm them

- As a user, I want to be able add characters I wish to obtain in the future into a wishlist

<br>

# Instructions for End User
- You can view the visual component upon application startup

- You can view all characters that have been added to your Character Archive by clicking "View Character Archive"

- You can add a character along with their assigned element and roles to your Character Archive by clicking "Add character"

- You can remove a chracter from your Character Archive by clicking "Remove character"

- You can build a team composition by clicking "Build team composition"

- You can save the current state of the application by clicking "Save Character Archive"

- You can reload the previously saved state of the application by clicking "Load saved file"

## Phase 4: Task 2

Tue Nov 25 23:15:31 PST 2025
Support role(s) added for Ineffa

Tue Nov 25 23:15:31 PST 2025
Sub-DPS role(s) added for Ineffa

Tue Nov 25 23:15:31 PST 2025
Ineffa successfuly added to archive.

Tue Nov 25 23:15:31 PST 2025
Neuvilette has been created!

Tue Nov 25 23:15:31 PST 2025
Main-DPS role(s) added for Neuvilette

Tue Nov 25 23:15:31 PST 2025
Neuvilette successfuly added to archive.

Tue Nov 25 23:15:31 PST 2025
Furina has been created!

Tue Nov 25 23:15:31 PST 2025
Support role(s) added for Furina

Tue Nov 25 23:15:31 PST 2025
Sub-DPS role(s) added for Furina

Tue Nov 25 23:15:31 PST 2025
Furina successfuly added to archive.

Tue Nov 25 23:15:31 PST 2025
Viewed all characters in the Character Archive.

Tue Nov 25 23:15:31 PST 2025
Viewed all characters in the Character Archive.

Tue Nov 25 23:15:51 PST 2025
Nefer has been created!

Tue Nov 25 23:15:51 PST 2025
Main DPS role(s) added for Nefer

Tue Nov 25 23:15:51 PST 2025
Nefer successfuly added to archive.

Tue Nov 25 23:15:54 PST 2025
Viewed all characters in the Character Archive.

Tue Nov 25 23:15:55 PST 2025
Viewed all characters in the Character Archive.

Tue Nov 25 23:15:56 PST 2025
Elemental reactions calculated.

Tue Nov 25 23:15:56 PST 2025
Viewed all characters in the Character Archive.

Tue Nov 25 23:15:56 PST 2025
Viewed all characters in the Character Archive.

Tue Nov 25 23:15:56 PST 2025
Elemental reactions calculated.

Tue Nov 25 23:15:57 PST 2025
Viewed all characters in the Character Archive.

Tue Nov 25 23:15:57 PST 2025
Viewed all characters in the Character Archive.

Tue Nov 25 23:15:57 PST 2025
Elemental reactions calculated.

Tue Nov 25 23:15:57 PST 2025
Elemental reactions calculated.

Tue Nov 25 23:15:59 PST 2025
Viewed all characters in the Character Archive.

Tue Nov 25 23:15:59 PST 2025
Elemental reactions calculated.

Tue Nov 25 23:16:00 PST 2025
Viewed all characters in the Character Archive.

Tue Nov 25 23:16:00 PST 2025
Elemental reactions calculated.

Tue Nov 25 23:16:03 PST 2025
Viewed all characters in the Character Archive.

Tue Nov 25 23:16:04 PST 2025
Ineffa has been created!

Tue Nov 25 23:16:04 PST 2025
Ineffa successfully removed from archive.

Tue Nov 25 23:16:06 PST 2025
Viewed all characters in the Character Archive.

## Phase 4: Task 3

The first change I would consider upon reflection of the design presented in my UML class diagram is creating an interface or abstract class that acts as a "container" for Character instances. Since both CharacterArchive and TeamComposition are have similar methods like removeCharacter, addCharacter, and identical getter methods, I could create a superclass to reduce duplication in my application. I would also change the data structure to be a Set instead of an ArrayList, because it reduces redundancy in checking for duplicates, and the order does not matter in this context. 

Another change I would make is to try and encapsulate ElementalReactions into TeamComposition, because it is only needed in that one class. Having a separate helper class was only done to reduce method length in the beginning when I did not know as much about Java. Now that I can utilize HashMaps and Sets, I could find a way to reduce having to use helper class that does not have any associations to other classes in my diagram.

For the UI, I could probably combined AddCharacter and RemoveCharacter into a single class that manages both functions. However, this is dependent on whether users to have the add and remove features in the same panel or separate. Additionally, I noticed that the names TeamBuilderDisplay and TeamBuilderGUI are confusing names. At the time, it made sense because TeamBuilderDisplay handled the formatting of the panel while TeamBuilderGUI handled UI interactions. Upon reflection, I realized it is confusing. I could probably rename TeamBuilderDisplay to TeamCompositionUI to indicate that the class is responsible for handling the logical implementation of TeamComposition in the user interface.