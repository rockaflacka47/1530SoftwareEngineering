package worldofsweets;

import java.awt.Color;
import java.util.*;

public class Deck implements java.io.Serializable {
    ArrayList<Card> deck = new ArrayList<Card>();
    Color [] colors = {GameColor.RED, GameColor.YELLOW, GameColor.BLUE, GameColor.GREEN, GameColor.ORANGE, GameColor.WHITE};
    int drawNum = 0;

    //add 12 of each color. 10 single 2 double
    //will need to change a bit when implementing special cards
    public Deck(){
        //Don't include white
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
        //Include White
        for(int i = 0; i < 8; i++){
            if(i<3)
                deck.add(new Card(GameColor.WHITE, 3));
            else
                deck.add(new Card(GameColor.WHITE, 4));
        }
    }

    public void shuffle(){
        Random rand = new Random();
        Collections.shuffle(deck, rand);
    }

    //if the deck has been drawn through shuffle it and keep going
    public Card drawCard(){
        if(drawNum == 67){
            drawNum = 0;
            this.shuffle();
        }
        return deck.get(drawNum++);
    }

}
