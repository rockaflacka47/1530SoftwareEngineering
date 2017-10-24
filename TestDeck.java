import java.awt.Color;

import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.*;

public class TestDeck {

  //Test that card is constructed correctly
  @Test
  public void testCardConstructor() {
    Card card = new Card(GameColor.RED, 2);
    assertEquals(card.color, GameColor.RED);
    assertEquals(card.value, 2);

  }

  //Test Deck Constructor
  @Test
  public void testDeckConstructor() {
    Deck deck = new Deck();
    assertEquals(deck.deck.size(), 60);

    Color [] colors = {GameColor.RED, GameColor.YELLOW, GameColor.BLUE, GameColor.GREEN, GameColor.ORANGE};
    int numCorrectColors = 0;
    for(int i = 0; i < 5; i++){
        for(int j = 0; j < 12; j++){
            Card check = deck.deck.get((i*12)+j);
            int value;
            if(j<10)
                value = 1;
            else
                value = 2;
            assertEquals(check.color, colors[i]);
            assertEquals(check.value, value);
        }
    }
  }
  @Test
  public void testDeckShuffle(){

  }

  
  @Test
  public void testDrawCard(){

  }
}
