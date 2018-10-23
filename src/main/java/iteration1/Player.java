package iteration1;

import java.util.ArrayList;

public class Player extends Observer {
	
	private String playerType;
	public ArrayList<Tile> Hand;
	public ArrayList<ArrayList<Tile>> runs;
	public ArrayList<ArrayList<Tile>> sets;
	private ArrayList<Tile> optimalMove;

	public Player(String type, Table table) {
		this.playerType = type;
		this.Hand = new ArrayList<Tile>();
		this.table = table;
		this.table.attach(this);
	}

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
	
	public ArrayList<Tile> optimalMove(){
		optimalMove = new ArrayList<Tile>();
		int optimalLength = 0;
		
		if (this.runs.isEmpty()) return optimalMove;
		
		for (ArrayList<Tile> t: this.runs) {
			optimalLength = t.size();
			for (Tile tile: t) {
				if (!this.sets.isEmpty()) {
					for (ArrayList<Tile> set: this.sets) {
						if (set.contains(tile)){
							ArrayList<Tile> set_copy = new ArrayList<Tile>(set); //making a copy just in case remove() mutates the ArrayList
							set_copy.remove(tile);
							if (isSet(set_copy) && optimalLength < set_copy.size()+1) optimalLength += set_copy.size();
						}
					
					}
				}
			}
			if (t.size() > optimalLength) {
				optimalLength = t.size();
				optimalMove = t;
			}
		}
		
		for (ArrayList<Tile> t: this.sets) {
			if (t.size() > optimalLength) {
				optimalLength = t.size();
				optimalMove = t;
			}
		}
		
		return optimalMove;
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
	
	public String displayHand(){
		String returnHand = "";
		for (Tile t: this.Hand) {
			returnHand += t.toString();
		}
		return returnHand;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
