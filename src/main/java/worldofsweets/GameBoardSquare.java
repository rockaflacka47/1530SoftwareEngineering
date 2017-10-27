package worldofsweets;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import java.util.*;

public class GameBoardSquare{
	private ArrayList<Player> players;
	private JPanel square;
	private Color color;
	

	public GameBoardSquare(){
		players = new ArrayList<Player>();
	}

	public GameBoardSquare(JPanel panel){
		players = new ArrayList<Player>();
		setPanel(panel);

	}

	public GameBoardSquare(JPanel panel, Color color){
		players = new ArrayList<Player>();
		this.color = color;
		setPanel(panel);

	}

	public void setPanel(JPanel panel){
		this.square = panel;
	}

	public Color getGameColor(){
		return color;
	}

	public void setPlayer(Player player){
		ImageIcon playerIcon = player.getIcon();

		players.add(player);
		square.add(new JLabel(scaleIcon(playerIcon, 20)));
	}

	public void setPlayers(){
		square.removeAll();
		for(Player player : players){
			ImageIcon playerIcon = player.getIcon();
			square.add(new JLabel(scaleIcon(playerIcon, 20)));
		}

	}

	public void removePlayer(Player player){
		if(players.contains(player))
		{
			players.remove(player);
			setPlayers();
		}

	}

	public ArrayList<Player> getPlayers(){
		return players;
	}

	private ImageIcon scaleIcon(ImageIcon icon, int size){
		Image image = icon.getImage();
		image = image.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
}
