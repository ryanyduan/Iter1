package iteration1;

import java.util.ArrayList;
import java.util.Iterator;

public class Strategy3 extends Player {
	
	public boolean condition = false;

	public Strategy3(Table table, String name) {
		super(table, name);
	}

	@Override
	public boolean turn() {
		
		if (!(optimalMoves == null) && !optimalMoves.isEmpty()) {
			executeMove();
		}
		
		else {
			
			runs = this.findRuns();
			sets = this.findSets();
			
			if (this.table.getState() <= this.Hand.size()-3) {
				condition=true;
			}
			else condition = false;
			
			if (runs.isEmpty() && sets.isEmpty()) {
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
				
				if (runs.isEmpty() && sets.isEmpty()) {
					emptyMessage();
					return false;
				}
				
				else {
					if (condition) optimalMove();
					else emptyMessage();
				}
			}
			
			else {
				if (condition) optimalMove();
				else emptyMessage();
			}
				
		}
		return true;
	}

}
