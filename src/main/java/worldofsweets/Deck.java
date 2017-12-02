package worldofsweets;

import java.awt.Color;
import java.util.*;
import java.io.*;

public class Deck implements Serializable{
    private static final long serialVersionUID = 1234567891L;
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
        for(int i = 0; i < 10; i++){
            if(i<5)
                deck.add(new Card(GameColor.WHITE, i+3));
            else
                deck.add(new Card(GameColor.WHITE, 8));
        }
    }

    public void shuffle(){
        Random rand = new Random();
        Collections.shuffle(deck, rand);
    }

    //if the deck has been drawn through shuffle it and keep going
    public Card drawCard(){
        if(drawNum == 69){
            drawNum = 0;
            this.shuffle();
        }
        return deck.get(drawNum++);
    }

    

    public void swap(int index){
        
        Card temp = new Card(deck.get(drawNum).getColor(), deck.get(drawNum).getValue(), deck.get(drawNum).getIcon());
        Card temp2 = new Card(deck.get(index).getColor(), deck.get(index).getValue(), deck.get(index).getIcon());
        deck.set(drawNum, temp2);
        
        deck.set(index, temp);
    }
    public int search(int toLook, int draw){
        int j = 0;
       
        for(int i = draw; i < 67; i ++){
            if(deck.get(i).getValue() == toLook){
                
                return i;
            }
        }
        return -1;
    }
    public int search(int toLook, Color col, int draw){
        for(int i = draw; i < 67; i ++){
            
            if(deck.get(i).getValue() == toLook && deck.get(i).getColor().equals(col)){
               
                return i;
            }
        }
        return -1;
    }
    

}
