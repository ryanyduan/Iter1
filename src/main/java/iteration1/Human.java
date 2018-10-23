package iteration1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Human extends Player {
	
	public HashMap turnOptions;

	public Human(Table table, String name) {
		super(table, name);
	}

	@Override
	public void turn() {
		runs = this.findRuns();
		sets = this.findSets();
		turnOptions = new HashMap<String, ArrayList<Tile>>();
		
		System.out.println(runs.toString());
		int counter = 0;
		for (ArrayList<Tile> run: runs) {
			turnOptions.put(counter, run);
			counter++;
		}
		for (ArrayList<Tile> set: sets) {
			turnOptions.put(counter, set);
			counter++;
		}
		
		System.out.println("Here are your options of melds to play");
		printMap(turnOptions);
		
		
		
		
	}
	
	public static void printMap(HashMap map) {
	    Iterator it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        HashMap.Entry pair = (HashMap.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}
	

}
