import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;

import java.util.*;

public class BoardGrid {

	private final int HEIGHT = 800;
    private final int WIDTH = 800;

    private JLabel start = new JLabel("Start");
    private JLabel end = new JLabel("Grandma's");
    private JLabel end2 = new JLabel("House");

    private JPanel[] _panels = new JPanel[42];
	private JFrame _frame = new JFrame("boardGrid");

	public static void main(String[] args) {
		new BoardGrid();
	}

	public BoardGrid() {
		_frame.setSize(WIDTH, HEIGHT);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setLayout(new GridLayout(7,6));

		int panelCounter = 1;

		_panels[0] = new JPanel();
	    _panels[0].setBackground(Color.white);
	    _panels[0].add(end);
	    _panels[0].add(end2);
	    
	    _frame.add(_panels[0]);

		for (int j = 1; j < 41; j++) {
	    	_panels[j] = new JPanel();
	    	_panels[j].setBackground(panelColor(panelCounter));
	    	_panels[j].setLayout(new GridLayout(2,2));

	    	_frame.add(_panels[j]);

	    	if(panelCounter < 5) {
	    		panelCounter++;
	    	} else {
	    		panelCounter = 1;
	    	}
		}

		_panels[41] = new JPanel();
	    _panels[41].setBackground(Color.white);
	    _panels[41].add(start);
	   
	    _frame.add(_panels[41]);
		_frame.setVisible(true);


	}

	

	public static Color panelColor(int panelCounter){
		Color red = new Color(255, 102, 102);
		Color yellow = new Color(255, 255, 102);
		Color blue = new Color(102, 204, 255);
		Color green = new Color(102, 255, 204);
		Color orange = new Color(255, 204, 102);

		Color color = red;
		  switch (panelCounter) {
            case 1:  color = red;
                     break;
            case 2:  color = yellow;
                     break;
            case 3:  color = blue;
                     break;
            case 4:  color = green;
                     break;
            case 5:  color = orange;
                     break;
            default: 
                     break;
        }

        return color;
	}

}