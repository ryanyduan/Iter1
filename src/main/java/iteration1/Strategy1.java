package iteration1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

public class Strategy1 extends Player {

	public Strategy1(Table table, String name) {
		super(table, name);
	}

	@Override
	public boolean turn() {
		
		// If player has previously calculated optimalMoves, play those moves
		
		if (!(optimalMoves == null) && !optimalMoves.isEmpty()) {
			executeMove();
		}
		
		else {
			
			// If not find all runs and sets in Player's hand 
			// If player has not broken 30, make sure they have to break 30
			
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
			
			boolean check = false;
			for (Iterator<Entry<Integer, ArrayList<Tile>>> it = possibleTiles.entrySet().iterator(); it.hasNext(); ) {
				Entry<Integer, ArrayList<Tile>> choice = it.next();
				if (!choice.getValue().isEmpty()) {
					check = true;
					break;
				}
			}
			
			if (runs.isEmpty() && sets.isEmpty() && !check) {
				emptyMessage();
				over = true;
				return false;
			}
			
			System.out.println(possibleTiles);
			
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
				
				if (runs.isEmpty() && sets.isEmpty() && !check) {
					emptyMessage();
					over = true;
					return false;
				}
				
				// If Player is able to break 30, let them play their optimalMove
				else {
					optimalMove();
				}
			}
			
			
			// If Player has already broken 30, let them play their optimalMove
			else {
				System.out.println("P1 HERE??");
				optimalMove();
			}
				
		}
		return true;
	}
	
	
	

}
