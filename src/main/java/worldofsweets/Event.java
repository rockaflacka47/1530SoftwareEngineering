package worldofsweets;

import java.util.*;
import java.io.*;

import javax.net.ssl.ExtendedSSLSession;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;

public class Event implements ActionListener, Serializable{

    private static final long serialVersionUID = 1234567899L;
    private ArrayList<Player> playerList;
    private ArrayList<JPanel> tileList;
    private GUI gameBoard;
    private Deck cardDeck;
    private int turnIndex;
    private Timer timer;
    private JButton button;
    private boolean computerPlayer;
    private int drawNum;
    private int numberOfComputerPlayers;


	public void run(){
		gameBoard = new GUI(this);
        int numberOfPlayers = getNumberOfPlayers();
        ArrayList<String> namesOfPlayers = getPlayerNames(numberOfPlayers);
        playerList = createPlayers(numberOfPlayers, namesOfPlayers);

        if(numberOfPlayers > 0 && numberOfPlayers < 5){
            cardDeck = new Deck();
            cardDeck.shuffle();
            turnIndex = 0;
            drawNum = 0;

            if(!computerPlayer){
                gameBoard.redraw(playerList, turnIndex, null);
            }else{
                gameBoard.redraw(playerList, numberOfComputerPlayers, null);
            }
        }else{
            button.setEnabled(false);
        }


	}

