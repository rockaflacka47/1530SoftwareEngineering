package worldofsweets;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import java.util.*;

public class GUI {

	private final JFrame frame;
	private ArrayList<JPanel> tileList;
	private Event event;

	public GUI(Event event) {
		this.event = event;

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("World of Sweets");
		frame.setResizable(false);

		Container pane = frame.getContentPane();
		pane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pane.setBackground(GameColor.TABLE);
		
		JPanel boardPanel = initializeBoardPanel(null);
		JPanel dataPanel = initializeDataPanel();

		pane.add(boardPanel);
		pane.add(dataPanel);

		frame.pack();
		frame.setVisible(true);
	}

	public void redraw(ArrayList<Player> playerList, int turnIndex, Card card){
		Container pane = frame.getContentPane();
		pane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pane.setBackground(GameColor.TABLE);

		JPanel boardPanel = initializeBoardPanel(playerList);
		JPanel dataPanel = initializeDataPanel(playerList, turnIndex, card);

		pane.removeAll();
		pane.add(boardPanel);
		pane.add(dataPanel);

		frame.revalidate();
		frame.repaint();
	}

	private JPanel initializeBoardPanel(ArrayList<Player> playerList){
		int numberOfPlayers;
		if(playerList != null){
			numberOfPlayers = playerList.size();
		}else{
			numberOfPlayers = 0;
		}
		

		tileList = new ArrayList<JPanel>();
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(7, 7, 0, 0));
		boardPanel.setPreferredSize(new Dimension(700,700));
		boardPanel.setBackground(Color.BLACK);

		tileList.add(createStartTile("Start", playerList));
		for(int i = 0; i < 47; i++){
			tileList.add(createColorTile(i, playerList));
		}
		tileList.add(createEndTile("End", playerList));

