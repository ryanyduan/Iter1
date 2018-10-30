package iteration1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

public class Strategy2 extends Player {
	
	public boolean condition = false;

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
			
			if (this.table.getState() <= this.Hand.size()-3) {
				condition=true;
			}
			else condition = false;
			
			if (runs.isEmpty() && sets.isEmpty() && possibleTiles.isEmpty()) {
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
				
				else {
					if (condition && table.s2Condition()) optimalMove();
					else {
						emptyMessage();
						over = true;
						return false;
					}
				}
			}
			
			else {
				if (condition) optimalMove();
				
				//this else if is the condition that strategy2 should only play with existing tiles on the board
//				else if (!possibleTiles.isEmpty()) {
//					
//					optimalMoves = new ArrayList<ArrayList<Tile>>();
//					for (Iterator<Entry<Integer, ArrayList<Tile>>> it = possibleTiles.entrySet().iterator(); it.hasNext(); ) {
//						Entry<Integer, ArrayList<Tile>> choice = it.next();
//						ArrayList<Tile> valueCopy = new ArrayList<Tile>(choice.getValue());
//						for (Tile t: valueCopy) {
//							boolean in = false;
//							for (Tile handTile: this.Hand) {
//								if (handTile.getRank() == t.getRank() && handTile.getColour() == t.getColour()){
//									in = true;
//									possibleTiles.get(choice.getKey()).remove(t);
//									possibleTiles.get(choice.getKey()).add(handTile);
//									break;
//								}
//							}
//							if (!in) {
//								possibleTiles.get(choice.getKey()).remove(t);
//							}
//						}
//					}
//					
//					if (!possibleTiles.isEmpty()) {
//			
//						tableTileIndex = (int) possibleTiles.keySet().toArray()[0];
//						optimalMoves.clear();
//						optimalMoves.add(possibleTiles.get(0));
//						executeMove();
//					}
//					
//					//this is if possibleTiles from subject don't match any tiles in your hand
//					else {
//						emptyMessage();
//						over = true;
//						return false;
//					}
//					
//				}
				else {
					emptyMessage();
					over = true;
					return false;
				}
			}
				
		}
		return true;
	}

	

}
