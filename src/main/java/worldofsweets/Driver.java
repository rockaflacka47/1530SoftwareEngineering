package worldofsweets;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Driver{

	public static Event event;

	
    public static void main(String[] args){
        event = new Event();
        event.run();
    }

    public static void saveGame(){
    	try{
    		FileOutputStream fileOut = new FileOutputStream("./savedGames/event.ser");
    		ObjectOutputStream out = new ObjectOutputStream(fileOut);
    		out.writeObject(event);
    		out.close();
    		fileOut.close();
    	}catch(IOException i){
    		i.printStackTrace();
    	}catch(NullPointerException n){
    		n.printStackTrace();
    	}

    	System.out.println("Saved Game");
    }

    public static void loadGame(GUI gui){
    	try{
    		FileInputStream fileIn = new FileInputStream("./savedGames/event.ser");
    		ObjectInputStream in = new ObjectInputStream(fileIn);
    		event = (Event) in.readObject();
    		in.close();
    		fileIn.close();

    		System.out.println("Loaded Game");

    		gui.setVisible(false);

    		event.load();
    	}catch (FileNotFoundException i) {
	        JOptionPane.showMessageDialog(null, "No save game file was found!");
      	}catch (Exception e){
	        JOptionPane.showMessageDialog(null, "Error loading file.");
	        e.printStackTrace();
      	}
    }
}
