package iteration1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Human extends Player {
	
	public HashMap<Integer, ArrayList<Tile>> turnOptions;
	public int choice = 0;

	public Human(Table table, String name) {
		super(table, name);
	}

	@Override
	public void turn() {
		runs = this.findRuns();
		sets = this.findSets();
		
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
		
		turnOptions = new HashMap<Integer, ArrayList<Tile>>();
		
		int counter = 0;
		for (ArrayList<Tile> run: runs) {
			turnOptions.put(counter, run);
			counter++;
		}
		
		for (ArrayList<Tile> set: sets) {
			turnOptions.put(counter, set);
			counter++;
		}
		
		if (!turnOptions.isEmpty()) {
			System.out.println("Here are your options of melds to play");
			printMap(turnOptions);
			System.out.println("Choose the number corresponding to the tiles you want to play.");
			Scanner scan = new Scanner(System.in);
			choice = scan.nextInt();
			while (!turnOptions.containsKey(choice)) {
				System.out.println(turnOptions.keySet());
				System.out.println("CHOICE" + choice);
				System.out.println("Choose the number corresponding to the tiles you want to play.");
				choice = scan.nextInt();
			}
			
			table.Board.add(turnOptions.remove(choice));
		}
		
		else {
			System.out.println("You draw a card since there are no tiles to play.");
			this.draw();
		}
	}
	
	
	public int value(ArrayList<Tile> meld) {
		int value = 0;
		for (Tile t: meld) {
			value += t.getRank();
		}
		return value;
	}
	
	public void printMap(HashMap map) {
	    Iterator it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        HashMap.Entry pair = (HashMap.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	    }
	}
	

}
