package consoleVersion;

/**
 * @author Chris Marston
 * 2021
 * 
 * This class acts to create the player object.
 * 
 * NOTE: Currently, the game does not have any objects created from this class. It is simply being used as a place
 * to house the inventory array and some other basic variables so as to make the variables and inventory "global."
 */

import java.util.*;

public class Player {
	
	/**
	 * Note: The first inventory slot in the inventory will always be empty, b/c 0 is the value assigned to "no item".
	 * All items therefore start getting added at inventory index 1.
	 * 
	 * Player Array Setup: The outer array is the inventory. Each of the inner arrays represents an item starting at index 1
	 * of the outer array. The inner arrays have two slots. The first slot, states that the player possesses the item or not
	 * (1 for possession, 0 for not possessed)
	 */
	static int[][] inventory = new int[6][2];
	
	// The player's name. Deleted when game is closed, but saved to the save file if player saves game.
	static String playerName = "";
	
	// Constructor class
	public Player() {
		
	}
	
	public static void main(String[] args) {

		// This will initialize the empty inventory. Currently set to create a 2D array with room for
		// the 5 items that exist in the game and 2 slots in each for if the player has it and how much of it.
	
	}
	
//	// This method will be called by other methods to add or remove items from the inventory
//	public static void inventoryManager(WILL NEED PARAMETERS FOR WHICH ITEM TO ACCESS AND WHAT MODIVFICATION TO APPLY) {
//		
//	}

}
