package iteration1;

import java.util.ArrayList;
import java.util.Collections;

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
		
		ArrayList<Tile> nextMove = new ArrayList<Tile>();
		nextMove.add(B11);
		nextMove.add(G11);
		nextMove.add(O11);
		s1.Hand.clear();
		
		assertEquals(table.Board.get(0), newBoard);
		
		s2.turn();
		assertEquals(table.Board.get(1), nextMove);
		
	}
	
	public void testHarderTest() {
		s2.Hand.clear();
		s2.Hand.add(G4);
		s2.Hand.add(B11);
		s2.Hand.add(G11);
		s2.Hand.add(O11);
		
		ArrayList<Tile> newBoard = new ArrayList<Tile>();
		newBoard.add(G1);
		newBoard.add(G2);
		newBoard.add(G3);
		
		table.Board.add(newBoard);
		ArrayList<Tile> fakeMove = new ArrayList<Tile>();
		fakeMove.add(B11);
		fakeMove.add(G11);
		fakeMove.add(O11);
		s2.turn();
		assertNotSame(table.Board.get(0),fakeMove );
		s1.is30 = true;
		s2.turn();
		assertEquals(table.Board.get(1),fakeMove );
		
		s2.Hand.add(O7);
		s2.Hand.add(G7);
		s2.Hand.add(R7);
		s2.Hand.add(R11);
		s2.Hand.add(R12);
		s2.Hand.add(R13);
		Collections.sort(s2.Hand);
		s2.turn();
		ArrayList<Tile> fakeMove2 = new ArrayList<Tile>();
		fakeMove2.add(G1);
		fakeMove2.add(G2);
		fakeMove2.add(G3);
		fakeMove2.add(G4);
		assertEquals(table.Board.get(0), fakeMove2);
		
		s2.Hand.add(R2);
		Collections.sort(s2.Hand);
		
		ArrayList<Tile> fakeMove3 = new ArrayList<Tile>();
		fakeMove3.add(B11);
		fakeMove3.add(G11);
		fakeMove3.add(O11);
		fakeMove3.add(R11);
		s2.turn();
		assertEquals(table.Board.get(1), fakeMove3);
		
		ArrayList<Tile> fakeMove4 = new ArrayList<Tile>();
		fakeMove4.add(G1);
		fakeMove4.add(G2);
		fakeMove4.add(G3);
		fakeMove4.add(G4);
		s2.turn();
		assertEquals(table.Board.get(0), fakeMove4);
		
		
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
	Tile R2 = new Tile('R',2);
	Tile O7 = new Tile('O',7);
	Tile G7 = new Tile('G',7);
	Tile R7 = new Tile('R',7);
	Tile R11 = new Tile('R',11);
	Tile R12 = new Tile('R',12);
	Tile R13 = new Tile('R',13);
}
