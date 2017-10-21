import java.util.*;
public class Driver{
    Deck deck = new Deck();
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int numberOfPlayers;
        ArrayList<Player> playerList;

        numberOfPlayers = getIntegerInput(in, "Please enter the number of players(2-4): ", 2, 4);
    	playerList = createPlayers(numberOfPlayers);
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

    public static ArrayList<Player> createPlayers(int numberOfPlayers){
    	ArrayList<Player> playerList = new ArrayList<Player>();
    	for(int i = 1; i <= numberOfPlayers; i++){
    		playerList.add(new Player(i));
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
