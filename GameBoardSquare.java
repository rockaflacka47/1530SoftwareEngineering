import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import java.util.*;

public class GameBoardSquare{
	private ArrayList<Player> players;
	private ArrayList<JPanel> panels;
	private JLabel label;
	private Container container;
	private int location;
	private Color color;

	public GameBoardSquare(){
		players = new ArrayList<Player>();
		panels = new ArrayList<JPanel>();
		label = null;
		container = new Container();
		location = 0;
		color = Color.WHITE;

		generatePanels();
		addColorToPanels();
		addPanelsToContainer();
	}

	public GameBoardSquare(int location){
		players = new ArrayList<Player>();
		panels = new ArrayList<JPanel>();
		label = null;
		container = new Container();
		this.location = location;

		generateColor();
		generatePanels();
		addColorToPanels();
		addPanelsToContainer();
	}

	public GameBoardSquare(int location, String labelText){
		players = new ArrayList<Player>();
		panels = new ArrayList<JPanel>();
		label = new JLabel("<html><center>" + labelText + "</center></html>", JLabel.CENTER);
		container = new Container();
		this.location = location;
		color = Color.WHITE;

		generatePanels();
		addColorToPanels();
		addPanelsToContainer();

		Container tempContainer = new Container();
		tempContainer.setLayout(new GridLayout(2,1,0,0));
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		tempContainer.add(label);
		tempContainer.add(container);
		container = tempContainer;
	}

	public void setPlayer(Player player){
		players.add(player);
	}

	public int getLocation(){
		return location;
	}

	public ArrayList<Player> getPlayers(){
		return players;
	}

	public JLabel getLabel(){
		return label;
	}

	public Color getColor(){
		return color;
	}

	public Container getContainer(){
		return container;
	}

	private void generateColor(){
		Color[] colors = {GameColor.RED, GameColor.YELLOW, GameColor.BLUE, GameColor.GREEN, GameColor.ORANGE};
		this.color = colors[location % colors.length];
	}

	private void generatePanels(){
		for(int i = 0; i < 4; i++){
			panels.add(new JPanel());
		}
	}

	private void addColorToPanels(){
		for(JPanel panel : panels){
			panel.setBackground(color);

		}
	}

	private void addPanelsToContainer(){
		container.setLayout(new GridLayout(2,2,0,0));
		for(JPanel panel : panels){
			container.add(panel);
		}
	}
}
