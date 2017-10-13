import java.awt.Color;
import java.util.*;
public class Deck{
    ArrayList<Card> deck = new ArrayList<Card>();
    //colors are RED/YELLOW/BLUE/GREEN/ORANGE in order
    Color [] colors = {new Color(255,0,0), new Color(255,255,0), new Color(0,0,255), new Color(0,128,0), new Color(255,165,0)};
    //dynamic way of drawing cards 
    int drawNum = 0;

    //add 12 of each color. 10 single 2 double
    //will need to change a bit when implementing special cards
    public Deck(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 12; j++){
                int value;
                if(j<10) 
                    value = 1;
                else 
                    value = 2;
                deck.add(new Card(colors[i],value));         
            }
        }       
    }

    public void shuffle(){
        Random rand = new Random();
        Collections.shuffle(deck, rand);
    }

    //if the deck has been drawn through shuffle it and keep going
    public Card drawCard(){
        if(drawNum == 60){
            drawNum = 0;
            this.shuffle();
        }
        return deck.get(drawNum++);
    }

}