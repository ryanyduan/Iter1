package iteration1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

public class Human extends Player {
	
	public HashMap<Integer, ArrayList<Tile>> turnOptions;
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
			ArrayList<Tile> valueCopy = new ArrayList<Tile>(choice.getValue());
			for (Tile t: valueCopy) {
				boolean in = false;
				for (Tile handTile: this.Hand) {
					if (handTile.getRank() == t.getRank() && handTile.getColour() == t.getColour()){
						in = true;
						possibleTiles.get(choice.getKey()).remove(t);
						possibleTiles.get(choice.getKey()).add(handTile);
						break;
					}
				}
				if (!in) {
					possibleTiles.get(choice.getKey()).remove(t);
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
			if (!choice.getValue().isEmpty()) {
				turnOptions.put(choice.getKey()+counter, choice.getValue());
			}
		}
		
		if (!turnOptions.isEmpty()) {
			System.out.println("Here are your options of melds to play");
			printMap(turnOptions);
			System.out.println("Choose the number corresponding to the tiles you want to play.");
			Scanner scan = new Scanner(System.in);
			choice = scan.nextInt();
			while (!turnOptions.containsKey(choice)) {
				System.out.println("Choose the number corresponding to the tiles you want to play.");
				choice = scan.nextInt();
			}
			
			if (choice >= counter) {
				manualChoice = choice-counter;
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
		
		for (Iterator<Tile> tiles = this.Hand.iterator(); tiles.hasNext();) {
			Tile toRemove = tiles.next();
	
			if (played.contains(toRemove)) {
				tiles.remove();
			}
		}
		
		
		if (manualChoice != -1) {
			if (played.get(0).getRank() < table.Board.get(manualChoice).get(0).getRank()) {
				table.Board.get(manualChoice).add(0, played.get(0));
			}
			else {
				table.Board.get(manualChoice).add(played.get(0));
			}
			
			if (played.size() == 2) {
				table.Board.get(manualChoice).add(played.get(1));
				table.Board.get(manualChoice).add(0, played.get(0));
			}
		}
		
		else table.Board.add(played);
		

		
		this.is30 = true;
		
		Collections.sort(this.Hand);
		this.displayHand();
		table.displayBoard();
		for (Tile t: played) {
			t.justPlayed = false;
		}
		
		if (played.size() == 1) played.remove(0).justPlayed = false;
		if (played.size() == 2) {
			played.remove(0).justPlayed = false;
			played.remove(0).justPlayed = false;
		}
		
	}	
	
	public void printMap(HashMap<Integer, ArrayList<Tile>> map) {
		
		// Print each of the player's possible melds he/she can play
		
	    Iterator<Entry<Integer, ArrayList<Tile>>> it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        HashMap.Entry<Integer, ArrayList<Tile>> pair = (Entry<Integer, ArrayList<Tile>>)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	    }
	}
	

}
