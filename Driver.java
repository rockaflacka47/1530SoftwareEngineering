import java.util.*;
import javax.swing.*;
public class Driver{
	
	static int cardsDrawn = 0;
    public static void main(String[] args){
        Deck deck = new Deck();
        deck.shuffle();
        Scanner in = new Scanner(System.in);
        int numberOfPlayers;
        ArrayList<Player> playerList;

        numberOfPlayers = getIntegerInput(in, "Please enter the number of players (2-4): ", 2, 4);
    	playerList = createPlayers(numberOfPlayers);

		GUI gameBoard = new GUI(playerList, deck);
		cardsDrawn = 60;
		//reshuffleNotification(gameBoard);
    }


    public static int getIntegerInput(Scanner in, String prompt, int min, int max){
    	String input;
    	int inputInt;

    	while(true){
    		System.out.print(prompt);
	    	try{
	    		input = in.next();
	    		inputInt = Integer.parseInt(input);
	    	}catch(NumberFormatException e){
	    		error("The input entered is not a valid integer.", false);
	    		continue;
	    	}
	    	if(inputInt < min){
	    		error("The integer entered is less than the minimum of " + min + ".", false);
	    		continue;
	    	}else if(inputInt > max){
				error("The integer entered is greater than the maximum of " + max + ".", false);
				continue;
	    	}else{
	    		return inputInt;
	    	}
    	}
    }

	//notification that deck is being shuffled comes up if we have drawn entire thing
	/*public static void reshuffleNotification(GUI gameBoard){
		if(cardsDrawn % 60 == 0){
			gameBoard.displayShuffleNotification("Reshuffling Deck");
		}
	}*/
    public static ArrayList<Player> createPlayers(int numberOfPlayers){
    	ImageIcon[] iconList = {new ImageIcon("images/tokens/candy.png", "Candy"), new ImageIcon("images/tokens/gummybear.png", "Gummy Bear"), new ImageIcon("images/tokens/jellybean.png", "Jelly Bean"), new ImageIcon("images/tokens/lollipop.png", "Lollipop")};
    	ArrayList<Player> playerList = new ArrayList<Player>();
    	for(int i = 0; i < numberOfPlayers; i++){
    		playerList.add(new Player(null, i+1, 0, iconList[i]));
    	}
    	return playerList;
    }


    public static void error(String errorMsg, boolean isFatal){
    	System.out.println("ERROR: " + errorMsg);
    	if(isFatal){
    		System.out.println("The game will now exit.");
    		System.exit(0);
    	}
    }
}
