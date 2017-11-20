package worldofsweets;

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;

public class Event implements ActionListener{

    private ArrayList<Player> playerList;
    private ArrayList<JPanel> tileList;
    private GUI gameBoard;
    private Deck cardDeck;
    private int turnIndex;
    private Timer timer;
    private JButton button;
    private boolean computerPlayer;
    private Card currCard = null; 
    

	public void run(){
		gameBoard = new GUI(this);
		int numberOfPlayers = getNumberOfPlayers();
        playerList = createPlayers(numberOfPlayers);

        cardDeck = new Deck();
        cardDeck.shuffle();
        turnIndex = 0;

        if(!computerPlayer){
            gameBoard.redraw(playerList, turnIndex, null);
        }else{
            gameBoard.redraw(playerList, 1, null);
        }
        
	}

	public int getNumberOfPlayers(){
        int numberOfPlayers;
        while(true){
            String stringInput = JOptionPane.showInputDialog(null, "Please enter the number of players (1-4):", "World of Sweets", JOptionPane.PLAIN_MESSAGE);
            try{
                if(stringInput == null){

                    return -1;
                }
                numberOfPlayers = Integer.parseInt(stringInput);
            }catch(NumberFormatException e){
                error("The value entered was not a valid integer.", false);
                continue;
            }
            
            if(numberOfPlayers >= 2 && numberOfPlayers <= 4){
                computerPlayer = false;
                gameBoard.startTimer();
                return numberOfPlayers;
            }
            else if(numberOfPlayers == 1){
                computerPlayer = true;
                gameBoard.startTimer();
                return 2;
            }
            else{
                error("The value entered was not between 1 and 4, inclusive.", false);
                continue;
            }
        }
    }

    public ArrayList<Player> createPlayers(int numberOfPlayers){
    	ImageIcon[] iconList = {new ImageIcon("images/tokens/candy.png", "Candy"), new ImageIcon("images/tokens/gummybear.png", "Gummy Bear"), new ImageIcon("images/tokens/jellybean.png", "Jelly Bean"), new ImageIcon("images/tokens/lollipop.png", "Lollipop")};
    	ArrayList<Player> playerList = new ArrayList<Player>();
        if(!computerPlayer){
        	for(int i = 0; i < numberOfPlayers; i++){
        		playerList.add(new Player(null, i+1, 0, iconList[i]));
        	}
        }
        else{
            playerList.add(new Player("Computer", 1, 0, iconList[0]));
            playerList.add(new Player(null, 2, 0, iconList[1]));
        }
    	return playerList;
    }

    public void error(String errorMsg, boolean isFatal){
    	if(isFatal){
    		errorMsg += "\nThe game will now exit.";
        }

        JOptionPane.showMessageDialog(null, errorMsg, "World of Sweets", JOptionPane.WARNING_MESSAGE);

        if(isFatal){
    		System.exit(0);
    	}
    }

    public void setButton(JButton button){
		this.button = button;
	}

    public void setTileList(ArrayList<JPanel> tileList){
        this.tileList = tileList;
    }

	public void actionPerformed(ActionEvent event){

        int turns = 0;
        if(!computerPlayer){
            turns = 1; 
        }else{
            //adds in computers turn
            turns = 2;
        }

		if(event.getSource() == button){
            Player player;
            Card card;

            for(int i = 0; i < turns; i++)
            {
                player = playerList.get(turnIndex);
                card = cardDeck.drawCard();
                if(card.getValue() == 1 || card.getValue() == 2){
                    player.setLocation(findMoveLocation(player.getLocation(), card));
                }
                else if(card.getValue() == 3){
                    // Go to licorice card
                    player.setLocation(10);
                }
                else if(card.getValue() == 4){
                   // go to ice cream card
                   player.setLocation(18);
                }
                else if(card.getValue() == 5){
                    // Go to cake card
                    player.setLocation(26);
                }
                else if(card.getValue() == 6){
                   // go to soda card
                   player.setLocation(34);
                }
                else if(card.getValue() == 7){
                    // Go to pie card
                    player.setLocation(42);
                }
                else if(card.getValue() == 8){
                   // Skip turn card
                }
                if(player.getLocation() == 48){
                    gameOver(player, card);
                }else{
                    turnIndex++;
                    if(turnIndex >= playerList.size()){
                        turnIndex = 0;
                    }
                    if(!computerPlayer){
                        gameBoard.redraw(playerList, turnIndex, card);  
                    }else{
                        gameBoard.redraw(playerList, 1, card);
                    }
                }
            }
        }	
	}

