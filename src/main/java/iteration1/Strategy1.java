package iteration1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Strategy1 extends Player {

	public Strategy1(Table table, String name) {
		super(table, name);
	}

	@Override
	public boolean turn() {
		
		runs = this.findRuns();
		sets = this.findSets();
		
		if (runs.isEmpty() && sets.isEmpty()) {
			System.out.println(this.getName() + " draws a card since there are no tiles to play.");
			if (this.table.Deck.isEmpty()) {
				System.out.println("Deck is empty. No card was drawn.");
			}
			else {
				this.draw();
			}
			this.displayHand();
			Collections.sort(this.Hand);
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
		}
		
		if (runs.isEmpty() && sets.isEmpty()) {
			return false;
		}
		
		return true;
	}

}
