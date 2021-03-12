package consoleVersion;

/**
 * @author ChrisChris Marston
 * 2021
 * 
 * This class houses the code that handles the functionality of the game. Random event/item generation, etc.
 */

import java.util.Random;

public class MainGame {
	
	// ******************************* ALL METHODS TRANSFERRED TO GUI GAME *******************************
	
	/**
	 * This is the constructor class. It is currently being used to launch the game. Both this method and main run when the
	 * program is started.
	 */
	public MainGame() {
		GameMenus.startMenu();
		
	}
		
	/**
	 * This method randomly selects an item from those available as the item that the player successfully collected.
	 * @return An integer value that corresponds to an in-game item.
	 */
	public static int collectIngredients() {
		
		Random randomValueGenerator = new Random(); // Create an instance of an item from the Random class
		int upperBound = 6; // Random will pull a random value from between 0 and upperBound - 1
		int randomItemValue = randomValueGenerator.nextInt(upperBound);
		
		addItemToInventory(randomItemValue);
		
		return randomItemValue;

	}
	
	/**
	 * This method adds an item to the players inventory.
	 * 
	 * @param itemValue The integer value that represents the item the player is adding to their inventory. 
	 */
	public static void addItemToInventory(int itemValue) {
		
		if (itemValue != 0) { // Checking to make sure they found an item. 0 indicates no item found, so no addition to inventory.
			switch (itemValue) {
				case 1: // Water
					Player.inventory[1][0] = 1; // Mark the item possessed
					Player.inventory[1][1]++; // Add one to their owned item tally for that item
					break;
				case 2: // Bone meal
					Player.inventory[2][0] = 1;
					Player.inventory[2][1]++;
					break;
				case 3: // Flax Seed
					Player.inventory[3][0] = 1;
					Player.inventory[3][1]++;
					break;
				case 4: // Charcoal
					Player.inventory[4][0] = 1;
					Player.inventory[4][1]++;
					break;
				case 5: // Honey
					Player.inventory[5][0] = 1;
					Player.inventory[5][1]++;
					break;
			}
		}
	}
	
	/**
	 * This method will be used to determine what item was pulled from an array before printing its name
	 * to the console or using it for anything else.
	 * @param itemValue The integer value representing an item.
	 * @return A string containing the name associated with the item whose integer value was the parameter.
	 */
	public static String whatItemString(int itemValue) {
		
		switch (itemValue) {
			case 0:
				return "nothing";
				// UG #001 - Eventually add this to this line: "You search for a long time. But fail to find anything suited for potioncraft."
			case 1:
				return "Water";
			case 2:
				return "Bone Meal";
			case 3:
				return "Flax Seed";
			case 4:
				return "Charcoal";
			case 5:
				return "Honey";
		}
		return "No ingredients found. *Developer, you found a bug in your code.*";
	}

	/**
	 * This method prints all of the contents of the player's inventory to the console in a nicely formatted way.
	 * 
	 * Note: The array that is printed by this method must have inner arrays of equal lengths due to the Player.inventory[0].length
	 * statement in the inner for loop. If the lengths vary between inner arrays, this will fail to get all of the items in any that are
	 * longer than the first inner array.
	 */
	public static void printInventoryContents() {
		
		System.out.println("\n-----------------------------");
		System.out.println("----- Current Inventory -----");
		System.out.println("-----------------------------");

		/*
		 * This counter simply indicates if the inventory is empty or not. Would likely be better as its own method that checks the
		 * inventory then returns a boolean. But this will work for now.
		 */
		int emptyOrNot = 0;
		/*
		 * These loops loop over the Player.inventory array and checks prints its contents to the console if the player
		 * has at least one of the item in their possession. 
		 * 		  
		 * Both for loops start at index 1 because the first slot in the outer array is for the "nothing" item and the first slot
		 * in the inner array is a 1 or 0 indicating whether the player has any of that item or not.
		 */
		for (int outerIndex = 1; outerIndex < Player.inventory.length; outerIndex++) {
			
			for (int innerIndex = 1; innerIndex < Player.inventory[0].length; innerIndex++) {
				// If the possession slot of the inventory item has a value of 1 or greater, that item will be printed to the console.
				if (Player.inventory[outerIndex][1] > 0) {
					
					System.out.println(whatItemString(outerIndex) + ": " + Player.inventory[outerIndex][innerIndex]);
					
					emptyOrNot = 1;
				}	
			}
		}
		if (emptyOrNot < 1) { // Prints the following message if inventory is empty
			
			System.out.println("\n-----");
			System.out.println("You have no items in your inventory.");
			System.out.println("-----");
		} else { // Prints a line below the stuff printed by the for loops, but not below the empty inventory message
			System.out.println("-----------------------------\n"); 
		}
	}
	
	/**
	 * This method prints out the invalid item statement. Making a separate method allows me to not have to update
	 * the print line everywhere when I want to change it.
	 */
	public static void invalidInputPrinter() {
			
			System.out.println("\n-----------------------------------------");
			System.out.println("Selected option does not exist.");
			System.out.println("Please select from the available options.");
			System.out.println("-----------------------------------------");
		}
	
	/**
	 * This method terminates the game and prints a message that it did so.
	 */
	public static void exitGame() {
		
		if (GameSaveSystem.safeToTerminateGame == false) {
			GameSaveSystem.reqeustSave();
		}		
		
		System.out.println("\n--------------------");
		System.out.println("---- Game Ended ----");
		System.out.println("--------------------");
		System.exit(0);
	}	
	
	// Not sure what the main method is for in this class - I believe this is called a constructor class.
	public static void main(String[] args) {
		// Not sure why I needed this line. But I couldn't access this class without it.
		MainGame primaryGameObject = new MainGame();
	}

}
