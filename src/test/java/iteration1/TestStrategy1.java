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
		s1.Hand.clear();
		s1.Hand.add(B1);
		s1.Hand.add(B2);
		s1.Hand.add(B3);
		assertFalse(s1.turn());	
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
		s1.findRuns();
		s1.findSets();
		
		fakeOptimalMove.add(B3);
		fakeOptimalMove.add(G3);
		fakeOptimalMove.add(O3);
		s1.optimalMove();
		assertEquals(6, s1.possibleSetsLength);
		
	}
	
	Table table = new Table();
	Player s1 = new Strategy1(table, "S1");
	
	Tile B1 = new Tile('B',1);
	Tile B2 = new Tile('B',2);
	Tile B3 = new Tile('B',3);
	Tile B4 = new Tile('B',4);
	Tile B5 = new Tile('B',5);
	
	Tile G3 = new Tile('G',3);
	Tile O3 = new Tile('O',3);
	Tile G4 = new Tile('G',4);
	Tile O4 = new Tile('O',4);
	
	
	Tile O11 = new Tile('O',11);
	Tile G11 = new Tile('G',11);
	Tile B11 = new Tile('B',11);

}
