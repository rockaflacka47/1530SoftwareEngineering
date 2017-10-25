package worldofsweets;

import javax.swing.*;
import java.util.*;
import java.awt.Color;
public class Card{
    Color color;
    int value;
    ImageIcon face;
    public Card(Color color, int value){
        this.color = color;
        this.value = value;
        this.face = getFace(color, value);
    }

    public ImageIcon getFace(Color color, int value)
    {
    	ImageIcon face; 
    	if(color == GameColor.RED)
    	{
    		if(value == 1)
    		{
    			face = GameCards.cardRedSingle;
    		}
    		else
    		{
    			face = GameCards.cardRedDouble;
    		}
    	}else if(color == GameColor.BLUE){
    		if(value == 1)
    		{
    			face = GameCards.cardBlueSingle;
    		}
    		else
    		{
    			face = GameCards.cardBlueDouble;
    		}

    	}else if(color == GameColor.GREEN){
    		if(value == 1)
    		{
    			face = GameCards.cardGreenSingle;
    		}
    		else
    		{
    			face = GameCards.cardGreenDouble;
    		}
    		
    	}else if(color == GameColor.YELLOW){
    		if(value == 1)
    		{
    			face = GameCards.cardYellowSingle;
    		}
    		else
    		{
    			face = GameCards.cardYellowDouble;
    		}
    		
    	}else{
    		if(value == 1)
    		{
    			face = GameCards.cardOrangeSingle;
    		}
    		else
    		{
    			face = GameCards.cardOrangeDouble;
    		}

    	}

    	return face;
    }
}
