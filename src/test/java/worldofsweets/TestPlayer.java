package worldofsweets;

import org.junit.*;
import static org.junit.Assert.*;
import javax.swing.*;

public class TestPlayer {

	Player p;
	String name = "Jeff";
	int number = 1;
	int location = 10;
	ImageIcon icon = new ImageIcon("images/tokens/jellybean.png");

	// create new player named Jeff, number 1, location 10, icon jellybean
	@Before
	public void makePlayer(){
		p = new Player(name, number, location, icon);
	}

	// check name
	@Test
	public void testName(){
		assertEquals(p.getName(), name);
	}

	// check number
	@Test
	public void testNumber(){
		assertEquals(p.getNumber(), number);
	}

	// check location
	@Test
	public void testLocation(){
		assertEquals(p.getLocation(), location);
	}

	// check icon
	@Test
	public void testIcon(){
		assertEquals(p.getIcon().getDescription(), icon.getDescription());
	}

	// change name to Robert
	@Test
	public void testChangeName(){
		p.setName("Robert");
		assertEquals(p.getName(), "Robert");
	}

	// change number to 0
	@Test
	public void testChangeNumber(){
		p.setNumber(0);
		assertEquals(p.getNumber(), 0);
	}

	// change location to 0
	@Test
	public void testChangeLocation(){
		p.setLocation(0);
		assertEquals(p.getLocation(), 0);
	}

	// change icon to gummybear
	@Test
	public void testChangeIcon(){
		p.setIcon(new ImageIcon("images/tokens/gummybear.png"));
		assertEquals(p.getIcon().getDescription(), "images/tokens/gummybear.png");
	}

	// check boomerangs
	@Test
	public void testGetBoomerangs(){
		assertNotNull(p.getBoomerangs());
	}

	// change boomerang amount
	@Test
	public void testChangeBoomerangs(){
		p.setBoomerangs(4);
		assertEquals(p.getBoomerangs(), 4);
	}

	// see if useBoomerang changes amount of boomerangs
	@Test
	public void testUseBoomerangChange(){
		int a = p.getBoomerangs();
		p.useBoomerang();
		assertEquals(p.getBoomerangs(), a-1);
	}

	// see if useBoomerang doesn't drop below 0
	@Test
	public void testUseBoomerangNotBelowZero() {
		p.setBoomerangs(0);
		p.useBoomerang();
		assertEquals(p.getBoomerangs(), 0);
	}

	// see if useBoomerang returns false if boomerangs = 0
	@Test
	public void testUseBoomerangReturnFalse() {
		p.setBoomerangs(0);
		assertFalse(p.useBoomerang());
	}

}
