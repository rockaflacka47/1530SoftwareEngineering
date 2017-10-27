package worldofsweets;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import java.util.*;

public class GUI {

	private final int HEIGHT = 700;
    private final int WIDTH_LEFT = 600;
    private final int WIDTH_RIGHT = 400;
    private JPanel turnPanel;
    private JPanel deckPanel;
    private JPanel keyPanel;
    private JPanel drawCardPanel;
    private Container infoContainer;
    private BoardGrid board;
    private int playerTurn;
    private int numberOfPlayers;

	public GUI() {
		JFrame frame = initFrame();
		frame.add(createGameBoard(null));
		frame.add(createInfoArea(null, null));
		frame.pack();
		frame.setVisible(true);

		numberOfPlayers = 0;
		playerTurn = 0;
	}

	public GUI(ArrayList<Player> playerList, Deck deck){
		JFrame frame = initFrame();
		frame.add(createGameBoard(playerList));
		frame.add(createInfoArea(playerList, deck));
		frame.pack();
		frame.setVisible(true);

		numberOfPlayers = playerList.size();
		playerTurn = 0;
	}

	public void setPlayerTurn(){
		if(playerTurn == numberOfPlayers - 1)
		{
			playerTurn = 0;
		}
		else
		{
			playerTurn = playerTurn + 1;	
		}	
	}

	private JFrame initFrame(){
		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		frame.setBackground(Color.WHITE);
		frame.setTitle("World of Sweets");
		frame.setResizable(false);

		return frame;
	}

	private Container createGameBoard(ArrayList<Player> playerList){
		board = new BoardGrid();
		board.initalizePlayer(playerList);
		

		return board.getContainer();
	}

	private Container createInfoArea(ArrayList<Player> playerList, Deck deck){
		final Deck deckTest = deck;
		final ArrayList<Player> players = playerList;

		infoContainer = new Container();
		infoContainer.setLayout(new GridLayout(4,1));
		infoContainer.setPreferredSize(new Dimension(WIDTH_RIGHT, HEIGHT));

		turnPanel = new JPanel();
		turnPanel.add(new JLabel("<html><center><h1>--</h1></center></html>", JLabel.CENTER));
		infoContainer.add(turnPanel);

		deckPanel = new JPanel();
		deckPanel.setLayout(new GridLayout(1,1,0,0));
		ImageIcon cardBack = GameCards.cardBack;
		
		deckPanel.add(new JLabel(scaleIcon(cardBack, 225)));
		deckPanel.add(new JLabel(scaleIcon(cardBack, 225)));
		infoContainer.add(deckPanel);

		JButton drawCardButton = new JButton("Draw Card");

		drawCardButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Card cardDrawn = deckTest.drawCard();
            ImageIcon cardDisplayed = cardDrawn.face;

         	deckPanel.remove(1);
            deckPanel.add(new JLabel(scaleIcon(cardDisplayed, 225)));
       	 	
       	 	board.movePlayer(players.get(playerTurn), cardDisplayed);
       	 	
       	 	setPlayerTurn();

       	    infoContainer.revalidate();
       	    infoContainer.repaint();
         }          
      	}); 

		drawCardPanel = new JPanel();
		drawCardPanel.add(drawCardButton);
		infoContainer.add(drawCardPanel);

		if(playerList != null){
			keyPanel = new JPanel();
			keyPanel.setLayout(new GridLayout(2,playerList.size()));
			for(Player player : playerList){
				keyPanel.add(new JLabel("<html><center>" + player.getName() + "<center></html>", JLabel.CENTER));
			}
			for(Player player : playerList){
				keyPanel.add(new JLabel(scaleIcon(player.getIcon(),50)));
			}
			infoContainer.add(keyPanel);
		}		

		return infoContainer;
	}

	private ImageIcon scaleIcon(ImageIcon icon, int size){
		Image image = icon.getImage();
		image = image.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}

	/*public void displayShuffleNotification(){
		JFrame shuffling = new JFrame();
		shuffling.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shuffling.setUndecorated(true);
        
		JLabel notif = new JLabel("Reshuffling Deck");
		shuffling.setAlwaysOnTop(true);
		shuffling.setLocation(500, 500);
		shuffling.setTitle("Notice!");
		shuffling.add(notif);
		shuffling.pack();
		shuffling.setVisible(true);
	}*/



}
