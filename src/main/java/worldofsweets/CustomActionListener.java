package worldofsweets;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CustomActionListener implements ActionListener, Serializable{
	private static final long serialVersionUID = 1234567897L;
	JLabel clock = new JLabel();
	private JFrame frame;
	private int ones = 0;
	private int tens = 0;
	private int decOnes = 0;
	private int decTens = 0;

	public CustomActionListener(JFrame frame, JLabel clock){
		this.frame = frame;
		this.clock = clock;
	}

	public void setFrame(JFrame frame){
		this.frame = frame;
	}

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
}