package iteration1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

public class Human extends Player {
	
	public HashMap<Integer, ArrayList<Tile>> turnOptions;
	public HashMap<Integer, Tile> boardTurnOptions;
	public ArrayList<Tile> played;
	public int choice = 0;
	public int manualChoice;

	public Human(Table table, String name) {
		super(table, name);
	}

	@Override
	public boolean turn() {
		
		manualChoice = -1;
		
		//Finds all melds.  If no melds, draw a card and end turn.
		//If player has not broken initial 30 points, require they play a meld with 30 points
		//Shows player list of melds to play and executes the move the player chooses
		
		runs = this.findRuns();
		sets = this.findSets();
		
		possibleTiles = this.table.getPossibleTiles();
		for (Iterator<Entry<Integer, ArrayList<Tile>>> it = possibleTiles.entrySet().iterator(); it.hasNext(); ) {
			Entry<Integer, ArrayList<Tile>> choice = it.next();
			for (Tile t: choice.getValue()) {
				if (!this.Hand.contains(t)) {
					choice.getValue().remove(t);
				}
			}
		}
		
		if (runs.isEmpty() && sets.isEmpty() && possibleTiles.isEmpty()) {
			emptyMessage();
			return false;
		}
		
		if (!this.is30) {
			for (Iterator<ArrayList<Tile>> it = runs.iterator(); it.hasNext(); ) {
				ArrayList<Tile> run = it.next();
				if (value(run) < 30) {
					it.remove();
				}
			}
			
			for (Iterator<ArrayList<Tile>> it = sets.iterator(); it.hasNext(); ) {
				ArrayList<Tile> set = it.next();
				if (value(set) < 30) {
					it.remove();
				}
			}
			
			possibleTiles.clear();
		}
		
		turnOptions = new HashMap<Integer, ArrayList<Tile>>();
		boardTurnOptions = new HashMap<Integer, Tile>();
		
		int counter = 0;
		for (ArrayList<Tile> run: runs) {
			turnOptions.put(counter, run);
			counter++;
		}
		
		for (ArrayList<Tile> set: sets) {
			turnOptions.put(counter, set);
			counter++;
		}
		
		for (Iterator<Entry<Integer, ArrayList<Tile>>> it = possibleTiles.entrySet().iterator(); it.hasNext(); ) {
			Entry<Integer, ArrayList<Tile>> choice = it.next();
			turnOptions.put(choice.getKey()+counter, choice.getValue());
		}
		
		if (!turnOptions.isEmpty()) {
			System.out.println("Here are your options of melds to play");
			printMap(turnOptions);
			printMap(boardTurnOptions);
			System.out.println("Choose the number corresponding to the tiles you want to play.");
			Scanner scan = new Scanner(System.in);
			choice = scan.nextInt();
			while (!turnOptions.containsKey(choice)) {
				System.out.println("Choose the number corresponding to the tiles you want to play.");
				choice = scan.nextInt();
			}
			
			scan.close();
			
			if (choice >= counter) {
				manualChoice = choice-counter;
				System.out.println(manualChoice);
			}
			
			executeMove();
		}
		
		else {
			emptyMessage();
			return false;
		}
		
		return true;
	}
	
	@Override
	public void executeMove() {
		
		// Play the meld onto the board (showing with '*' that it was just played) and also remove the corresponding tiles from player's hand 
		// If player has just "broken" his rule about not being able to play until he has 30 points, the rule is now broken (is30 = true)
		// Displays Hand
		// Displays Table
		
		played = turnOptions.remove(choice);
		
		for (Tile t: played) {
			t.justPlayed = true;
		}
		
		if (manualChoice != -1) {
			table.Board.get(manualChoice).add(played.get(0));
		}
		else table.Board.add(played);
		
		for (Iterator<Tile> tiles = this.Hand.iterator(); tiles.hasNext();) {
			Tile toRemove = tiles.next();
			if (played.contains(toRemove)) {
				tiles.remove();
			}
		}
		
		this.is30 = true;
		
		Collections.sort(this.Hand);
		this.displayHand();
		table.displayBoard();
		for (Tile t: played) {
			t.justPlayed = false;
		}
	}	
	
	public void printMap(HashMap map) {
		
		// Print each of the player's possible melds he/she can play
		
	    Iterator<Entry> it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        HashMap.Entry pair = (HashMap.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	    }
	}
	

}
