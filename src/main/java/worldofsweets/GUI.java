package worldofsweets;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;

public class GUI {

	private final JFrame frame;
	private ArrayList<JPanel> tileList;
	private Event event;
	private int ones = 0;
	private int tens = 0;
	private int decOnes = 0;
	private int decTens = 0;
	JLabel clock = new JLabel();
	ActionListener updateClockAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(++decOnes == 10){
				decOnes = 0;
				if(++decTens == 6){
					decTens = 0;
					if(++ones == 10){
						ones = 0;
						tens++;
					}
				}
			}
			frame.setTitle("World of Sweets - " + tens + "" + ones + ":" + decTens + "" + decOnes);
			clock.setText(tens + "" + ones + ":" + decTens + "" + decOnes);
			frame.pack();
		}
	};
	private Timer timer = new Timer(1000, updateClockAction);
	
	public void stopTimer(){
		timer.stop();
	}
	public void startTimer(){
		timer.start();
	}
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
		JPanel dataPanel = initializeDataPanel(null, -1, null);

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


	// Board Panel

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
		tileList.add(createEndTile("Grandma's House", playerList));
		event.setTileList(tileList);

		return addTilesToPanel(boardPanel);
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
			}else if(i == 10){
				ImageIcon ic = new ImageIcon("images/specialSquares/licorice.png");
				Image image = ic.getImage();
				image = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
				ic = new ImageIcon(image);
				JLabel temp = new JLabel(ic);
				tileList.get(i).setLayout(new GridLayout());
				tileList.get(i).setBackground(GameColor.WHITE);
				tileList.get(i).add(temp);
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, borderColor));
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, borderColor));
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
			}else if(i == 18){
				ImageIcon ic = new ImageIcon("images/specialSquares/iceCream.png");
				Image image = ic.getImage();
				image = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
				ic = new ImageIcon(image);
				JLabel temp = new JLabel(ic);
				tileList.get(i).setLayout(new GridLayout());
				tileList.get(i).setBackground(GameColor.WHITE);
				tileList.get(i).add(temp);
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, borderColor));			
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
			}else if(i == 26){
				ImageIcon ic = new ImageIcon("images/specialSquares/cake.png");
				Image image = ic.getImage();
				image = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
				ic = new ImageIcon(image);
				JLabel temp = new JLabel(ic);
				tileList.get(i).setLayout(new GridLayout());
				tileList.get(i).setBackground(GameColor.WHITE);
				tileList.get(i).add(temp);
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, borderColor));
			}else if(i == 27){
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, borderColor));
			}else{
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, borderColor));
			}

			boardPanel.add(tileList.get(i));
		}
		for(int i = 28; i < 35; i++){
			if(i == 28){
				tileList.get(i).setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, borderColor));
			}else if(i == 34){
				ImageIcon ic = new ImageIcon("images/specialSquares/soda.png");
				Image image = ic.getImage();
				image = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
				ic = new ImageIcon(image);
				JLabel temp = new JLabel(ic);
				tileList.get(i).setLayout(new GridLayout());
				tileList.get(i).setBackground(GameColor.WHITE);
				tileList.get(i).add(temp);
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
				ImageIcon ic = new ImageIcon("images/specialSquares/pie.png");
				Image image = ic.getImage();
				image = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
				ic = new ImageIcon(image);
				JLabel temp = new JLabel(ic);
				tileList.get(i).setLayout(new GridLayout());
				tileList.get(i).setBackground(GameColor.WHITE);
				tileList.get(i).add(temp);
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

		JLabel label = new JLabel("<html><body style='width: 100; text-align: center'>" + message + "</body></html>");
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

		JLabel label = new JLabel("<html><body style='width: 100; text-align: center'>" + message + "</body></html>");
		label.setPreferredSize(new Dimension(100, 30));
		endTile.add(label);

		if(playerList != null){
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(100, 70));
			panel.setBackground(GameColor.TRANSPARENT);
			panel.setLayout(new GridLayout(1, 1, 0, 0));

			for(Player player : getPlayersOnTile(playerList, 48)){
				JLabel iconLabel = new JLabel(player.getIcon(50));
				panel.add(iconLabel);
			}

			endTile.add(panel);
		}
		
		return endTile;
	}


	// Data Panel

	private JPanel initializeDataPanel(ArrayList<Player> playerList, int turnIndex, Card card){
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		dataPanel.setPreferredSize(new Dimension(500, 700));
		dataPanel.setBackground(GameColor.TRANSPARENT);

		JPanel turnPanel = createTurnPanel(playerList, turnIndex);

		JPanel deckPanel = createDeckPanel(card);

		JPanel buttonPanel = createButtonPanel();

		JPanel keyPanel = createKeyPanel(playerList, turnIndex);

		dataPanel.add(turnPanel);
		dataPanel.add(deckPanel);
		dataPanel.add(buttonPanel);
		dataPanel.add(keyPanel);

		return dataPanel;
	}

	private JPanel createTurnPanel(ArrayList<Player> playerList, int turnIndex){
		String message = "---";
		if(playerList != null){
			message = playerList.get(turnIndex).getName() + "'s Turn";
		}

		JPanel turnPanel = new JPanel();
		JLabel label = new JLabel(message);
		Font font = label.getFont();
		label.setFont(new Font(font.getName(), font.getStyle(), 24));
		label.setForeground(Color.WHITE);
		turnPanel.setPreferredSize(new Dimension(500, 100));
		turnPanel.setBackground(GameColor.TRANSPARENT);

		turnPanel.add(label);

		return turnPanel;
	}

	private JPanel createDeckPanel(Card card){
		ImageIcon leftIcon = new ImageIcon("images/cards/cardBack.png");
		ImageIcon rightIcon;
		if(card != null){
			rightIcon = card.getIcon();
		}else{
			rightIcon = leftIcon;
		}

		JPanel deckPanel = new JPanel();
		deckPanel.setPreferredSize(new Dimension(500, 350));
		deckPanel.setBackground(GameColor.TRANSPARENT);
		deckPanel.setLayout(new GridLayout(1, 2, 4, 0));

		JLabel leftLabel = new JLabel(leftIcon);

		JLabel rightLabel = new JLabel(rightIcon);

		deckPanel.add(leftLabel);
		deckPanel.add(rightLabel);

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

	private JPanel createKeyPanel(ArrayList<Player> playerList, int turnIndex){
		JPanel keyPanel = new JPanel();
		keyPanel.setPreferredSize(new Dimension(500, 150));
		keyPanel.setBackground(GameColor.TRANSPARENT);
		keyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		if(playerList != null){
			JPanel textPanel = new JPanel();
			textPanel.setLayout(new GridLayout(1, 4, 5, 5));
			textPanel.setPreferredSize(new Dimension(500, 35));
			textPanel.setBackground(GameColor.TRANSPARENT);
			JPanel iconPanel = new JPanel();
			iconPanel.setLayout(new GridLayout(1, 4, 5, 5));
			iconPanel.setPreferredSize(new Dimension(500, 115));
			iconPanel.setBackground(GameColor.TRANSPARENT);

			for(int i = 0; i < playerList.size(); i++){
				Player player = playerList.get(i);

				JPanel panelA = new JPanel();
				JLabel textLabel = new JLabel(player.getName());
				panelA.add(textLabel);
				if(i == turnIndex){
					panelA.setBackground(Color.WHITE);
				}else{
					panelA.setBackground(GameColor.SEMI_TRANSPARENT);
				}
				textPanel.add(panelA);

				JPanel panelB = new JPanel();
				JLabel iconLabel = new JLabel(player.getIcon(100));
				panelB.add(iconLabel);
				if(i == turnIndex){
					panelB.setBackground(Color.WHITE);
				}else{
					panelB.setBackground(GameColor.SEMI_TRANSPARENT);
				}
				iconPanel.add(panelB);
			}

			keyPanel.add(textPanel);
			keyPanel.add(iconPanel);
		}

		return keyPanel;
	}

}
