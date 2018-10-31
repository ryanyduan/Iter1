package iteration1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

public class Strategy2 extends Player {
	
	public Strategy2(Table table, String name) {
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
					if (table.s2Condition()) optimalMove();
					else {
						emptyMessage();
						return false;
					}
				}
			}
			
			else {
				
				//if Strategy2 can't play any singles on tiles already on the board, play everything it can
				if (!check) optimalMove();
				
				// else if Strategy2 can play singles, just play those
				else if (check) {
					optimalMoves = new ArrayList<ArrayList<Tile>>();
					tableTileIndex = 0;
					for (Iterator<Entry<Integer, ArrayList<Tile>>> it = possibleTiles.entrySet().iterator(); it.hasNext(); ) {
						Entry<Integer, ArrayList<Tile>> choice = it.next();
						if (!choice.getValue().isEmpty()) {
							tableTileIndex = choice.getKey();
							break;
						}
						tableTileIndex++;
					}
					
					optimalMoves.add(possibleTiles.get(tableTileIndex));
					executeMove();
					}
					
				else {
					emptyMessage();
					return false;
				}
			}
				
		}
		return true;
	}

	

}
