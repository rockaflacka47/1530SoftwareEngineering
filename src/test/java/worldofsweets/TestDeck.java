package worldofsweets;

import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.*;
import java.awt.Color;
import java.util.*;

public class TestDeck {

	// Test for the correct number of cards (60)
	@Test
	public void testCardsInDeck(){
		int numberOfCards = 70;
		Deck d = new Deck();
		assertEquals(d.deck.size(), numberOfCards);
	}

	// Test that deck contains 12 red cards
	@Test
	public void test12Cards_Red(){
		int numberOfCards = 12;
		Color testColor = GameColor.RED;
		Deck d = new Deck();
		HashMap<Color, Integer> map = new HashMap<Color, Integer>();
		for(Color color : d.colors){
			map.put(color, 0);
		}
		for(Card card : d.deck){
			map.put(card.getColor(), map.get(card.getColor())+1);
		}
		assertEquals((int)map.get(testColor), numberOfCards);
	}

	//Test that deck contains 10 singles and 2 double red cards
	@Test
	public void testSinglesAndDoubles_Red(){
		int numberOfSingleCards = 10;
		int numberOfDoubleCards = 2;
		Color testColor = GameColor.RED;
		Deck d = new Deck();
		HashMap<Integer, Integer> valueMap = new HashMap<Integer, Integer>();
		valueMap.put(1,0);
		valueMap.put(2,0);
		for(Card card : d.deck){
			if(card.getColor() == testColor){
				valueMap.put(card.getValue(), valueMap.get(card.getValue())+1);
			}
		}

		assertTrue(valueMap.get(1) == numberOfSingleCards && valueMap.get(2) == numberOfDoubleCards);
	}

	//Test that deck is shuffled
	@Test
	public void testShuffled(){
		Deck orderedDeck = new Deck();
		Deck shuffledDeck = new Deck();
		shuffledDeck.shuffle();
		for(Card c : shuffledDeck.deck){
			System.out.println(c.getColor() + ", " + c.getValue());
		}

		boolean isEqual = true;
		for(int i = 0;  i < orderedDeck.deck.size(); i++){
			if(orderedDeck.deck.get(i).getColor() != shuffledDeck.deck.get(i).getColor() || orderedDeck.deck.get(i).getValue() != shuffledDeck.deck.get(i).getValue()){
				isEqual = false;
			}
		}

		assertFalse(isEqual);

	}

	//Test that special cards were added
	@Test
	public void testSpecialCards() {
		int numberOfCards = 10;
		Color testColor = GameColor.WHITE;
		Deck d = new Deck();
		HashMap<Color, Integer> map = new HashMap<Color, Integer>();
		for(Color color : d.colors){
			map.put(color, 0);
		}
		for(Card card : d.deck){
			map.put(card.getColor(), map.get(card.getColor())+1);
		}
		assertEquals((int)map.get(testColor), numberOfCards);
	}

	//Test that 5 'Skip Turn' cards are implemented
	@Test
	public void testNumberOfSkipTurnCards(){
		int numberOfCards = 0;
		Deck d = new Deck();
		for(Card c : d.deck){
			if(c.getValue() == 8){
				numberOfCards++;
			}
		}
		assertEquals(numberOfCards, 5);
	} 

	//Test that 3 'Go To Middle' cards are implemented
	@Test
	public void testNumberOfGoToCards(){
		int numberOfCards = 0;
		Deck d = new Deck();
		for(Card c : d.deck){
			if(c.getValue() > 2 && c.getValue() < 8){
				numberOfCards++;
			}
		}
		assertEquals(numberOfCards, 5);
	} 

}