    public void load(){
        gameBoard = new GUI(this, gameBoard.customActionListener);

        if(!computerPlayer){
            gameBoard.redraw(playerList, turnIndex, null);
        }else{
            gameBoard.redraw(playerList, numberOfComputerPlayers, null);
        }

        gameBoard.startTimer();
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
                numberOfComputerPlayers = 0;

                gameBoard.startTimer();
                return numberOfPlayers;
            }
            else if(numberOfPlayers == 1){
                String stringNumComputerPlayers = JOptionPane.showInputDialog(null, "Please enter the number of AI players (1-3):", "World of Sweets", JOptionPane.PLAIN_MESSAGE);
                 try{
                if(stringInput == null){
                    return -1;
                }
                numberOfComputerPlayers = Integer.parseInt(stringNumComputerPlayers);
                }catch(NumberFormatException e){
                    error("The value entered was not a valid integer.", false);
                    continue;
                }
                if(numberOfComputerPlayers >= 1 && numberOfComputerPlayers <= 3){
                    computerPlayer = true;
                    gameBoard.startTimer();
                    return numberOfPlayers + numberOfComputerPlayers;
                }
                else{
                    error("The value entered was not between 1 and 3, inclusive.", false);
                    continue;
                }
            }
            else{
                error("The value entered was not between 1 and 4, inclusive.", false);
                continue;
            }
        }
    }

    public ArrayList<String> getPlayerNames(int numPlayers){
        ArrayList<String> ret = new ArrayList<String>();
        for(int i = 0; i < numPlayers; i ++){
            String stringInput = JOptionPane.showInputDialog(null, "Please enter the name of player " + (i+1) + " :", "World of Sweets", JOptionPane.PLAIN_MESSAGE);
            if(stringInput.equals("")){
                String temp = "Player " + (i+1);

                ret.add(i, temp);
            }
            else
                ret.add(i, stringInput);
        }
        return ret;
    }

    public ArrayList<Player> createPlayers(int numberOfPlayers, ArrayList<String> names){
    	ImageIcon[] iconList = {new ImageIcon("images/tokens/candy.png", "Candy"), new ImageIcon("images/tokens/gummybear.png", "Gummy Bear"), new ImageIcon("images/tokens/jellybean.png", "Jelly Bean"), new ImageIcon("images/tokens/lollipop.png", "Lollipop")};
    	ArrayList<Player> playerList = new ArrayList<Player>();
        if(!computerPlayer){
        	for(int i = 0; i < numberOfPlayers; i++){
                playerList.add(new Player(names.get(i), i+1, 0, iconList[i]));

        	}
        }
        else{
            for(int j = 0; j < numberOfComputerPlayers; j++)
            {
                int k = j+1;
                playerList.add(new Player("Computer " + k, j+1, 0, iconList[j]));
            }

            playerList.add(new Player(null, numberOfComputerPlayers+1, 0, iconList[numberOfComputerPlayers]));
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
            turns = numberOfComputerPlayers + 1;
        }

		if(event.getSource() == button){
            Player player;
            Card card;

            for(int i = 0; i < turns; i++)
            {
                player = playerList.get(turnIndex);
                if(player.getName().equalsIgnoreCase("dad")){

                    int loc = player.getLocation();


                    //get where in the deck each of these cards is
                    int index = cardDeck.search(3, drawNum);
                    int indexCream = cardDeck.search(4, drawNum);
                    int indexCake = cardDeck.search(5, drawNum);
                    int indexSoda = cardDeck.search(6,drawNum);
                    int indexPie = cardDeck.search(7,drawNum);
                    int numYellow = cardDeck.search(1, GameColor.YELLOW, drawNum);
                    int red = cardDeck.search(1, GameColor.RED, drawNum);
                    int blue = cardDeck.search(1, GameColor.BLUE, drawNum);
                    int green = cardDeck.search(1, GameColor.GREEN, drawNum);
                    int orange = cardDeck.search(1, GameColor.ORANGE, drawNum);

                    //checkif player is in front of a special sqaure and if that card has been used before
                    //if both of those are true put the special card as the next card to be drawn
                    if(player.getLocation() == 9 && index != -1){
                        cardDeck.swap(index);
                    }
                    else if(player.getLocation() == 17 && indexCream != -1){
                        cardDeck.swap(indexCream);
                    }
                    else if(player.getLocation() == 25 && indexCake != -1){
                        cardDeck.swap(indexCake);
                    }
                    else if(player.getLocation() == 33 && indexSoda != -1){
                        cardDeck.swap(indexSoda);
                    }
                    else if(player.getLocation() == 41 && indexPie != -1){
                        cardDeck.swap(indexPie);
                    }
                    //means red square
                    else if(loc % 5 == 1){

                        if(numYellow != -1 || blue != -1 || green != -1 || orange != -1 || red != -1){

                            //if player is in front of special square and its been used then move them to the closest color past it
                            if(player.getLocation() == 41){

                                if(blue != -1)
                                    cardDeck.swap(blue);
                                else if(green != -1)
                                    cardDeck.swap(green);
                                else if(orange != -1)
                                    cardDeck.swap(orange);
                                else if (red != -1)
                                    cardDeck.swap(red);
                                else if(numYellow != -1)
                                    cardDeck.swap(numYellow);
                            }
                            //else move them forward to the closest square
                            else if(numYellow != -1)
                                cardDeck.swap(numYellow);
                            else if(blue != -1)
                                cardDeck.swap(blue);
                            else if(green != -1)
                                cardDeck.swap(green);
                            else if(orange != -1)
                                cardDeck.swap(orange);
                            else
                                cardDeck.swap(red);
                        }
                        //if no single cards left choose the closest double square
                        else{
                            int dubYellow = cardDeck.search(2, GameColor.YELLOW, drawNum);
                            int dubRed = cardDeck.search(2, GameColor.RED,drawNum);
                            int dubBlue = cardDeck.search(2, GameColor.BLUE,drawNum);
                            int dubGreen = cardDeck.search(2, GameColor.GREEN,drawNum);
                            int dubOrange = cardDeck.search(2, GameColor.ORANGE,drawNum);
                            //if player is in front of special square and its been used then move them to the closest color past it
                            if(player.getLocation() == 41){

                                if(dubBlue != -1)
                                    cardDeck.swap(dubBlue);
                                else if(dubGreen != -1)
                                    cardDeck.swap(dubGreen);
                                else if(dubOrange != -1)
                                    cardDeck.swap(dubOrange);
                                else
                                    cardDeck.swap(dubRed);
                                if(dubYellow != -1)
                                    cardDeck.swap(dubYellow);
                            }
                            //else move them past it
                            else if(dubYellow != -1)
                                cardDeck.swap(dubYellow);
                            else if(dubBlue != -1)
                                cardDeck.swap(dubBlue);
                            else if(dubGreen != -1)
                                cardDeck.swap(dubGreen);
                            else if(dubOrange != -1)
                                cardDeck.swap(dubOrange);
                            else
                                cardDeck.swap(dubRed);
                        }


                    }
                    //means yellow square
                    else if(loc % 5 == 2){

                        if(numYellow != -1 || blue != -1 || green != -1 || orange != -1 || red != -1){
                            //if player is in front of special square and its been used then move them to the closest color past it
                            if(player.getLocation() == 17){

                                if(green != -1)
                                    cardDeck.swap(green);
                                else if(orange != -1)
                                    cardDeck.swap(orange);
                                else if(red != -1)
                                    cardDeck.swap(red);
                                else if(numYellow != -1)
                                    cardDeck.swap(numYellow);
                                else if(blue != -1)
                                    cardDeck.swap(blue);
                            }
                            //else move them to the first square past it that a card still exists for
                            else if(blue != -1)
                                cardDeck.swap(blue);
                            else if(green != -1)
                                cardDeck.swap(green);
                            else if(orange != -1)
                                cardDeck.swap(orange);
                            else if(red != -1)
                                cardDeck.swap(red);
                            else if(numYellow != -1)
                                cardDeck.swap(numYellow);
                        }
                        else{
                            int dubYellow = cardDeck.search(2, GameColor.YELLOW,drawNum);
                            int dubRed = cardDeck.search(2, GameColor.RED,drawNum);
                            int dubBlue = cardDeck.search(2, GameColor.BLUE,drawNum);
                            int dubGreen = cardDeck.search(2, GameColor.GREEN,drawNum);
                            int dubOrange = cardDeck.search(2, GameColor.ORANGE,drawNum);
                            if(player.getLocation() == 17){

                                if(dubGreen != -1)
                                    cardDeck.swap(dubGreen);
                                else if(dubOrange != -1)
                                    cardDeck.swap(dubOrange);
                                else if(dubRed !=-1)
                                    cardDeck.swap(dubRed);
                                else if(dubYellow != -1)
                                    cardDeck.swap(dubYellow);
                                else if(dubBlue != -1)
                                    cardDeck.swap(dubBlue);
                            }
                            else if(dubBlue != -1)
                                cardDeck.swap(dubBlue);
                            else if(dubGreen != -1)
                                cardDeck.swap(dubGreen);
                            else if(dubOrange != -1)
                                cardDeck.swap(dubOrange);
                            else if(dubRed !=-1)
                                cardDeck.swap(dubRed);
                            else if(dubYellow != -1)
                                cardDeck.swap(dubYellow);
                        }
                    }
                    //means blue square
                    else if(loc % 5 == 3){
                        //if player is in front of special square and its been used then move them to the closest color past it
                        if(numYellow != -1 || blue != -1 || green != -1 || orange != -1 || red != -1){

                            if(player.getLocation() == 33){

                                if(orange != -1)
                                    cardDeck.swap(orange);
                                else if(red != -1)
                                    cardDeck.swap(red);
                                else if(numYellow != -1)
                                    cardDeck.swap(numYellow);
                                else if(blue != -1)
                                    cardDeck.swap(blue);
                                else if(green != -1)
                                    cardDeck.swap(green);
                            }
                            //else move them past it to the first square that still have a card in the deck
                            else if(green != -1)
                                cardDeck.swap(green);
                            else if(orange != -1)
                                cardDeck.swap(orange);
                            else if(red != -1)
                                cardDeck.swap(red);
                            else if(numYellow != -1)
                                cardDeck.swap(numYellow);
                            else if(blue != -1)
                                cardDeck.swap(blue);
                        }
                        else{
                            int dubYellow = cardDeck.search(2, GameColor.YELLOW,drawNum);
                            int dubRed = cardDeck.search(2, GameColor.RED,drawNum);
                            int dubBlue = cardDeck.search(2, GameColor.BLUE,drawNum);
                            int dubGreen = cardDeck.search(2, GameColor.GREEN,drawNum);
                            int dubOrange = cardDeck.search(2, GameColor.ORANGE,drawNum);
                            if(player.getLocation() == 33){

                                if(dubOrange != -1)
                                    cardDeck.swap(dubOrange);
                                else if(dubRed !=-1)
                                    cardDeck.swap(dubRed);
                                else if(dubYellow != -1)
                                    cardDeck.swap(dubYellow);
                                else if(dubBlue != -1)
                                    cardDeck.swap(dubBlue);
                                else if(dubGreen != -1)
                                    cardDeck.swap(dubGreen);
                            }
                            else if(dubGreen != -1)
                                cardDeck.swap(dubGreen);
                            else if(dubOrange != -1)
                                cardDeck.swap(dubOrange);
                            else if(dubRed !=-1)
                                cardDeck.swap(dubRed);
                            else if(dubYellow != -1)
                                cardDeck.swap(dubYellow);
                            else if(dubBlue != -1)
                                cardDeck.swap(dubBlue);
                        }
                    }
                    //means green square
                    else if(loc % 5 == 4){
                        //if player is in front of special square and its been used then move them to the closest color past it
                        if(numYellow != -1 || blue != -1 || green != -1 || orange != -1 || red != -1){


                            if(player.getLocation() == 9){

                                if(red != -1)
                                    cardDeck.swap(red);
                                else if(numYellow != -1)
                                    cardDeck.swap(numYellow);
                                else if(blue != -1)
                                    cardDeck.swap(blue);
                                else if(green != -1)
                                    cardDeck.swap(green);
                                else if(orange != -1)
                                    cardDeck.swap(orange);
                            }
                            else if(orange != -1)
                                cardDeck.swap(orange);
                            else if(red != -1)
                                cardDeck.swap(red);
                            else if(numYellow != -1)
                                cardDeck.swap(numYellow);
                            else if(blue != -1)
                                cardDeck.swap(blue);
                            else if(green != -1)
                                cardDeck.swap(green);
                        }
                        else{
                            int dubYellow = cardDeck.search(2, GameColor.YELLOW,drawNum);
                            int dubRed = cardDeck.search(2, GameColor.RED,drawNum);
                            int dubBlue = cardDeck.search(2, GameColor.BLUE,drawNum);
                            int dubGreen = cardDeck.search(2, GameColor.GREEN,drawNum);
                            int dubOrange = cardDeck.search(2, GameColor.ORANGE,drawNum);
                            if(player.getLocation() == 9){

                                if(dubRed !=-1)
                                    cardDeck.swap(dubRed);
                                else if(dubYellow != -1)
                                    cardDeck.swap(dubYellow);
                                else if(dubBlue != -1)
                                    cardDeck.swap(dubBlue);
                                else if(dubGreen != -1)
                                    cardDeck.swap(dubGreen);
                                else if(dubOrange != -1)
                                    cardDeck.swap(dubOrange);
                            }
                            else if(dubOrange != -1)
                                cardDeck.swap(dubOrange);
                            else if(dubRed !=-1)
                                cardDeck.swap(dubRed);
                            else if(dubYellow != -1)
                                cardDeck.swap(dubYellow);
                            else if(dubBlue != -1)
                                cardDeck.swap(dubBlue);
                            else if(dubGreen != -1)
                                cardDeck.swap(dubGreen);
                        }
                    }
                    //means orange square
                    else if(loc % 5 == 0){

                        if(numYellow != -1 || blue != -1 || green != -1 || orange != -1 || red != -1){

                            if(player.getLocation() == 25){

                                if(numYellow != -1)
                                    cardDeck.swap(numYellow);
                                else if(blue != -1)
                                    cardDeck.swap(blue);
                                else if(green != -1)
                                    cardDeck.swap(green);
                                else if(orange != -1)
                                    cardDeck.swap(orange);
                                else if(red != -1)
                                    cardDeck.swap(red);
                            }
                            else if(red != -1)
                                cardDeck.swap(red);
                            else if(numYellow != -1)
                                cardDeck.swap(numYellow);
                            else if(blue != -1)
                                cardDeck.swap(blue);
                            else if(green != -1)
                                cardDeck.swap(green);
                            else if(orange != -1)
                                cardDeck.swap(orange);
                        }
                        else{
                            int dubYellow = cardDeck.search(2, GameColor.YELLOW,drawNum);
                            int dubRed = cardDeck.search(2, GameColor.RED,drawNum);
                            int dubBlue = cardDeck.search(2, GameColor.BLUE,drawNum);
                            int dubGreen = cardDeck.search(2, GameColor.GREEN,drawNum);
                            int dubOrange = cardDeck.search(2, GameColor.ORANGE,drawNum);
                            if(player.getLocation() == 25){

                                if(dubYellow != -1)
                                    cardDeck.swap(dubYellow);
                                else if(dubBlue != -1)
                                    cardDeck.swap(dubBlue);
                                else if(dubGreen != -1)
                                    cardDeck.swap(dubGreen);
                                else if(dubOrange != -1)
                                    cardDeck.swap(dubOrange);
                                else if(dubRed !=-1)
                                    cardDeck.swap(dubRed);
                            }
                            else if(dubRed !=-1)
                                cardDeck.swap(dubRed);
                            else if(dubYellow != -1)
                                cardDeck.swap(dubYellow);
                            else if(dubBlue != -1)
                                cardDeck.swap(dubBlue);
                            else if(dubGreen != -1)
                                cardDeck.swap(dubGreen);
                            else if(dubOrange != -1)
                                cardDeck.swap(dubOrange);
                        }
                    }

                    card = cardDeck.drawCard();
                    drawNum++;
                    if(drawNum==69)
                        drawNum =0;
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
                else{
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
                            gameBoard.redraw(playerList, numberOfComputerPlayers, card);
                        }

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
            if(color.toString().equals(panelColor.toString())){
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
