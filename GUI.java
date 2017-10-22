import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import java.util.*;

public class GUI {

	private final int HEIGHT = 770;
    private final int WIDTH_LEFT = 660;
    private final int WIDTH_RIGHT = 400;
    private ArrayList<JPanel> boardPanelList;
    private JPanel playerTurnPanel;
    private JPanel deckPanel;
    private JPanel keyPanel;

	public GUI() {
		boardPanelList = new ArrayList<JPanel>();
		JFrame frame = initFrame();
		frame.add(initGameBoard());
		frame.add(initInfoArea());
		frame.pack();
		frame.setVisible(true);
	}

	public void setPlayerTurn(){

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

	private Container initGameBoard(){
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

	private Container initInfoArea(){
		Container infoContainer = new Container();
		infoContainer.setPreferredSize(new Dimension(WIDTH_RIGHT, HEIGHT));

		playerTurnPanel = new JPanel();
		infoContainer.add(playerTurnPanel);

		deckPanel = new JPanel();
		infoContainer.add(deckPanel);

		keyPanel = new JPanel();
		infoContainer.add(keyPanel);

		return infoContainer;
	}

}
