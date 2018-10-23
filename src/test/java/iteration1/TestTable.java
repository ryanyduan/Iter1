package iteration1;

import java.util.ArrayList;

import junit.framework.TestCase;

public class TestTable extends TestCase {
	
	public void testDisplayTable() {
		Table table = new Table();
		ArrayList<Tile> newMeld = new ArrayList<Tile>();
		newMeld.add(B1);
		newMeld.add(B2);
		newMeld.add(B3);
		table.Board.add(newMeld);
		assertEquals("{[B1 , B2 , B3 ]}", table.displayBoard());
	}
	
	public void testDisplayTable2Melds() {
		Table table = new Table();
		newMeld.add(B1);
		newMeld.add(B2);
		newMeld.add(B3);
		table.Board.add(newMeld);
		newMeld2.add(B1);
		newMeld2.add(B2);
		newMeld2.add(B3);
		table.Board.add(newMeld2);
		
		assertEquals("{[B1 , B2 , B3 ]}{[B1 , B2 , B3 ]}", table.displayBoard());
		
	}
	
	ArrayList<Tile> newMeld = new ArrayList<Tile>();
	ArrayList<Tile> newMeld2 = new ArrayList<Tile>();
	Tile B1 = new Tile('B',1);
	Tile B2 = new Tile('B',2);
	Tile B3 = new Tile('B',3);
}
