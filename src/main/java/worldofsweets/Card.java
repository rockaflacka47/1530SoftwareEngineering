package worldofsweets;

import javax.swing.*;
import java.util.*;
import java.awt.Color;
public class Card{
    private Color color;
    private int value;
    private ImageIcon icon;
    
    public Card(Color color, int value){
        this.color = color;
        this.value = value;
        this.icon = createIcon(color, value);
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
