package worldofsweets;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Event implements ActionListener{

    private ArrayList<Player> playerList;
    private GUI gameBoard;
    private Deck cardDeck;
    private int turnIndex;
	private JButton button;

	public void run(){
		gameBoard = new GUI(this);
		int numberOfPlayers = getNumberOfPlayers();
        playerList = createPlayers(numberOfPlayers);
        cardDeck = new Deck();
        turnIndex = 0;

        gameBoard.redraw(playerList, turnIndex, new Card(Color.BLUE, 1));
	}

	public static int getNumberOfPlayers(){
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

    public static ArrayList<Player> createPlayers(int numberOfPlayers){
    	ImageIcon[] iconList = {new ImageIcon("images/tokens/candy.png", "Candy"), new ImageIcon("images/tokens/gummybear.png", "Gummy Bear"), new ImageIcon("images/tokens/jellybean.png", "Jelly Bean"), new ImageIcon("images/tokens/lollipop.png", "Lollipop")};
    	ArrayList<Player> playerList = new ArrayList<Player>();
    	for(int i = 0; i < numberOfPlayers; i++){
    		playerList.add(new Player(null, i+1, 0, iconList[i]));
    	}
    	return playerList;
    }

    public static void error(String errorMsg, boolean isFatal){
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

	public void actionPerformed(ActionEvent event){
		if(event.getSource() == button){
			System.out.println("Test");
			// Place all logic here
		}
	}

}
