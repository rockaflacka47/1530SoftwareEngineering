package worldofsweets;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Driver{

	public static Event event;


    public static void main(String[] args){
        event = new Event();
        event.run();
    }

    public static void saveGame(){
    	try{
    		FileOutputStream fileOut = new FileOutputStream("./savedGames/event.ser");
    		ObjectOutputStream out = new ObjectOutputStream(fileOut);
    		out.writeObject(event);
				writeCheckSum();
    		out.close();
    		fileOut.close();
    	}catch(IOException i){
    		i.printStackTrace();
    	}catch(NullPointerException n){
    		n.printStackTrace();
    	}

    	System.out.println("Saved Game");
    }

    public static void loadGame(GUI gui){
    	try{
    		FileInputStream fileIn = new FileInputStream("./savedGames/event.ser");
    		ObjectInputStream in = new ObjectInputStream(fileIn);
    		event = (Event) in.readObject();
				if(!checkCheckSum()) throw new Exception();
    		in.close();
    		fileIn.close();

    		System.out.println("Loaded Game");

    		gui.setVisible(false);

    		event.load();
    	}catch (FileNotFoundException i) {
	        JOptionPane.showMessageDialog(null, "No save game file was found!");
      	}catch (Exception e){
	        JOptionPane.showMessageDialog(null, "Error loading file.");
	        e.printStackTrace();
      	}
    }
		private static String getCheckSum() {

	    String datafile = "./savedGames/event.ser";

	    MessageDigest md = null;
			byte[] dataBytes = new byte[1024];

			try {
				md = MessageDigest.getInstance("SHA1");
				FileInputStream fis = new FileInputStream(datafile);

				int nread = 0;

				while ((nread = fis.read(dataBytes)) != -1) {
					md.update(dataBytes, 0, nread);
				};

			} catch (FileNotFoundException e) {
				System.err.println("No save game");
			} catch (IOException i) {
				i.printStackTrace();
			} catch (NoSuchAlgorithmException xz) {
				System.err.println("SHA1 didn't work");
			}


	    byte[] mdbytes = md.digest();

	    //convert the byte to hex format
	    StringBuffer sb = new StringBuffer("");
	    for (int i = 0; i < mdbytes.length; i++) {
	    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	    }

	    return sb.toString();
		}
		private static void writeCheckSum() {
			String checksum = getCheckSum();
			try{
				FileOutputStream fileOut = new FileOutputStream("./savedGames/eventCheck.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(checksum);
				out.close();
				fileOut.close();
			}catch(IOException i){
				i.printStackTrace();
			}catch(NullPointerException n){
				n.printStackTrace();
			}
		}
		private static boolean checkCheckSum() {
			String gameCheckSum = getCheckSum();
			Scanner sc = null;
			try {
				sc = new Scanner(new File("eventCheck.ser"));
			} catch (Exception e) {
				System.err.println("checksum file problem");
				e.printStackTrace();
			}
			String readInCheckSum = sc.nextLine();

			if (gameCheckSum.equals(readInCheckSum)) return true;
			else return false;
		}
	}
