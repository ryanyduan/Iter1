package iteration1;

import java.util.ArrayList;

import junit.framework.TestCase;

public class TestStrategy2 extends TestCase {

	public void testStrategy30() {
		
		s1.Hand.clear();
		s1.is30 = true;
		s2.turn();
		assertTrue(s2.condition);
		s1.is30 = false;
		s2.Hand.clear();
		s2.Hand.add(B11);
		s2.Hand.add(G11);
		s2.Hand.add(O11);
		s2.turn();
		s1.is30 = true;
		s2.turn();	
	}
	
	public void testPlay1Tile() {
		s1.is30 = true;
		s2.is30 = true;
		s2.Hand.clear();
		s2.Hand.add(B11);
		s2.Hand.add(G11);
		s2.Hand.add(O11);
		s2.Hand.add(G4);
		
		ArrayList<Tile> fakeMove = new ArrayList<Tile>();
		fakeMove.add(G1);
		fakeMove.add(G2);
		fakeMove.add(G3);
		
		table.Board.add(fakeMove);
		
		s2.turn();
		ArrayList<Tile> newBoard = new ArrayList<Tile>();
		newBoard.add(G1);
		newBoard.add(G2);
		newBoard.add(G3);
		newBoard.add(G4);
		
		assertEquals(table.Board.get(0), newBoard);
		
	}
	
	
	Table table = new Table();
	Strategy1 s1 = new Strategy1(table, "strategy1"); 
	Strategy2 s2 = new Strategy2(table, "Strategy 2");
	Tile B11 = new Tile('B',11);
	Tile G11 = new Tile('G',11);
	Tile O11 = new Tile('O',11);
	Tile G1 = new Tile('G',1);
	Tile G2 = new Tile('G',2);
	Tile G3 = new Tile('G',3);
	Tile G4 = new Tile('G',4);
}
