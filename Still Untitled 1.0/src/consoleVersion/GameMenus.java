package consoleVersion;

/**
 * @author Chris Marston
 * 2021
 * 
 * This class houses all of the menus in the game.
 */

import java.util.Scanner;

// This class houses all of the menus for the game.

public class GameMenus {

	public GameMenus() {
		
	}
	
	/**
	 * This is the first menu that opens up when you start the game. It contains only the options to load a new game, start a new one, or exit.
	 */
	public static void startMenu() {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\n-------------------------------------");
		System.out.println("----- Welcome to Still Untitled -----");
		System.out.println("-------------------------------------");
		System.out.println("------------- START MENU ------------\n");
			
		while (true) {
			
			System.out.println("1 - Start New Game");
			System.out.println("2 - Load Game");
			System.out.println("0 - Exit Game");
			// Collects the input from the user. Needs to be inside the while loop to ensure that in the event of a bad input,
			//the while loop prints the message in the else block then stops again at this line to wait for a new input.
			String selectedOption = input.nextLine();
		
			switch (selectedOption) {
				case "1":
					GameSaveSystem.findEmptySaveFile();
					GameSaveSystem.requestPlayerInfoMenu();
					mainMenu();
					break;
				case "2":
					gameSavesMenu();
					GameSaveSystem.loadGame();
					break;
				case "0":
					GameSaveSystem.safeToTerminateGame = true;
					MainGame.exitGame();
					break;
				default:
					MainGame.invalidInputPrinter();
					break;
			}
		}
	}
	
	/**
	 * This is the primary menu. It calls other methods in this class as they are selected by the user.
	 * This method is going to get quite large. May want to move all of the methods it calls to other class files.
	 */
	public static void mainMenu() {
			
		Scanner input = new Scanner(System.in);
			
		System.out.println("\n-------------------------------------");
		System.out.println("------------- MAIN MENU -------------");
		System.out.println("-------------------------------------\n");
			
		while (true) {
			
			System.out.println("What would you like to do?");
			System.out.println("1 - Collect Ingredients");
			System.out.println("2 - Check Inventory");
			System.out.println("9 - Save Game");
			System.out.println("0 - Exit Game");
			/*
			 * Collects the input from the user. Needs to be inside the while loop to ensure that in the event of a bad input,
			 * the while loop prints the message in the else block then stops again at this line to wait for a new input.
			 */
			String selectedOption = input.nextLine();
		
			switch (selectedOption) {
				case "1":
					collectIngredientsMenu();
					break;
				case "2":
					MainGame.printInventoryContents();
					break;
				case "9":
					GameSaveSystem.saveGame();
					break;
				case "0":
					MainGame.exitGame();
					break;
				default:
					MainGame.invalidInputPrinter();
					break;
			}
		}
	}
	
	/**
	 * This menu allows a player to choose which saved game they would like to load and play.
	 */
	public static void gameSavesMenu() {
		
		boolean optionSelected = false;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\n-------------------------------------");
		System.out.println("---------- GAME SAVES MENU ----------");
		System.out.println("-------------------------------------");
			
		while (optionSelected != true) {
			
			System.out.println("Which game would you like to load?");
		
			// Put the print lines in an if block to allow for the name output to be either NO DATA or the playerName
			System.out.println("1 - Load Save 1");
			System.out.println("2 - Load Save 2");
			System.out.println("3 - Load Save 3");
			System.out.println("9 - Return to Start Menu");
			/*
			 * Collects the input from the user. Needs to be inside the while loop to ensure that in the event of a bad input,
			 * the while loop prints the message in the else block then stops again at this line to wait for a new input.
			 */
			String selectedOption = input.nextLine();
		
			switch (selectedOption) {
			
				case "1":
					// Some statement to load the correct game
					GameSaveSystem.currentSaveFileName = "SaveFile1.txt";
					optionSelected = true;
					break;
				case "2":
					GameSaveSystem.currentSaveFileName = "SaveFile2.txt";
					optionSelected = true;
					break;
				case "3":
					GameSaveSystem.currentSaveFileName = "SaveFile3.txt";
					optionSelected = true;
					break;
				case "9":
					startMenu();
					break;
				default:
					MainGame.invalidInputPrinter();
			}
		}
		
	}
	
	/**
	 * This method allows the player to select a game file to overwrite
	 * 
	 * This method does not allow for a player to wipe an existing save. That is the saveWipeMenu
	 */
	public static void saveOverwriteMenu() {
		
		boolean optionSelected = false;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\n------------------------------------");
		System.out.println("------- Save Overwrite Menu --------");
		System.out.println("------------------------------------\n");
			
		while (optionSelected != true) {
			System.out.println("Sorry. There are no available save slots! However, you may overwrte an existing file.");
			System.out.println("Which file would you like to overwrite?");
			System.out.println("NOTE: ALL DATA ON THE CHOSEN FILE WILL BE LOST!\n");
			System.out.println("1 - Save 1");
			System.out.println("2 - Save 2");
			System.out.println("3 - Save 3");
			System.out.println("9 - Return to Start Menu");
			
			
			/*
			 * Collects the input from the user. Needs to be inside the while loop to ensure that in the event of a bad input,
			 * the while loop prints the message in the else block then stops again at this line to wait for a new input.
			 */
			String selectedOption = input.nextLine();
		
			switch (selectedOption) {
				case "1":
					GameSaveSystem.currentSaveFileName = "SaveFile1.txt";
					optionSelected = true;
				case "2":
					GameSaveSystem.currentSaveFileName = "SaveFile2.txt";
					optionSelected = true;
				case "3":
					GameSaveSystem.currentSaveFileName = "SaveFile3.txt";
					optionSelected = true;
				case "9":
					startMenu();
				default:
					MainGame.invalidInputPrinter();
			}
		}
		
	}
	
	/**
	 * This method allows a player to select a save file to wipe.
	 * 
	 * This menu is accessible from the Main Menu
	 */
	public static void saveWipeMenu() {
		
	}
	
	/**
	 * This method manages the menu for gathering items in the game. It does not run the logistics of gathering the items, however.
	 * That is the function of the collectIngredients method.
	 */
	public static void collectIngredientsMenu() {
			
		Scanner input = new Scanner(System.in);
		
		System.out.println("\n---------------------------------------");
		System.out.println("----- Ingredients Collection Menu -----");
		System.out.println("---------------------------------------");
		
		while (true) {
			
			System.out.println("What would you like to do?\n");
			System.out.println("1 - Collect Ingredients");
			System.out.println("2 - Check Inventory");
			System.out.println("3 - Return to Main Menu");
			
			String selectedOption = input.nextLine();
			
			switch (selectedOption) {
				case "1":
					// Generates a random item, then prints the name of that item to the console.
					int newItemValue = MainGame.collectIngredients();
					System.out.println("\n-----");
					System.out.println("You successfully collect " + MainGame.whatItemString(newItemValue));
					System.out.println("\n-----");
					break;
				case "2":
					MainGame.printInventoryContents();
					break;
				case "3":
					mainMenu();
					break;
				default:
					MainGame.invalidInputPrinter();
					break;
			}
		}
	}

	public static void main(String[] args) {

	}

}
