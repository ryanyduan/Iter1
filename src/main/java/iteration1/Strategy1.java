package iteration1;

import java.util.ArrayList;
import java.util.Iterator;

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
			
			if (runs.isEmpty() && sets.isEmpty()) {
				emptyMessage();
				over = true;
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
				
				if (runs.isEmpty() && sets.isEmpty()) {
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
				optimalMove();
			}
				
		}
		return true;
	}
	
	
	

}
