package worldofsweets;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;

import java.util.*;

public class BoardGrid {

	private final int HEIGHT = 700;
    private final int WIDTH = 600;

    private JLabel start = new JLabel("Start");
    private JLabel end = new JLabel("Grandma's");
    private JLabel end2 = new JLabel("House");

    private GameBoardSquare[] _panels = new GameBoardSquare[42];
	private Container _container = new Container();


	public BoardGrid() {
		_container.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		_container.setLayout(new GridLayout(7,6));

		JPanel temp;
		Color squareColor;
		int panelCounter = 1;

		temp = new JPanel();
	    temp.setBackground(Color.white);

	    _panels[0] = new GameBoardSquare(temp);
	    _container.add(temp);

		for (int j = 1; j < 41; j++) {
	    	temp = new JPanel();
	    	squareColor = panelColor(panelCounter);
	    	temp.setBackground(squareColor);
	    	temp.setLayout(new GridLayout(1,4));

	    	_panels[j] = new GameBoardSquare(temp, squareColor);
	    	_container.add(temp);

	    	if(panelCounter < 5) {
	    		panelCounter++;
	    	} else {
	    		panelCounter = 1;
	    	}
		}

		temp = new JPanel();
	    _panels[41] = new GameBoardSquare(temp);

	    _container.add(temp);
		_container.setVisible(true);
	}

	public void initalizePlayer(ArrayList<Player> players){
		for(Player player : players){
			player.setLocation(0);
			_panels[0].setPlayer(player);
		}
	}

	public void movePlayer(Player player, ImageIcon currentCard){
		int newLocation = getNewLocation(currentCard, player.getLocation());
	
		_panels[player.getLocation()].removePlayer(player);
		player.setLocation(newLocation);
		_panels[newLocation].setPlayer(player);

		_container.revalidate();
		_container.repaint();
	}

	public int getNewLocation(ImageIcon currentCard, int currentLocation){
		if(currentCard == GameCards.cardRedSingle){
			for(int i = currentLocation + 1; i < 42; i++)
			{
				if(_panels[i].getGameColor() == GameColor.RED)
					return i;
			}

		}else if(currentCard == GameCards.cardRedDouble){
			int k = 0;
			for(int i = currentLocation + 1; i < 42; i++)
			{
				if(_panels[i].getGameColor() == GameColor.RED){
					k = i;
					break;
				}
				
			}
			for(int j = k + 1; j < 42; j++)
			{
				if(_panels[j].getGameColor() == GameColor.RED)
					return j;
			}

		}else if(currentCard == GameCards.cardBlueSingle){
			for(int i = currentLocation + 1; i < 42; i++)
			{
				if(_panels[i].getGameColor() == GameColor.BLUE)
					return i;
			}

		}else if(currentCard == GameCards.cardBlueDouble){
			int k = 0;
			for(int i = currentLocation + 1; i < 42; i++)
			{
				if(_panels[i].getGameColor() == GameColor.BLUE){
					k = i;
					break;
				}
				
			}
			for(int j = k + 1; j < 42; j++)
			{
				if(_panels[j].getGameColor() == GameColor.BLUE)
					return j;
			}

		}else if(currentCard == GameCards.cardGreenSingle){
			for(int i = currentLocation + 1; i < 42; i++)
			{
				if(_panels[i].getGameColor() == GameColor.GREEN)
					return i;
			}

		}else if(currentCard == GameCards.cardGreenDouble){
			int k = 0;
			for(int i = currentLocation + 1; i < 42; i++)
			{
				if(_panels[i].getGameColor() == GameColor.GREEN){
					k = i;
					break;
				}
				
			}
			for(int j = k + 1; j < 42; j++)
			{
				if(_panels[j].getGameColor() == GameColor.GREEN)
					return j;
			}

		}else if(currentCard == GameCards.cardYellowSingle){
			for(int i = currentLocation + 1; i < 42; i++)
			{
				if(_panels[i].getGameColor() == GameColor.YELLOW)
					return i;
			}

		}else if(currentCard == GameCards.cardYellowDouble){
			int k = 0;
			for(int i = currentLocation + 1; i < 42; i++)
			{
				if(_panels[i].getGameColor() == GameColor.YELLOW){
					k = i;
					break;
				}
				
			}
			for(int j = k + 1; j < 42; j++)
			{
				if(_panels[j].getGameColor() == GameColor.YELLOW)
					return j;
			}

		}else if(currentCard == GameCards.cardOrangeSingle){
			for(int i = currentLocation + 1; i < 42; i++)
			{
				if(_panels[i].getGameColor() == GameColor.ORANGE)
					return i;
			}

		}else{
			int k = 0;
			for(int i = currentLocation + 1; i < 42; i++)
			{
				if(_panels[i].getGameColor() == GameColor.ORANGE){
					k = i;
					break;
				}
				
			}
			for(int j = k + 1; j < 42; j++)
			{
				if(_panels[j].getGameColor() == GameColor.ORANGE)
					return j;
			}
			
		}

		return currentLocation;

	}

	public Container getContainer()
	{
		return _container;
	}

	public static Color panelColor(int panelCounter){
		Color red = new Color(255, 102, 102);
		Color yellow = new Color(255, 255, 102);
		Color blue = new Color(102, 204, 255);
		Color green = new Color(102, 255, 204);
		Color orange = new Color(255, 204, 102);

		Color color = red;
		  switch (panelCounter) {
            case 1:  color = GameColor.RED;
                     break;
            case 2:  color = GameColor.YELLOW;
                     break;
            case 3:  color = GameColor.BLUE;
                     break;
            case 4:  color = GameColor.GREEN;
                     break;
            case 5:  color = GameColor.ORANGE;
                     break;
            default: 
                     break;
        }

        return color;
	}

	private ImageIcon scaleIcon(ImageIcon icon, int size){
		Image image = icon.getImage();
		image = image.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}

}