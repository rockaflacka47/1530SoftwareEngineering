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
    Container infoContainer;

	public GUI() {
		JFrame frame = initFrame();
		frame.add(createGameBoard());
		frame.add(createInfoArea(null, null));
		frame.pack();
		frame.setVisible(true);
	}

	public GUI(ArrayList<Player> playerList, Deck deck){
		JFrame frame = initFrame();
		frame.add(createGameBoard());
		frame.add(createInfoArea(playerList, deck));
		frame.pack();
		frame.setVisible(true);
	}

	public void setPlayerTurn(Player player){
		
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

	private Container createGameBoard(){
		Container boardContainer = new Container();
		int panelsPerColumn = 7;
		int panelsPerRow = 6;

		boardContainer.setLayout(new GridLayout(panelsPerColumn, panelsPerRow));
		boardContainer.setPreferredSize(new Dimension(WIDTH_LEFT,HEIGHT));

		boardContainer.add(new GameBoardSquare(0, "Start").getContainer());

		for(int i = 0; i < (panelsPerRow * panelsPerColumn) - 2; i++){
			boardContainer.add(new GameBoardSquare(i+1).getContainer());
		}

		boardContainer.add(new GameBoardSquare((panelsPerRow * panelsPerColumn) - 1, "Grandma's House").getContainer());

		return boardContainer;
	}

	private Container createInfoArea(ArrayList<Player> playerList, Deck deck){
		final Deck deckTest = deck;
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
