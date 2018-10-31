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
		assertEquals("Board: {[B1 , B2 , B3 ]}", table.displayBoard());
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
		
		assertEquals("Board: {[B1 , B2 , B3 ]}{[B1 , B2 , B3 ]}", table.displayBoard());
		
	}
	
	public void testDisplay1MeldWithStars() {
		Table table = new Table();
		newMeld.add(B1);
		B1.isJustPlayed();
		newMeld.add(B2);
		B2.isJustPlayed();
		newMeld.add(B3);
		B3.isJustPlayed();
		table.Board.add(newMeld);
		assertEquals("Board: {[*B1 , *B2 , *B3 ]}", table.displayBoard());
	}
	
	public void testDisplay2MeldsWithStars() {
		Table table = new Table();
		newMeld.add(B1);
		B1.isJustPlayed();
		newMeld.add(B2);
		B2.isJustPlayed();
		newMeld.add(B3);
		B3.isJustPlayed();
		table.Board.add(newMeld);
		assertEquals("Board: {[*B1 , *B2 , *B3 ]}", table.displayBoard());
		
		newMeld2.add(G4);
		newMeld2.add(O4);
		newMeld2.add(R4);
		table.Board.add(newMeld2);
		for (Tile tile: newMeld) {
			tile.justPlayed = false;
		}
		for (Tile tile: newMeld2) {
			tile.isJustPlayed();
		}
		assertEquals("Board: {[B1 , B2 , B3 ]}{[*G4 , *O4 , *R4 ]}", table.displayBoard());
	}
	
	ArrayList<Tile> newMeld = new ArrayList<Tile>();
	ArrayList<Tile> newMeld2 = new ArrayList<Tile>();
	Tile B1 = new Tile('B',1);
	Tile B2 = new Tile('B',2);
	Tile B3 = new Tile('B',3);
	Tile O4 = new Tile('O',4);
	Tile G4 = new Tile('G',4);
	Tile R4 = new Tile('R',4);
}
