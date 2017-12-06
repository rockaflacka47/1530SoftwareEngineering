# Manual Tests

## Testing Event.java and GUI.java

### Number of Player Input
1.	Enter 2 in "...number of players..." box, expect two players to show up on the GUI.
2.	Enter 4 in "...number of players..." box, expect four players to show up on the GUI.
3. 	Enter 100 in "...number of players..." box, expect an error message.
4.	Enter "test" in "...number of players..." box, expect an error message.
5. 	Enter "" in "...number of players..." box, expect an error message.

### Game Board
1.	Enter 4 in "...number of players..." box.
2.	Observe the board, expect to see the colors repeat in a red, yellow, blue, green, orange pattern.
3. 	Observe the board, expect >= 40 colored tiles.
4. 	Observe start tile, expect to see 4 icons.

### Player Turn Area and Key Area
1.	Enter 4 in "...number of players..." box.
2.	Press "Draw A Card" button.
3.	Observe player turn area, expect to see "Player 2's Turn".
5.	Observe key area, expect to see Player 2's icon on a white field.
6.	Press "Draw A Card" button 3 times.
7.	Observe the player turn area, expect to see "Player 1's Turn".
8.	Observe key area, expect to see Player 1's icon on a white field.

### Deck Area and Button Area
1.	Enter 4 in "...number of players..." box.
2.	Press "Draw A Card" button.
3.	Observe deck area, expect to see a card back on the left and a card face on the right.

### Complete Game
1.	Enter 4 in "...number of players..." box.
2.	Press "Draw A Card" button until an icon reaches the end tile, expect "We have a winner..." message.
3. 	Press "OK" button, expect the game to exit.

### 'Skip Turn' Card
1.  Enter 4 in "...number of players..." box.
2.  Press "Draw A Card" button until a 'Skip Turn' card comes up.
3.  Observe that the player icon did not move on the game board.

### Running Timer
1.  Enter 4 in "...number of players..." box.
2.  Start timer on external source (e.g., a stopwatch).
3.  Wait 61 seconds.
4.  Hold external timer up to on screen timer so they are both in your field of view.
5.  Observe both times, expect them to be the same (+- a second due to human error).

### Playing Against an AI
1.  Enter 1 in "...number of players..." box.
2.  Observe the screen, expect to see two players show up on the game board, a computer and a user.
3.  Press "Draw A Card", expect for your token to advance as well as the computer's token to advance automatically.

### Saving and Loading a Game
##### Saving
1.  Enter 4 in "...number of players..." box.
2.  Press "Draw A Card" button 3 times.
3.  Note the location of players on the board, the current time, and current card.
4.  Click File > Save in the file menu.
5.  Observe the terminal, expect no errors to be thrown.

##### Loading
1.  Start a new game, pressing "Cancel" on the "...number of players..." box.
2.  Click File > Load in the file menu.
3.  Observe the terminal, expect no errors to be thrown.
4.  Observe the board, expect the location of players, the time, and the current card to be the same.

##### Loading without a Save
1.  Delete the file found at "savedGames/event.ser".
2.  Start a new game, pressing "Cancel" on the "...number of players..." box.
3.  Click File > Load in the file menu.
4.  Observe the screen, expect an error message.

### Boomerang
##### Test Startup Prompt
1.  Run the program, in the game mode selector dialog box, select Strategic.
2.  Enter 4 players.
3.  Press OK on each of the following dialog boxes.
4.  Observe the game board, expect to see "Boomerangs remaining: 3" below "Player 1's Turn".

##### Test Action
1.  Run the program, in the game mode selector dialog box, select Strategic.
2.  Enter 4 players.
3.  Press OK on each of the following dialog boxes.
4.  Click 'Draw a Card' four times.
5.  Click the 'Boomerang' button.
6.  Click the 'Player 2' button.
7.  Observe Player 2's position.
8.  Click 'Draw a Card'.
9.  Observe Player 2's position. If the card was a color card, Player 2 should have returned to the previous color space (or two previous if two colors). If the card was a Go To card, Player 2 should have moved to that spot on the board. If the card was a skip card, nothing should have happened.
10. Click 'Draw a Card' 3 times.
11. Observe Player 1's "Boomerangs Remaining: " section. It should say that there are two boomerangs remaining.

##### Test No More Boomerangs
1.  Run the program, in the game mode selector dialog box, select Strategic.
2.  Enter 4 players.
3.  Press OK on each of the following dialog boxes.
4.  Click the 'Boomerang' button.
5.  Click the 'Player 2' button.
6.  Click 'Draw a Card' four times.
7.  Click the 'Boomerang' button.
8.  Click the 'Player 2' button.
9.  Click 'Draw a Card' four times.
10.  Click the 'Boomerang' button.
11.  Click the 'Player 2' button.
12.  Click 'Draw a Card' four times.
13.  Observe the 'Boomerang' button is disabled and the "Boomerangs Remaining: " section displays 0.
