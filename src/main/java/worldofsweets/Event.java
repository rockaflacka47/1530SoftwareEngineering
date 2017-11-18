package worldofsweets;

import java.util.*;
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
            String stringInput = JOptionPane.showInputDialog(null, "Please enter the number of players (1-4):", "World of Sweets", JOptionPane.PLAIN_MESSAGE);
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
                computerPlayer = false;
                return numberOfPlayers;
            }
            else if(numberOfPlayers == 1){
                computerPlayer = true;
                return 2;

            }
            else{
                error("The value entered was not between 2 and 4, inclusive.", false);
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
                        // Go to middle card
                        player.setLocation(24);
                    }
                    else if(card.getValue() == 4){
                       // Skip turn card
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

}
