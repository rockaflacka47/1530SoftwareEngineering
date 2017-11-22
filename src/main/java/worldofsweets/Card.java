package worldofsweets;

import javax.swing.*;
import java.util.*;
import java.awt.Color;
import java.io.*; 

public class Card implements Serializable{
    private static final long serialVersionUID = 1234567892L;
    private Color color;
    private int value;
    private ImageIcon icon;
    
    
    public Card(Color color, int value){
        this.color = color;
        this.value = value;
        this.icon = createIcon(color, value);
    
    }
    public Card(Color color, int value, ImageIcon icon){
        this.color = color;
        this.value = value;
        this.icon = icon;
    }

    public Color getColor(){
        return color;
    }

    public int getValue(){
        return value;
    }

    public ImageIcon getIcon(){
        return icon;
    }

    private ImageIcon createIcon(Color color, int value){
        String cardName = "";

        if(value == 1){
            cardName += "single_";
        }else if(value == 2){
            cardName += "double_";
        }else if(value == 3){
            cardName += "go_to_licorice.png";
        }else if(value == 4){
            cardName += "go_to_iceCream.png";
        }else if(value == 5){
            cardName += "go_to_cake.png";
        }else if(value == 6){
            cardName += "go_to_soda.png";
        }else if(value == 7){
            cardName += "go_to_pie.png";
        }else if(value == 8){
            cardName += "skip_turn.png";
        }
        

        if(color == GameColor.RED){
            cardName += "red.png";
        }else if(color == GameColor.BLUE){
            cardName += "blue.png";
        }else if(color == GameColor.GREEN){
            cardName += "green.png";
        }else if(color == GameColor.YELLOW){
            cardName += "yellow.png";
        }else if(color == GameColor.ORANGE){
            cardName += "orange.png";
        }

        return new ImageIcon("images/cards/" + cardName);
    }
}
