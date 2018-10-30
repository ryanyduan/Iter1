package iteration1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Table {
	
	private static char[] COLORS = {'O','G','R','B'};
	public ArrayList<Tile> Deck;
	public ArrayList<ArrayList<Tile>> Board;
	public ConcurrentHashMap<Integer, ArrayList<Tile>> possibleMoves;
	public ArrayList<Tile> possibleTiles;
	private List<Observer> observers;
	private int lowestTilesInHand;
	
	public Table() {
		observers = new ArrayList<Observer>();
		Deck = new ArrayList<Tile>();
		Board = new ArrayList<ArrayList<Tile>>();
		createDeck();
	}
	
	public Tile draw() {
		return Deck.remove(0);
	}
	
	private void createDeck() {
		for (int i = 1; i < 14; i++) {
			for (char c: COLORS) {
				for (int j = 0; j < 2; j++) {
					Deck.add(new Tile(c, i));
				}
			}
		}
		Collections.shuffle(Deck);
	}
	
	public String displayBoard() {
		String boardMelds = "Board: "; 
		for (ArrayList<Tile> meld : this.Board) {
			boardMelds += "{" + meld.toString() + "}";
		}
		System.out.println(boardMelds);
		return boardMelds;
	}
	
	public ConcurrentHashMap<Integer, ArrayList<Tile>> getPossibleTiles(){
		possibleMoves = new ConcurrentHashMap<Integer, ArrayList<Tile>>();
		int index = 0;
		for (ArrayList<Tile> meld: Board) {
			possibleTiles = new ArrayList<Tile>();
			if (isRun(meld)) {
				char currentColour = meld.get(0).getColour();
				if(meld.get(0).getRank() > 1) {
					int oneLowerRank = meld.get(0).getRank()-1;
					int oneHigherRank = meld.get(meld.size()-1).getRank() + 1;
					possibleTiles.add(new Tile(currentColour, oneLowerRank));
					if (oneHigherRank<=14) {
						possibleTiles.add(new Tile(currentColour, oneHigherRank));
					}
				}
				else {
					int oneHigherRank = meld.get(meld.size()-1).getRank() + 1;
					if (oneHigherRank<=14) {
						possibleTiles.add(new Tile(currentColour, oneHigherRank));
					}
				}
			}
			// if it's not a run then it's a set
			else {
				if (meld.size() == 3) {
				
					boolean red = false;
					boolean blue = false;
					boolean green = false;
					boolean orange = false;
					
					
					for (Tile t: meld) {
						if (t.getColour() == 'R') red = true;
						else if (t.getColour() == 'B') blue = true;
						else if (t.getColour() == 'G') green = true;
						else if (t.getColour() == 'O') orange = true;
					}
					
					int setRank = meld.get(0).getRank();
					if (!red) possibleTiles.add(new Tile('R', setRank));
					if (!blue) possibleTiles.add(new Tile('B', setRank));
					if (!green) possibleTiles.add(new Tile('G', setRank));
					if (!orange) possibleTiles.add(new Tile('O', setRank));
				}
			}
		
				possibleMoves.put(index, possibleTiles);
				index++;
			
		}
		return possibleMoves;
	}
	
	public int getState() {
		lowestTilesInHand = observers.get(0).Hand.size();
		for (Observer o: this.observers) {
			if (o.Hand.size() < lowestTilesInHand) lowestTilesInHand = o.Hand.size();
		}
		return lowestTilesInHand;
	}
	
	public void attach(Observer observer){
	      observers.add(observer);	
	}
	
	public boolean s2Condition() {
		for (Observer o: this.observers) {
			if (o.is30) return true;
		}
		return false;
	}
	
		public boolean isRun(List<Tile> list) {
		
		//	this method checks if an existing run is still a run even after taking out a certain tile
		
		if (list.size() < 3) return false;
		
		//here we'll use the formula for an arithmetic sequence as a shortcut to see if it's a set since we have the 'a' and 'n' and 'd'
		// Sum of arithmetic sequence = n/2 * (2a + (n-1)d)

		double tryRunSum = 0;
		for (Tile t: list) {
			tryRunSum += t.getRank();
		}

		double n = (double) list.size();
		
		if ((n / 2.0) * (2*list.get(0).getRank() + (list.size()-1)) != tryRunSum) return false;
		
		return true;
		
	}
	
}
