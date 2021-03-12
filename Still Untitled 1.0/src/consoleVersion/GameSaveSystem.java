package consoleVersion;

/**
 * @author Chris Marston
 * 2021
 * 
 * This class houses all of the methods that manage tasks associated with saving and loading the game.
 * 
 * IMPORTANT: The "blank" save files used for saving game data MUST have a true or false in the first space of line 1.
 * This is how the game knows if a save file exists in a given file or not. I have not taken the time to put a 
 * check for a blank slot in yet, so this MUST be done.
 */

import java.io.*;
import java.util.*;

public class GameSaveSystem {
	
	/*
	 * This boolean allows the requestSave and exitGame methods to communicate that the first has already
	 * run and it is okay for exitGame to proceed with terminating the program.
	 */
	static boolean safeToTerminateGame = false;
	// This boolean tells the saveGame method and other methods if the game has existing save data on file
	static boolean existingSave = false;
	// This boolean prevents the saveOverwite method from running endlessly as a result of it's call in the saveGame method
	static boolean saveOverwriteCheckRan = false;
	// This String holds the name of the save file corresponding to the game that is currently being played
	static String currentSaveFileName = "";
	
	// Constructor class
	public GameSaveSystem() {
		
	}
	
	/**
	 * This method loads the game from the save file. In this case SaveFile1.txt
	 */
	public static void loadGame() {
		
		try { // All save functionality needs to be within the try block due to scope
			
			// BufferedReader is the class in Java that allows you to read data from a .txt file
			BufferedReader saveReader = new BufferedReader(new FileReader(currentSaveFileName) );
			// Sets the value for existingSave based on the data from the save file to mark if the game has existing save data or not. 0 for no data, 1 for existing data.

			existingSave = Boolean.parseBoolean(saveReader.readLine() );
			Player.playerName = saveReader.readLine();
			
			for (int outerIndex = 0; outerIndex < Player.inventory.length; outerIndex++) {
				
				for (int innerIndex = 0; innerIndex < Player.inventory[0].length; innerIndex++) {
					
					// Should write to the inventory in the same order that the items in the save file were written to the file from the original inventory.
					Player.inventory[outerIndex][innerIndex] = Integer.parseInt(saveReader.readLine() );
					
				}
			}
			// Closes the writer after the method finishes using it
			saveReader.close();
			
		} catch (Exception e) {
			
		}
		
		System.out.print("\n-----");
		System.out.println("Game Loaded Successfully");
		System.out.println("-----");
		
		GameMenus.mainMenu();
	}
	
	/**
	 * This method writes the save data to the text file SaveFile1.txt
	 */
	public static void saveGame() {
		
		if (saveOverwriteCheckRan != true) {
			confirmSaveOverwrite(); // This method checks to see if the current game already has save data on file
		}
		
		try { // All save functionality needs to be within the try block due to scope
			
			// BufferedWriter is the class in Java that allows you to write to a text file
			BufferedWriter saveWriter = new BufferedWriter(new FileWriter(currentSaveFileName) );
			
			saveWriter.write("true"); // Marks the game as having a save file. 1 indicates saved, 0 indicates not saved
			saveWriter.newLine();
			
			saveWriter.write(Player.playerName);
			saveWriter.newLine();
			
			for (int outerIndex = 0; outerIndex < Player.inventory.length; outerIndex++) {
				
				for (int innerIndex = 0; innerIndex < Player.inventory[0].length; innerIndex++) {
					
					/*
					 * These write statements should write the integer values from the player's inventory to the .txt file
					 * with each integer on a new line. They will go from top to bottom in the order of: item possessed or not (0 or 1),
					 * how many of that item ("0 to infinity"). Then repeat with each set of two numbers representing one item.
					 */				
					saveWriter.write("" + Player.inventory[outerIndex][innerIndex]); // The write method of BufferedWriter requires a string parameter, hence the string concatenation.
					saveWriter.newLine();
					
				}
			}
			// Closes the writer after the method finishes using it
			saveWriter.close();
			
		} catch (Exception e) {
			
		}
		
		System.out.println("\n-----");
		System.out.println("Game Saved Successfully");
		System.out.println("-----");
		
	}

