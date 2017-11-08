# Manual Tests

## Testing Event.java and GUI.java

### Number of Player Input
1.	Enter 2 in "...number of players..." box, expect two players to show up on the GUI.
2.	Enter 4 in "...number of players..." box, expect four players to show up on the GUI.
3. 	Enter 100 in "...number of players..." box, expect an error message.
4.	Enter 1 in "...number of players..." box, expect an error message.
5.	Enter "test" in "...number of players..." box, expect an error message.
6. 	Enter "" in "...number of players..." box, expect an error message.

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
1.  Enter 4 in "... number of players..." box.
2.  Press "Draw A Card" button until a 'Skip Turn' card comes up.
3.  Observe that the player icon did not move on the game board.

### 'Go To Middle' Card
1.  Enter 4 in "... number of players..." box.
2.  Press "Draw A Card" button until a 'Go To Middle' card comes up.
3.  Observe that the player icon moves to the middle of the board (denoted by a green square with a white border).
