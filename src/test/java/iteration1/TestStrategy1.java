package iteration1;

import java.util.ArrayList;
import java.util.Collections;

import junit.framework.TestCase;

public class TestStrategy1 extends TestCase {
	
	public void testStrat() {
		Player s1 = new Strategy1(table, "S1");
		s1.Hand.clear();
		assertFalse(s1.turn());
	}
	
	public void testStrat1No30() {
		
		ArrayList<Tile> fakeMove = new ArrayList<Tile>();
		s1.Hand.clear();
		s1.Hand.add(B1);
		s1.Hand.add(B2);
		s1.Hand.add(B3);
		assertFalse(s1.turn());	
		
		s1.Hand.add(B11);
		s1.Hand.add(G11);
		s1.Hand.add(O11);
		
		fakeMove.add(B11);
		fakeMove.add(G11);
		fakeMove.add(O11);
		Collections.sort(s1.Hand);
		s1.turn();
		assertEquals(fakeMove, s1.table.Board.get(0));
		}
	
	public void testStrat1Has30() {
		
		ArrayList<ArrayList<Tile>> fakeBoard = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> fakeMove = new ArrayList<Tile>();
		s1.Hand.clear();
		s1.Hand.add(B11);
		s1.Hand.add(G11);
		s1.Hand.add(O11);
		fakeMove.add(B11);
		fakeMove.add(G11);
		fakeMove.add(O11);
		fakeBoard.add(fakeMove);
		assertTrue(s1.turn());
		assertEquals(table.Board, fakeBoard);
	}
	
	public void testOptimalMove() {
		
		ArrayList<Tile> fakeOptimalMove = new ArrayList<Tile>();
		
		s1.Hand.clear();
		s1.is30 = true;
		s1.Hand.add(B1);
		s1.Hand.add(B2);
		s1.Hand.add(B3);
		s1.Hand.add(B4);
		s1.Hand.add(B5);
		s1.Hand.add(G3);
		s1.Hand.add(O3);
		s1.Hand.add(G4);
		s1.Hand.add(O4);
		Collections.sort(s1.Hand);
		
		fakeOptimalMove.add(B3);
		fakeOptimalMove.add(G3);
		fakeOptimalMove.add(O3);
		s1.turn();
		assertEquals(6, s1.possibleSetsLength);
		assertEquals(fakeOptimalMove, table.Board.get(0));
		fakeOptimalMove.clear();
		fakeOptimalMove.add(B4);
		fakeOptimalMove.add(G4);
		fakeOptimalMove.add(O4);
		s1.turn();
		assertEquals(fakeOptimalMove, table.Board.get(1));
	}
	
	public void testOptimalMoveWithoutFixing30() {
		ArrayList<Tile> fakeOptimalMove = new ArrayList<Tile>();
		
		s1.Hand.clear();
		s1.Hand.add(B1);
		s1.Hand.add(B2);
		s1.Hand.add(B3);
		s1.Hand.add(B4);
		s1.Hand.add(B5);
		s1.Hand.add(G3);
		s1.Hand.add(O3);
		s1.Hand.add(G4);
		s1.Hand.add(O4);
		s1.Hand.add(B11);
		s1.Hand.add(B9);
		s1.Hand.add(B10);
		
		Collections.sort(s1.Hand);
		
		fakeOptimalMove.add(B9);
		fakeOptimalMove.add(B10);
		fakeOptimalMove.add(B11);
		s1.turn();
		assertEquals(fakeOptimalMove, table.Board.get(0));
		fakeOptimalMove.clear();
		fakeOptimalMove.add(B3);
		fakeOptimalMove.add(G3);
		fakeOptimalMove.add(O3);
		s1.turn();
		assertEquals(fakeOptimalMove, table.Board.get(1));
		fakeOptimalMove.clear();
		fakeOptimalMove.add(B4);
		fakeOptimalMove.add(G4);
		fakeOptimalMove.add(O4);
		s1.turn();
		assertEquals(fakeOptimalMove, table.Board.get(2));
	}
	
