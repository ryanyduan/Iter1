package iteration1;

import java.util.ArrayList;

public class Player extends Observer {
	
	private String playerType;
	private String name;
	public ArrayList<Tile> Hand;
	public ArrayList<ArrayList<Tile>> runs;
	public ArrayList<ArrayList<Tile>> sets;
	private ArrayList<ArrayList<Tile>> optimalMoves;

	public Player(String type, Table table, String name) {
		this.playerType = type;
		this.Hand = new ArrayList<Tile>();
		this.table = table;
		this.name = name;
		this.table.attach(this);
		for (int i = 0; i < 14; i++) {
			this.Hand.add(this.table.draw());
		}
	}
	
	public void turn(){
		System.out.println("Player " + this.name + "'s turn");
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
	
	public int optimalMove(){
		
		optimalMoves = new ArrayList<ArrayList<Tile>>();
		ArrayList<ArrayList<Tile>> currentOptimalMoves = new ArrayList<ArrayList<Tile>>();
		int optimalLength = 0;
		int currentLength = 0;
		
		if (this.runs.isEmpty()) return -1; // if no possible runs just return empty ArrayList
		
		for (ArrayList<Tile> currentRun: this.runs) {
			currentLength = currentRun.size();
			
			if (!this.sets.isEmpty()) {
				for (Tile currentTile: currentRun) {
					for (ArrayList<Tile> set: this.sets) {
						if (set.contains(currentTile)){
							ArrayList<Tile> set_copy = new ArrayList<Tile>(set); //making a copy just in case remove() mutates the ArrayList
							set_copy.remove(currentTile);
							if (!isSet(set_copy) && (currentLength + set_copy.size() > optimalLength)) {
								ArrayList<Tile> run_copy = new ArrayList<Tile>(currentRun);
								run_copy.remove(currentTile);
								currentLength += set_copy.size();
								currentOptimalMoves.add(set_copy);
								currentOptimalMoves.add(run_copy);
							}
						}
					}
				}
			}
			
			if (currentLength > optimalLength) {
				optimalLength = currentLength;
				optimalMoves = currentOptimalMoves;
			}
		}
		
		for (ArrayList<Tile> currentSet: this.sets) {
			if (currentSet.size() > optimalLength) {
				optimalLength = currentSet.size();
				optimalMoves.clear();
				optimalMoves.add(currentSet);
			}
		}
		
		return 1;
		
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
		System.out.println(returnHand);
		return returnHand;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
