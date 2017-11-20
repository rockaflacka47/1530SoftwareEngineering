package worldofsweets;

import javax.swing.*;
import java.awt.*;

public class Player {
    private String name;
    private int number;
    private int location;
    private ImageIcon icon;

    public Player(){
      name = null;
    	number = 0;
    	location = 0;
    	icon = null;
    }

    public Player(String name, int number, int location, ImageIcon icon){
        if(name == null){
            this.name = "Player " + number;
        }else{
            this.name = name;
        }
        this.number = number;
        this.location = location;
        this.icon = icon;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setNumber(int number){
    	this.number = number;
    }

    public void setLocation(int location){
        this.location = location;
    }

    public void setIcon(ImageIcon icon){
    	this.icon = icon;
    }

    public String getName(){
        return name;
    }

    public int getNumber(){
    	return number;
    }

    public int getLocation(){
    	return location;
    }

    public ImageIcon getIcon(){
    	return icon;
    }

    public ImageIcon getIcon(int size){
        Image image = icon.getImage();
        image = image.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}
