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
		assertEquals("{B1 B2 B3}", table.displayBoard());
	}
	
	Tile B1 = new Tile('B',1);
	Tile B2 = new Tile('B',2);
	Tile B3 = new Tile('B',3);
}
