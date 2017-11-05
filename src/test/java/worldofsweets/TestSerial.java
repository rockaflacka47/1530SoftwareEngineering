package worldofsweets;

import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;
import javax.swing.*;
import static org.mockito.Mockito.*;

public class TestSerial {

  //Test that a serialized file is actually saved of our game
  @Test
  public void testSaveGame() {
    Event tempGame = new Event();
    Driver.saveGame(tempGame);
    File saveFile = new File("./saveGame.ser");
    assertNotNull(saveFile);
  }

  //Test loading from our serialized file exists
  @Test
  public void testLoadGameExists() {
    Event tempGame = new Event();
    Driver.saveGame(tempGame);
    File saveFile = new File("./savedgames/saveGame.ser");
    Event e = Driver.loadGame();
    assertNotNull(e);
  }

  //Test loading from our serialized file is equal
  //TODO: Test when file has been run, no idea how to test for inputing into the UI
  @Test
  public void testLoadGameEqual() {
    Event tempGame = new Event();
    Driver.saveGame(tempGame);
    File saveFile = new File("./savedgames/saveGame.ser");

    Event e = Driver.loadGame();
    assertTrue(tempGame.equals(e));
  }
}
