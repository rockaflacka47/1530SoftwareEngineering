import javax.swing.*;
public class Player{
    private int number;
    private int location;
    private ImageIcon icon;

    public Player(){
    	number = 0;
    	location = 0;
    	icon = null;
    }

    public Player(int number, int location, ImageIcon icon){
        this.number = number;
        this.location = location;
        this.icon = icon;
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

    public int getNumber(){
    	return number;
    }

    public int getLocation(){
    	return location;
    }

    public ImageIcon getIcon(){
    	return icon;
    }
}
