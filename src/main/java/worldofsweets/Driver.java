package worldofsweets;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Driver{

  public static void main(String[] args){
      Event event = new Event();
      event.run();
  }

  public static void saveGame(Event e) {
    try {
       FileOutputStream fileOut =
       new FileOutputStream("./savedgames/saveGame.ser");
       ObjectOutputStream out = new ObjectOutputStream(fileOut);
       out.writeObject(e);
       out.close();
       fileOut.close();
    } catch (IOException i) {
       i.printStackTrace();
    }
  }
  public static Event loadGame() {
    Event e;
    try {
       FileInputStream fileIn = new FileInputStream("./savedgames/saveGame.ser");
       ObjectInputStream in = new ObjectInputStream(fileIn);
       e = (Event) in.readObject();
       in.close();
       fileIn.close();
       return e;
    } catch (Exception i) {
       i.printStackTrace();
       return null;
    }
  }
}