	public void testOptimalMoveIsSet() {
		
		ArrayList<Tile> fakeOptimalMove = new ArrayList<Tile>();
		s1.Hand.clear();
		table.Board.clear();
		s1.is30 = true;
		s1.Hand.add(B1);
		s1.Hand.add(B2);
		s1.Hand.add(B3);
		s1.Hand.add(R5);
		s1.Hand.add(B5);
		s1.Hand.add(G5);
		s1.Hand.add(O5);
		Collections.sort(s1.Hand);
		
		fakeOptimalMove.add(R5);
		fakeOptimalMove.add(B5);
		fakeOptimalMove.add(G5);
		fakeOptimalMove.add(O5);
		
		s1.turn();
		assertEquals(fakeOptimalMove, table.Board.get(0));
	}
	
	public void testHardSet() {
		ArrayList<Tile> fakeOptimalMove = new ArrayList<Tile>();
		s1.Hand.clear();
		s1.is30 = true;
		s1.Hand.add(B1);
		s1.Hand.add(B2);
		s1.Hand.add(B3);
		s1.Hand.add(R4);
		s1.Hand.add(G4);
		s1.Hand.add(B4);
		s1.Hand.add(B5);
		s1.Hand.add(B6);
		s1.Hand.add(B7);
		//123 444 456
		//optimal play should be this ^
		Collections.sort(s1.Hand);
		s1.displayHand();
		s1.turn();
		fakeOptimalMove.add(B1);
		fakeOptimalMove.add(B2);
		fakeOptimalMove.add(B3);
		
		assertEquals(fakeOptimalMove, table.Board.get(0));
		
		fakeOptimalMove.clear();
		
		fakeOptimalMove.add(B5);
		fakeOptimalMove.add(B6);
		fakeOptimalMove.add(B7);
		
		s1.turn();
		assertEquals(fakeOptimalMove, table.Board.get(1));
		
		fakeOptimalMove.clear();
		fakeOptimalMove.add(R4);
		fakeOptimalMove.add(B4);
		fakeOptimalMove.add(G4);
		s1.turn();
		assertEquals(fakeOptimalMove, table.Board.get(2));
		
		
	}
	
	public void testWeirdRun() {
		ArrayList<Tile> fakeOptimalMove = new ArrayList<Tile>();
		s1.Hand.clear();
		s1.is30 = true;
		
		s1.Hand.add(B1);
		s1.Hand.add(B2);
		s1.Hand.add(B3);
		s1.Hand.add(B4);
		s1.Hand.add(B42);
		s1.Hand.add(B5);
		s1.Hand.add(B6);
		
		fakeOptimalMove.add(B1);
		fakeOptimalMove.add(B2);
		fakeOptimalMove.add(B3);
		fakeOptimalMove.add(B4);
		fakeOptimalMove.add(B5);
		fakeOptimalMove.add(B6);
		
		s1.turn();
		assertEquals(fakeOptimalMove, table.Board.get(0));
	}
	
	public void testInteractionWithBoard1Run() {
		s1.Hand.clear();
		s1.Hand.add(B1);
		ArrayList<Tile> fakeMove = new ArrayList<Tile>();
		ArrayList<Tile> possibleMoves = new ArrayList<Tile>();
		fakeMove.add(B2);
		fakeMove.add(B3);
		fakeMove.add(B4);
		table.Board.clear();
		table.Board.add(fakeMove);
		
		possibleMoves.add(B1);
		
		s1.turn();
		assertEquals(table.Board.get(0).get(0), possibleMoves.get(0));
	}
	
	Table table = new Table();
	Player s1 = new Strategy1(table, "S1");
	
	Tile B1 = new Tile('B',1);
	Tile B2 = new Tile('B',2);
	Tile B3 = new Tile('B',3);
	Tile R4 = new Tile('R',4);
	Tile B4 = new Tile('B',4);
	Tile B5 = new Tile('B',5);
	Tile B6 = new Tile('B',6);
	Tile R3 = new Tile('R',3);
	Tile R5 = new Tile('R',5);
	Tile G5 = new Tile('G',5);
	Tile O5 = new Tile('O',5);
	
	Tile B42 = new Tile('B',4);
	Tile B7 = new Tile('B',7);
	
	
	Tile G3 = new Tile('G',3);
	Tile O3 = new Tile('O',3);
	Tile G4 = new Tile('G',4);
	Tile O4 = new Tile('O',4);
	
	
	Tile O11 = new Tile('O',11);
	Tile G11 = new Tile('G',11);
	Tile B11 = new Tile('B',11);
	Tile B10 = new Tile('B',10);
	Tile B9 = new Tile('B',9);

}