		return addTilesToPanel(boardPanel);
	}

	private JPanel initializeDataPanel(){
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		dataPanel.setPreferredSize(new Dimension(500, 700));
		dataPanel.setBackground(GameColor.TRANSPARENT);

		JPanel turnPanel = createTurnPanel("---");

		JPanel deckPanel = createDeckPanel();

		JPanel buttonPanel = createButtonPanel();

		JPanel keyPanel = createKeyPanel();

		dataPanel.add(turnPanel);
		dataPanel.add(deckPanel);
		dataPanel.add(buttonPanel);
		dataPanel.add(keyPanel);

		return dataPanel;
	}

	private JPanel initializeDataPanel(ArrayList<Player> playerList, int turnIndex, Card card){
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		dataPanel.setPreferredSize(new Dimension(500, 700));
		dataPanel.setBackground(GameColor.TRANSPARENT);

		JPanel turnPanel = createTurnPanel("---");

		JPanel deckPanel = createDeckPanel();

		JPanel buttonPanel = createButtonPanel();

		JPanel keyPanel = createKeyPanel();

		dataPanel.add(turnPanel);
		dataPanel.add(deckPanel);
		dataPanel.add(buttonPanel);
		dataPanel.add(keyPanel);

		return dataPanel;
	}

	private JPanel createTurnPanel(String message){
		JPanel turnPanel = new JPanel();
		JLabel label = new JLabel("<html><h1>" + message + "</h1></html>");
		label.setForeground(Color.WHITE);
		turnPanel.setPreferredSize(new Dimension(500, 100));
		turnPanel.setBackground(GameColor.TRANSPARENT);

		turnPanel.add(label);

		return turnPanel;
	}

	private JPanel createDeckPanel(){
		ImageIcon backImage = new ImageIcon("images/cards/cardBack.png");

		JPanel deckPanel = new JPanel();
		deckPanel.setPreferredSize(new Dimension(500, 350));
		deckPanel.setBackground(GameColor.TRANSPARENT);
		deckPanel.setLayout(new GridLayout(1, 2, 4, 0));

		JLabel cardBack = new JLabel(backImage);

		JLabel cardFront = new JLabel(backImage);

		deckPanel.add(cardBack);
		deckPanel.add(cardFront);

		return deckPanel;
	}

	private JPanel createButtonPanel(){
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(500, 100));
		buttonPanel.setBackground(GameColor.TRANSPARENT);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		JPanel blankPanel = new JPanel();
		blankPanel.setPreferredSize(new Dimension(500, 40));
		blankPanel.setBackground(GameColor.TRANSPARENT);

		JButton button = new JButton("Draw A Card");

		event.setButton(button); 
		button.addActionListener(event);

		buttonPanel.add(blankPanel);
		buttonPanel.add(button);

		return buttonPanel;
	}

	private JPanel createKeyPanel(){
		JPanel keyPanel = new JPanel();
		keyPanel.setPreferredSize(new Dimension(500, 150));
		keyPanel.setBackground(GameColor.TRANSPARENT);
		keyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		JPanel labelPanel = new JPanel();
		labelPanel.setBackground(GameColor.SEMI_TRANSPARENT);
		labelPanel.setLayout(new GridLayout(1, 4, 0, 0));
		labelPanel.setPreferredSize(new Dimension(500, 25));
		JLabel p1Label = new JLabel("--");
		p1Label.setBackground(GameColor.TRANSPARENT);
		p1Label.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, GameColor.TABLE));
		JLabel p2Label = new JLabel("--");
		p2Label.setBackground(GameColor.TRANSPARENT);
		p2Label.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, GameColor.TABLE));
		JLabel p3Label = new JLabel("--");
		p3Label.setBackground(GameColor.TRANSPARENT);
		p3Label.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, GameColor.TABLE));
		JLabel p4Label = new JLabel("--");
		p4Label.setBackground(GameColor.TRANSPARENT);
		p4Label.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, GameColor.TABLE));

		JPanel iconPanel = new JPanel();
		iconPanel.setBackground(GameColor.SEMI_TRANSPARENT);
		iconPanel.setLayout(new GridLayout(1, 4, 0, 0));
		iconPanel.setPreferredSize(new Dimension(500, 125));
		JPanel p1Icon = new JPanel();
		p1Icon.setBackground(GameColor.TRANSPARENT);
		p1Icon.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, GameColor.TABLE));
		JPanel p2Icon = new JPanel();
		p2Icon.setBackground(GameColor.TRANSPARENT);
		p2Icon.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, GameColor.TABLE));
		JPanel p3Icon = new JPanel();
		p3Icon.setBackground(GameColor.TRANSPARENT);
		p3Icon.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, GameColor.TABLE));
		JPanel p4Icon = new JPanel();
		p4Icon.setBackground(GameColor.TRANSPARENT);
		p4Icon.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, GameColor.TABLE));

		labelPanel.add(p1Label);
		labelPanel.add(p2Label);
		labelPanel.add(p3Label);
		labelPanel.add(p4Label);
		iconPanel.add(p1Icon);
		iconPanel.add(p2Icon);
		iconPanel.add(p3Icon);
		iconPanel.add(p4Icon);

		keyPanel.add(labelPanel);
		keyPanel.add(iconPanel);

		return keyPanel;
	}

	private JPanel addTilesToPanel(JPanel boardPanel){
		Color borderColor = Color.BLACK;

		for(int i = 0; i < 7; i++){
			if(i == 6){
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, borderColor));
			}else{
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, borderColor));
			}

			boardPanel.add(tileList.get(i));
		}
		for(int i = 13; i >= 7; i--){
			if(i == 7){
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, borderColor));
			}else if(i == 13){
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, borderColor));
			}else{
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, borderColor));
			}

			boardPanel.add(tileList.get(i));
		}
		for(int i = 14; i < 21; i++){
			if(i == 14){
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, borderColor));
			}else if(i == 20){
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, borderColor));
			}else{
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, borderColor));
			}

			boardPanel.add(tileList.get(i));
		}
		for(int i = 27; i >= 21; i--){
			if(i == 21){
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, borderColor));
			}else if(i == 27){
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, borderColor));
			}else if(i == 24){
				// Middle Tile
				tileList.get(i).setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, borderColor), BorderFactory.createMatteBorder(4, 4, 4, 4, Color.WHITE)));
			}else{
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, borderColor));
			}

			boardPanel.add(tileList.get(i));
		}
		for(int i = 28; i < 35; i++){
			if(i == 28){
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, borderColor));
			}else if(i == 34){
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, borderColor));
			}else{
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, borderColor));
			}

			boardPanel.add(tileList.get(i));
		}
		for(int i = 41; i >= 35; i--){
			if(i == 35){
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, borderColor));
			}else if(i == 41){
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, borderColor));
			}else{
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, borderColor));
			}

			boardPanel.add(tileList.get(i));
		}
		for(int i = 42; i < 49; i++){
			if(i == 42){
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, borderColor));
			}else{
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, borderColor));
			}
			boardPanel.add(tileList.get(i));
		}

		boardPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, borderColor));

		return boardPanel;
	}

	private JPanel createStartTile(String message, ArrayList<Player> playerList){
		JPanel startTile = new JPanel();
		startTile.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		startTile.setPreferredSize(new Dimension(100, 100));
		startTile.setBackground(Color.WHITE);

		JLabel label = new JLabel(message);
		startTile.add(label);

		if(playerList != null){
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(100, 78));
			panel.setBackground(GameColor.TRANSPARENT);
			panel.setLayout(new GridLayout(2, 2, 0, 0));

			for(Player player : getPlayersOnTile(playerList, 0)){
				JLabel iconLabel = new JLabel(player.getIcon(35));
				panel.add(iconLabel);
			}

			startTile.add(panel);
		}
		
		return startTile;
	}

	private JPanel createColorTile(int i, ArrayList<Player> playerList){
		JPanel colorTile = new JPanel();
		colorTile.setLayout(new GridLayout(2, 2, 1, 1));
		colorTile.setPreferredSize(new Dimension(100,100));
		colorTile.setBackground(GameColor.TILE_COLORS[i % GameColor.TILE_COLORS.length]);

		if(playerList != null){
			for(Player player : getPlayersOnTile(playerList, i+1)){
				JLabel iconLabel = new JLabel(player.getIcon(40));
				colorTile.add(iconLabel);
			}
		}

		return colorTile;
	}

	private ArrayList<Player> getPlayersOnTile(ArrayList<Player> playerList, int location){
		ArrayList<Player> playersOnTile = new ArrayList<Player>();
		for(Player player : playerList){
			if(player.getLocation() == location){
				playersOnTile.add(player);
			}
		}
		return playersOnTile;
	}

	private JPanel createEndTile(String message, ArrayList<Player> playerList){
		JPanel endTile = new JPanel();
		endTile.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		endTile.setPreferredSize(new Dimension(100, 100));
		endTile.setBackground(Color.WHITE);

		JLabel label = new JLabel(message);
		endTile.add(label);

		if(playerList != null){
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(100, 78));
			panel.setBackground(GameColor.TRANSPARENT);
			panel.setLayout(new GridLayout(1, 1, 0, 0));

			for(Player player : getPlayersOnTile(playerList, 48)){
				JLabel iconLabel = new JLabel(player.getIcon(78));
				panel.add(iconLabel);
			}

			endTile.add(panel);
		}
		
		return endTile;
	}
}
