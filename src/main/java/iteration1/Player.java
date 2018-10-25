package iteration1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public abstract class Player extends Observer {
	
	private String name;
	public ArrayList<Tile> Hand;
	public ArrayList<ArrayList<Tile>> runs;
	public ArrayList<ArrayList<Tile>> sets;
	public ArrayList<ArrayList<Tile>> optimalMoves;
	public boolean is30 = false;
	public int possibleSetsLength;
	public ArrayList<ArrayList<Tile>> possibleSets;

	public Player(Table table, String name) {
		this.Hand = new ArrayList<Tile>();
		this.table = table;
		this.name = name;
		this.table.attach(this);
		for (int i = 0; i < 14; i++) {
			this.Hand.add(this.table.draw());
		}
		Collections.sort(this.Hand);
	}
	
	public abstract boolean turn();

	public ArrayList<ArrayList<Tile>> findRuns(){
		// reset possible runs 
		runs = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> matchedTiles = new ArrayList<Tile>();
		char initialColour;
		for (Tile t: this.Hand) {
			if (matchedTiles.contains(t)) continue; //if we've found a run using this tile, don't use it to find more runs
		
			ArrayList<Tile> possibleRunList = new ArrayList<Tile>();
			
			possibleRunList.add(t);
			initialColour = t.getColour();
		
			int currentNum = t.getRank();
			for (Tile possibleTile: this.Hand) {
				if (possibleTile.getColour() == initialColour) {
					if (possibleTile.getRank() == currentNum+1) {
						possibleRunList.add(possibleTile);
						currentNum = possibleTile.getRank();
					}
					else if (possibleTile.getRank() == currentNum) {
						continue;
					}
				}
			}
			
			if (possibleRunList.size() >= 3) {
				runs.add(possibleRunList); 
				for (Tile matched : possibleRunList) matchedTiles.add(matched);
			}
		}
		return runs;
	}
	
	public ArrayList<ArrayList<Tile>> findSets(){
		
		sets = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> matchedTiles = new ArrayList<Tile>();
		
		for (Tile t: this.Hand) {
			if (matchedTiles.contains(t)) continue; // if we've found a set using this tile, don't use it to find more sets
			ArrayList<Tile> possibleSetList = new ArrayList<Tile>();
			
			possibleSetList.add(t);
			ArrayList<Character> prevColours = new ArrayList<Character>(); 
			prevColours.add(t.getColour());
			for (Tile possibleTile: this.Hand) {
				if (possibleTile.getRank() == t.getRank()) {
					if (!prevColours.contains(possibleTile.getColour())) {
						possibleSetList.add(possibleTile);
						prevColours.add(possibleTile.getColour());
					}
				}
			}
		
			if (possibleSetList.size() >= 3) {
				sets.add(possibleSetList);
				for (Tile matched: possibleSetList) matchedTiles.add(matched);
			}
		}
		return sets;
	}
	
	public int optimalMove(){
		
		if (!(optimalMoves == null) && !optimalMoves.isEmpty()) {
			executeMove();
		}
		
		optimalMoves = new ArrayList<ArrayList<Tile>>();
		ArrayList<ArrayList<Tile>> currentOptimalMoves = new ArrayList<ArrayList<Tile>>();
		int optimalLength = 0;
		
		if (this.runs.isEmpty()) return -1; // if no possible runs just return empty ArrayList
		
		for (ArrayList<Tile> currentRun: this.runs) {
			possibleSets = new ArrayList<ArrayList<Tile>>();
			possibleSetsLength = 0;
			int currentRunLength = currentRun.size();
			
			if (!this.sets.isEmpty()) {
				for (Tile currentTile: currentRun) {
					for (ArrayList<Tile> set: this.sets) {
						if (set.contains(currentTile)){
							ArrayList<Tile> set_copy = new ArrayList<Tile>(set); //making a copy just in case remove() mutates the ArrayList
							set_copy.remove(currentTile);
							
							// for example if we have 12345 but inside we have 333 444, if we play 12345 we wont be able to play 333,444 
							//which is the optimal move
							
							if (!isSet(set_copy)) {
								possibleSetsLength += set.size();
								possibleSets.add(set);
							}
							
							
//							if (!isSet(set_copy) && (currentLength + set_copy.size() > optimalLength)) {
//								ArrayList<Tile> run_copy = new ArrayList<Tile>(currentRun);
//								run_copy.remove(currentTile);
//								currentLength += set_copy.size();
//								currentOptimalMoves.add(set_copy);
//								currentOptimalMoves.add(run_copy);
//							}
						}
					}
				}
			}
			
			if (possibleSetsLength > currentRunLength && possibleSetsLength > optimalLength) {
				optimalMoves.clear();
				optimalLength = possibleSetsLength;
				optimalMoves = possibleSets;
				executeMove();
			}
			else if (possibleSetsLength < currentRunLength && currentRunLength > optimalLength){
				optimalMoves.clear();
				optimalLength = currentRunLength;
				optimalMoves.add(currentRun);
				this.table.Board.add(optimalMoves.remove(0));
			}
		}
		
		// next possible difficult scenario is 123333 where 3333 will be the longest but the best move is actually 333 123
		
//		for (ArrayList<Tile> currentSet: this.sets) {
//			if (currentSet.size() > optimalLength) {
//				optimalLength = currentSet.size();
//				optimalMoves.clear();
//				optimalMoves.add(currentSet);
//			}
//		}
		
		return 1;
		
	}
	
	public void executeMove() {
		
		for (Tile t: optimalMoves.get(0)) {
			t.justPlayed = true;
		}
		
		ArrayList<Tile> tempPlayed = optimalMoves.get(0);
		this.table.Board.add(optimalMoves.remove(0));
		
		for (Iterator<Tile> tiles = this.Hand.iterator(); tiles.hasNext();) {
			Tile toRemove = tiles.next();
			if (tempPlayed.contains(toRemove)) {
				tiles.remove();
			}
		}
		
		Collections.sort(this.Hand);
		this.displayHand();
		table.displayBoard();
		for (Tile t: tempPlayed) {
			t.justPlayed = false;
		}
		
	}
	
	public boolean isRun(ArrayList<Tile> tryRun) {
		
		if (tryRun.size() < 3) return false;
		
		//here we'll use the formula for an arithmetic sequence as a shortcut to see if it's a set since we have the 'a' and 'n' and 'd'
		// Sum of arithmetic sequence = n/2 * (2a + (n-1)d)

		int tryRunSum = 0;
		for (Tile t: tryRun) {
			tryRunSum += t.getRank();
		}
		
		if ((tryRun.size() / 2) * (2*tryRun.get(0).getRank() + (tryRun.size()-1)) != tryRunSum) return false;
		
		return true;
		
	}
	
	public boolean isSet(ArrayList<Tile> trySet) {
		if(trySet.size() < 3) return false;
		return true;
	}
	
	public int value(ArrayList<Tile> meld) {
		
		// Calculate the value of a meld
		
		int value = 0;
		for (Tile t: meld) {
			value += t.getRank();
		}
		return value;
	}
	
	public void draw() {
		this.Hand.add(table.Deck.remove(0));
	}
	
	public String displayHand(){
		String returnHand = this.name + "'s hand: ";
		for (Tile t: this.Hand) {
			returnHand += t.toString();
		}
		System.out.println(returnHand);
		return returnHand;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public String getName() {
		return this.name;
	}

}