	/**
	 * This method prompts the player to save the game when they choose to quit the game.
	 * It does not check to see if they have recently saved. It always asks.
	 */
	public static void reqeustSave() {
		
		Scanner input = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("\n-----");
			System.out.println("Would you like to save your game before exiting?");
			System.out.println("-----");
			System.out.println("1 - Save Game");
			System.out.println("0 - Exit without saving");
			
			String selectedOption = input.nextLine();

			switch (selectedOption) {
				case "1":
					saveGame();
					safeToTerminateGame = true;
					MainGame.exitGame();
				case "0":
					safeToTerminateGame = true;
					MainGame.exitGame();
				default:
					MainGame.invalidInputPrinter();
			}
		}
	}

	/**
	 * This method notifies the player if there is existing save data when they choose to save, and asks them
	 * if they want to overwrite the data or not.
	 */
	public static void confirmSaveOverwrite() {
		
		saveOverwriteCheckRan = true;
		
		if (existingSave == true) { // If save data already on file, this should run. It shouldn't run otherwise.
			
			Scanner input = new Scanner(System.in);
			
			while (true) {
				
				System.out.println("-----");
				System.out.println("This game has data on file.");
				System.out.println("Would you like to overwrite the existing game data?");
				System.out.println("-----");
				System.out.println("1 - Yes, overwrite existing data");
				System.out.println("0 - No, don't save game");
				
				String selectedOption = input.nextLine();
				
				switch (selectedOption) {
					case "1":
						saveGame();
						safeToTerminateGame = true;
						MainGame.exitGame(); // Issue on this line see BUG #001
					case "0":
						MainGame.invalidInputPrinter();
					default:
						MainGame.invalidInputPrinter();
				}
			}
		}
	}
	
	/**
	 * This method asks the player for their name, then assigns it to the Player.playerName variable
	 * 
	 * This method is part of allowing the game to save the player's name to their save file
	 */
	public static void requestPlayerInfoMenu() {
		
		Scanner input = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("What is your name?");
			
			String selectedOption = input.nextLine();
			
			if (selectedOption.isEmpty() ) {
				MainGame.invalidInputPrinter();
				
			} else {
				Player.playerName = selectedOption;
				break;
			}
		}
		
	}
	
	/**
	 * This method checks all of the available save files to find one without save data on it.
	 * If it doesn't find an empty file, it notifies the player and redirects them to the saveOverwrite menu.
	 * 
	 */
	public static void findEmptySaveFile() {
		
		try {
			
			BufferedReader saveFile1Reader = new BufferedReader(new FileReader("SaveFile1.txt") );
			BufferedReader saveFile2Reader = new BufferedReader(new FileReader("SaveFile2.txt") );
			BufferedReader saveFile3Reader = new BufferedReader(new FileReader("SaveFile3.txt") );
			
			int saveFile1Status = Integer.parseInt(saveFile1Reader.readLine() );
			int saveFile2Status = Integer.parseInt(saveFile2Reader.readLine() );
			int saveFile3Status = Integer.parseInt(saveFile3Reader.readLine() );
			
			// Checks the first slot of each of the save files to see if it has a 1 or 0
			if (saveFile1Status == 0) {
				currentSaveFileName = "SaveFile1.txt";
				
			} else if (saveFile2Status == 0) {
				currentSaveFileName = "SaveFile2.txt";
				
			} else if (saveFile3Status == 0) {
				currentSaveFileName = "SaveFile3.txt";
				
			} else {
				// Redirect to the saveOverwriteMenu
				GameMenus.saveOverwriteMenu();
			}
			
		} catch (Exception e) {
			
		}
		
	}
	
	public static void fileWiper(String saveFileName) {
		
	}
	
	public static void main(String[] args) {

	}

}
