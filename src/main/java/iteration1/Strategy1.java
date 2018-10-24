package iteration1;

import java.util.Collections;

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
		
		return true;
	}

}
