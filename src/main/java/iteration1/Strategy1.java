package iteration1;

import java.util.ArrayList;
import java.util.Iterator;

public class Strategy1 extends Player {

	public Strategy1(Table table, String name) {
		super(table, name);
	}

	@Override
	public boolean turn() {
		
		this.displayHand();
		
		if (!(optimalMoves == null) && !optimalMoves.isEmpty()) {
			executeMove();
		}
		
		else {
			
			runs = this.findRuns();
			sets = this.findSets();
			
			if (runs.isEmpty() && sets.isEmpty()) {
				return emptyMessage();
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
					return emptyMessage();
				}
				
				else {
					optimalMove();
				}
			}
			
			else {
				optimalMove();
			}
				
		}
		return true;
	}
	
	
	

}
