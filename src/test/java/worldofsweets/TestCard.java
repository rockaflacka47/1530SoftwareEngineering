package worldofsweets;

import org.junit.*;
import static org.junit.Assert.*;
import javax.swing.*;

public class TestCard {

	Card c, middleCard, skipTurnCard;
	
	// Creates a blue single card
	@Before
	public void createCard(){
		c = new Card(GameColor.BLUE, 1);
	}

	// Ensures color is blue
	@Test
	public void testBlueColor(){
		assertEquals(c.getColor(), GameColor.BLUE);
	}

	// Ensures value is 1
	@Test
	public void testValue1(){
		assertEquals(c.getValue(), 1);
	}

	// Ensures ImageIcon is correct
	@Test
	public void testImageIcon(){
		assertEquals(c.getIcon().getDescription(), "images/cards/single_blue.png");
	}
}
