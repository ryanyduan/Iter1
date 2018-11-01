package iteration1;

import java.util.ArrayList;
import java.util.Collections;

import junit.framework.TestCase;

public class TestPlayerTurn extends TestCase {

	
	public void testBasicTurn() {
		Table table = new Table();
		Player human = new Human(table, "Human");
		human.Hand.clear();
		human.Hand.add(B1);
		human.Hand.add(B2);
		human.Hand.add(B3);
		
		human.turn();
	}
	
	public void testBasicTurn2Melds() {
		Player human = new Human(table, "Human");
		human.Hand.clear();
		human.Hand.add(B1);
		human.Hand.add(B2);
		human.Hand.add(B3);
		human.Hand.add(R4);
		human.Hand.add(G4);
		human.Hand.add(O4);
		human.Hand.add(G12);
		human.Hand.add(B4);
		Collections.sort(human.Hand);
		
		human.turn();
		
		
	}
	
	public void testBasicTurn3Melds() {
		Player human = new Human(table, "Human");
		human.Hand.clear();
		human.Hand.add(B1);
		human.Hand.add(B2);
		human.Hand.add(B3);
		human.Hand.add(R4);
		human.Hand.add(G4);
		human.Hand.add(O4);
		human.Hand.add(G12);
		human.Hand.add(B4);
		human.Hand.add(R12);
		human.Hand.add(O12);
		
		Collections.sort(human.Hand);
		
		human.turn();
	}
	
	public void testPlayerUser() {
		Human human = new Human(table, "Human");
		human.Hand.clear();
		human.Hand.add(B1);
		human.Hand.add(B2);
		human.Hand.add(B3);
		human.Hand.add(R4);
		human.Hand.add(G4);
		human.Hand.add(O4);
		human.Hand.add(G12);
		human.Hand.add(B4);
		human.Hand.add(R12);
		human.Hand.add(O12);
		
		Collections.sort(human.Hand);
		human.turn();
		human.choice = 0;
		assertEquals(human.turnOptions.get(0), human.turnOptions.get(human.choice)); 
	}
	
	public void test30() {
		Human human = new Human(table, "Human");
		human.Hand.clear();
		human.Hand.add(B1);
		human.Hand.add(B2);
		human.Hand.add(B3);
		human.Hand.add(R4);
		human.Hand.add(G4);
		human.Hand.add(O4);
		human.Hand.add(G12);
		human.Hand.add(B4);
		human.Hand.add(R12);
		human.Hand.add(O12);
		Collections.sort(human.Hand);
		human.turn();
		
		ArrayList<Tile> thirty = new ArrayList<Tile>();
		thirty.add(R12);
		thirty.add(G12);
		thirty.add(O12);
		
		assertEquals(thirty, human.turnOptions.get(0));
	}
	
	public void testPlay30() {
		Human human = new Human(table, "Human");
		human.Hand.clear();
		human.Hand.add(B1);
		human.Hand.add(B2);
		human.Hand.add(B3);
		human.Hand.add(R4);
		human.Hand.add(G4);
		human.Hand.add(O4);
		human.Hand.add(G12);
		human.Hand.add(B4);
		human.Hand.add(R12);
		human.Hand.add(O12);
		Collections.sort(human.Hand);
		human.turn();
		
		ArrayList<ArrayList<Tile>> fakeBoard = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> humanTurn = new ArrayList<Tile>();
		humanTurn.add(R12);
		humanTurn.add(G12);
		humanTurn.add(O12);
		fakeBoard.add(humanTurn);
		
		assertEquals(table.Board, fakeBoard);
	}
	
	public void testTilesRemoved() {
		Human human = new Human(table, "Human");
		human.Hand.clear();
		human.Hand.add(B1);
		human.Hand.add(B2);
		human.Hand.add(B3);
		human.Hand.add(R4);
		human.Hand.add(G4);
		human.Hand.add(O4);
		human.Hand.add(G12);
		human.Hand.add(B4);
		human.Hand.add(R12);
		human.Hand.add(O12);
		Collections.sort(human.Hand);
		human.turn();
		
		ArrayList<ArrayList<Tile>> fakeBoard = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> humanTurn = new ArrayList<Tile>();
		humanTurn.add(R12);
		humanTurn.add(G12);
		humanTurn.add(O12);
		fakeBoard.add(humanTurn);
		
		assertEquals(false, human.Hand.contains(R12));
		
		
	}
	
