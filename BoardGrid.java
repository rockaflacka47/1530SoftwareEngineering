import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;

import java.util.*;

public class BoardGrid {

	private final int HEIGHT = 770;
    private final int WIDTH_LEFT = 660;
    private final int WIDTH_RIGHT = 400;

	public BoardGrid() {
		JFrame frame = initFrame();
		frame.add(initGameBoard());
		frame.add(initInfoArea());
		frame.pack();
		frame.setVisible(true);
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

	private JPanel initGameBoard(){
		JPanel mainPanel = new JPanel();
		int panelsPerRow = 7;
		int panelsPerColumn = 6;

		mainPanel.setPreferredSize(new Dimension(WIDTH_LEFT,HEIGHT));
		mainPanel.setLayout(new GridLayout(7,6,5,5));

		JPanel startPanel = new JPanel();
		startPanel.setBackground(Color.WHITE);
		startPanel.setLayout(new GridLayout(2,1,5,5));
		JLabel startLabel = new JLabel("<html><center>Start</center></html>");
		startPanel.add(startLabel);
		JPanel startIconPanel = new JPanel();
		startIconPanel.setBackground(Color.WHITE);
		startIconPanel.setLayout(new GridLayout(2,2,5,5));
		startPanel.add(startIconPanel);
		mainPanel.add(startPanel);

		for(int i = 0; i < (panelsPerRow * panelsPerColumn) - 2; i++){
			JPanel panel = new JPanel();
			panel.setBackground(panelColor(i));
			panel.setLayout(new GridLayout(2,2,5,5));
			mainPanel.add(panel);
		}

		JPanel endPanel = new JPanel();
		endPanel.setBackground(Color.WHITE);
		endPanel.setLayout(new GridLayout(2,1,5,5));
		JLabel endLabel = new JLabel("<html><center>Grandma's House</center></html>");
		endPanel.add(endLabel);
		mainPanel.add(endPanel);

		return mainPanel;
	}

	private JPanel initInfoArea(){
		JPanel mainPanel = new JPanel();

		mainPanel.setPreferredSize(new Dimension(WIDTH_RIGHT, HEIGHT));
		mainPanel.setLayout(new GridLayout(3,2,5,5));

		JPanel playerTurnPanel = new JPanel();
		JLabel playerTurnLabel = new JLabel("<html><h1>Player 1's Turn</h1></html>");
		playerTurnPanel.add(playerTurnLabel);
		mainPanel.add(playerTurnPanel);

		JPanel deckPanel = new JPanel();
		//Deck and current card will be displayed side by side here.
		mainPanel.add(deckPanel);

		JPanel keyPanel = new JPanel();
		JLabel player1Label = new JLabel("Player 1");
		JLabel player2Label = new JLabel("Player 2");
		JLabel player3Label = new JLabel("Player 3");
		JLabel player4Label = new JLabel("Player 4");
		keyPanel.setLayout(new GridLayout(2,4,5,5));
		keyPanel.add(player1Label);
		keyPanel.add(player2Label);
		keyPanel.add(player3Label);
		keyPanel.add(player4Label);
		mainPanel.add(keyPanel);

		return mainPanel;
	}

	private Color panelColor(int panelNumber){
		int numOfColors = 5;

		switch (panelNumber % numOfColors) {
			case 0:
				return GameColor.RED;
			case 1:
				return GameColor.YELLOW;
			case 2:
				return GameColor.BLUE;
			case 3:
				return GameColor.GREEN;
			case 4:
				return GameColor.ORANGE;
			default:
				return null;
		}
	}

}
