package worldofsweets;

import java.util.*;
import javax.swing.*;
public class Driver{
	
	static int cardsDrawn = 0;
    public static void main(String[] args){
        Deck deck = new Deck();
        deck.shuffle();

        int numberOfPlayers = getNumberOfPlayers();

        ArrayList<Player> playerList = createPlayers(numberOfPlayers);

        GUI gameBoard = new GUI(playerList, deck);
        cardsDrawn = 60;
        //reshuffleNotification(gameBoard);

    }
    
	//notification that deck is being shuffled comes up if we have drawn entire thing
	/*public static void reshuffleNotification(GUI gameBoard){
		if(cardsDrawn % 60 == 0){
			gameBoard.displayShuffleNotification("Reshuffling Deck");
		}
	}*/

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
    	ImageIcon[] iconList = {new ImageIcon("worldofsweets/images/tokens/candy.png", "Candy"), new ImageIcon("worldofsweets/images/tokens/gummybear.png", "Gummy Bear"), new ImageIcon("worldofsweets/images/tokens/jellybean.png", "Jelly Bean"), new ImageIcon("worldofsweets/images/tokens/lollipop.png", "Lollipop")};
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
}