    private int findMoveLocation(int location, Card card){
        Color color = card.getColor();
        int value = card.getValue();
        int counter = 0;

        for(int i = location+1; i < tileList.size(); i++){
            Color panelColor = tileList.get(i).getBackground();
            if(color == panelColor){
                counter++;
            }
            if(counter == value){
                return i;
            }
        }

        return 48;
    }

    private void gameOver(Player player, Card card){
        button.setEnabled(false);
        gameBoard.redraw(playerList, turnIndex, card);
        gameBoard.stopTimer();
        JOptionPane.showMessageDialog(null, player.getName() + " has won the game!", "We have a winner...", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }


    //TODO: This should be updated, need to compare with a couple players
    public boolean equals(Event e) {
      if (e.playerList == null && this.playerList == null) {
        return true;
      } else if (e.playerList.equals(this.playerList)) {
        return true;
      } else {
        return false;
      }
    }

    public void saveGame() {
      try {
         FileOutputStream fileOut =
         new FileOutputStream("./savedgames/savedPlayers.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(playerList);
         out.close();
         fileOut.close();

         fileOut = new FileOutputStream("./savedgames/savedDeck.ser");
         out = new ObjectOutputStream(fileOut);
         out.writeObject(cardDeck);
         out.close();
         fileOut.close();

         //TODO: DALTON MAKE SURE TURN INDEX ISN't INITed to NULL
         fileOut = new FileOutputStream("./savedgames/savedTurn.ser");
         out = new ObjectOutputStream(fileOut);
         out.writeObject(new Integer(turnIndex));
         out.close();
         fileOut.close();

         if (currCard != null) {
           fileOut = new FileOutputStream("./savedgames/savedCurrCard.ser");
           out = new ObjectOutputStream(fileOut);
           out.writeObject(currCard);
           out.close();
           fileOut.close();
         }
      } catch (IOException i) {
         i.printStackTrace();
      } catch (NullPointerException n) {
        //TODO DALTON - take this catch out if you make sure there isn't a null
        System.out.println("No Game Saved, No one has moved.");
      }
      System.out.println("Saved Game");
    }

    public void loadGame() {
      try {
         FileInputStream fileIn =
         new FileInputStream("./savedgames/savedPlayers.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         playerList = (ArrayList<Player>) in.readObject();
         in.close();
         fileIn.close();

         fileIn = new FileInputStream("./savedgames/savedDeck.ser");
         in = new ObjectInputStream(fileIn);
         cardDeck = (Deck) in.readObject();
         in.close();
         fileIn.close();

         fileIn = new FileInputStream("./savedgames/savedTurn.ser");
         in = new ObjectInputStream(fileIn);
         turnIndex = (Integer) in.readObject();
         in.close();
         fileIn.close();

         //TODO: DALTON: Make sure that turnIndex isn't null but starts at 0 (or 1 and you can change this)
         if (turnIndex != 0) {
           fileIn = new FileInputStream("./savedgames/savedCurrCard.ser");
           in = new ObjectInputStream(fileIn);
           currCard = (Card) in.readObject();
           in.close();
           fileIn.close();
         }
         gameBoard.redraw(playerList, turnIndex, currCard);

      } catch (FileNotFoundException i) {
        //TODO add prompt to UI
         System.out.println("No games to load");
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Other Exception");
      }
      System.out.println("Loaded Game");

    }

}