	public void testTwoTurns() {
		Human human = new Human(table, "Human");
		human.Hand.clear();
		human.Hand.clear();
		human.Hand.add(B1);
		human.Hand.add(B2);
		human.Hand.add(B3);
		human.Hand.add(R4);
		human.Hand.add(G4);
		human.Hand.add(O4);
		human.Hand.add(G12);
		human.Hand.add(B4);
		human.Hand.add(R12);
		human.Hand.add(O12);
		Collections.sort(human.Hand);
		human.turn();
		
		ArrayList<ArrayList<Tile>> fakeBoard = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> humanTurn = new ArrayList<Tile>();
		humanTurn.add(R12);
		humanTurn.add(G12);
		humanTurn.add(O12);
		fakeBoard.add(humanTurn);
		
		assertEquals(table.Board, fakeBoard);
		
		ArrayList<Tile> humanTurn2 = new ArrayList<Tile>();
	
		humanTurn2.add(B1);
		humanTurn2.add(B2);
		humanTurn2.add(B3);
		humanTurn2.add(B4);
		fakeBoard.add(humanTurn2);
		
		
		human.turn();
		assertEquals(table.Board, fakeBoard);
	}
	
	public void testHasNextTurn() {
		Human human = new Human(table, "Human");
		human.Hand.clear();
		human.Hand.clear();
		human.Hand.add(B1);
		human.Hand.add(B2);
		human.Hand.add(B3);
		human.Hand.add(R4);
		human.Hand.add(G4);
		human.Hand.add(O4);
		human.Hand.add(G12);
		human.Hand.add(B4);
		human.Hand.add(R12);
		human.Hand.add(O12);
		Collections.sort(human.Hand);
		
		assertEquals(true, human.turn());
	}
	
	public void testNoNextTurn() {
		Human human = new Human(table,"Human");
		human.Hand.clear();
		human.Hand.add(G12);
		human.Hand.add(R12);
		human.Hand.add(O12);
		Collections.sort(human.Hand);
		assertTrue(human.turn());
		
		ArrayList<ArrayList<Tile>> fakeBoard = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> humanTurn = new ArrayList<Tile>();
		humanTurn.add(R12);
		humanTurn.add(G12);
		humanTurn.add(O12);
		fakeBoard.add(humanTurn);
		
		assertEquals(table.Board, fakeBoard);
		assertFalse(human.turn());
	}
	
	public void test30NEW() {
		Human human = new Human(table, "Human");
		human.Hand.clear();
		human.Hand.add(B5);
		human.Hand.add(B6);
		human.Hand.add(B7);
		human.Hand.add(B4);
		human.Hand.add(G4);
		human.Hand.add(O4);
		Collections.sort(human.Hand);
		human.turn();
		assertTrue(human.is30);
	}
	
	public void test30NEW_1_FAIL() {
		Human human = new Human(table, "Human");
		human.Hand.clear();
		human.Hand.add(B5);
		human.Hand.add(B6);
		human.Hand.add(B7);
		human.Hand.add(B1);
		human.Hand.add(G1);
		human.Hand.add(O1);
		Collections.sort(human.Hand);
		human.turn();
		assertTrue(human.is30);
	}
	
	Table table = new Table();
	Tile B1 = new Tile('B',1);
	Tile B2 = new Tile('B',2);
	Tile B3 = new Tile('B',3);
	Tile B5 = new Tile('B',5);
	Tile B6 = new Tile('B',6);
	Tile B7 = new Tile('B',7);
	Tile O1 = new Tile('O',1);
	Tile G1 = new Tile('G',1);
	
	Tile R4 = new Tile('R',4);
	Tile G4 = new Tile('G',4);
	Tile O4 = new Tile('O',4);
	Tile B4 = new Tile('B',4);
	
	Tile G12 = new Tile('G',12);
	Tile O12 = new Tile('O',12);
	Tile R12 = new Tile('R',12);
}
