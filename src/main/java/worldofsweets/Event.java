package worldofsweets;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Event implements ActionListener, java.io.Serializable{

    public ArrayList<Player> playerList;
    private ArrayList<JPanel> tileList;
    private GUI gameBoard;
    private Deck cardDeck;
    private int turnIndex;
	  private JButton button;

	public void run(){
		gameBoard = new GUI(this);
		int numberOfPlayers = getNumberOfPlayers();
        playerList = createPlayers(numberOfPlayers);
        cardDeck = new Deck();
        cardDeck.shuffle();
        turnIndex = 0;

        gameBoard.redraw(playerList, turnIndex, null);
	}

	public int getNumberOfPlayers(){
        int numberOfPlayers;
        while(true){
            String stringInput = JOptionPane.showInputDialog(null, "Please enter the number of players (2-4):", "World of Sweets", JOptionPane.PLAIN_MESSAGE);
            if(stringInput == null){
                System.exit(0);
            }
            try{
                numberOfPlayers = Integer.parseInt(stringInput);
            }catch(NumberFormatException e){
                error("The value entered was not a valid integer.", false);
                continue;
            }
            if(numberOfPlayers >= 2 && numberOfPlayers <= 4){
                return numberOfPlayers;
            }else{
                error("The value entered was not between 2 and 4, inclusive.", false);
                continue;
            }
        }
    }

    public ArrayList<Player> createPlayers(int numberOfPlayers){
    	ImageIcon[] iconList = {new ImageIcon("images/tokens/candy.png", "Candy"), new ImageIcon("images/tokens/gummybear.png", "Gummy Bear"), new ImageIcon("images/tokens/jellybean.png", "Jelly Bean"), new ImageIcon("images/tokens/lollipop.png", "Lollipop")};
    	ArrayList<Player> playerList = new ArrayList<Player>();
    	for(int i = 0; i < numberOfPlayers; i++){
    		playerList.add(new Player(null, i+1, 0, iconList[i]));
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
		if(event.getSource() == button){
            Player player = playerList.get(turnIndex);
            Card card = cardDeck.drawCard();
            if(card.getValue() == 1 || card.getValue() == 2){
                player.setLocation(findMoveLocation(player.getLocation(), card));
            }
            else if(card.getValue() == 3){
                player.setLocation(24);
            }
            else if(card.getValue() == 4){
               //nothing needs to be done because of how this method was implemented
            }
            if(player.getLocation() == 48){
                gameOver(player, card);
            }else{
                turnIndex++;
                if(turnIndex >= playerList.size()){
                    turnIndex = 0;
                }
                gameBoard.redraw(playerList, turnIndex, card);
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
}
