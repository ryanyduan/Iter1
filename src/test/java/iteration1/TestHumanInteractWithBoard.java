package iteration1;

import java.util.ArrayList;
import java.util.Collections;

import junit.framework.TestCase;

public class TestHumanInteractWithBoard extends TestCase {
	
	Table table = new Table();
	Human human = new Human(table, "Human");
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
	Tile G2 = new Tile('G',2);
	Tile O2 = new Tile('O',2);
	Tile R2 = new Tile('R',2);
	
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
	
	public void test1SetOnBoard() {
		ArrayList<Tile> fakeMove = new ArrayList<Tile>();
		ArrayList<Tile> possibleTiles = new ArrayList<Tile>();
		human.is30 = true;
		fakeMove.add(B2);
		fakeMove.add(B3);
		fakeMove.add(B4);
		possibleTiles.add(B1);
		possibleTiles.add(B5);
		
		table.Board.add(fakeMove);
		human.turn();
		assertEquals(possibleTiles, human.possibleTiles);
	}
	
	public void test2SetsonBoard() {
		ArrayList<Tile> fakeMove = new ArrayList<Tile>();
		ArrayList<Tile> fakeMove1 = new ArrayList<Tile>();
		ArrayList<Tile> possibleTiles = new ArrayList<Tile>();
		human.is30 = true;
		fakeMove.add(B2);
		fakeMove.add(B3);
		fakeMove.add(B4);
		fakeMove1.add(R3);
		fakeMove1.add(R4);
		fakeMove1.add(R5);
		human.Hand.add(B1);
		Collections.sort(human.Hand);
		
		possibleTiles.add(B1);
		possibleTiles.add(B5);
		
		table.Board.add(fakeMove);
		table.Board.add(fakeMove1);
		ArrayList<Tile> oldMove = new ArrayList<Tile>(table.Board.get(0));
		oldMove.add(B5);
		human.turn();
		assertEquals(table.Board.get(0), oldMove);
	}
	
	public void testSetOnBoard() {
		human.is30 = true;
		ArrayList<Tile> fakeMove = new ArrayList<Tile>();
		ArrayList<Tile> possibleTiles = new ArrayList<Tile>();
		fakeMove.add(B2);
		fakeMove.add(G2);
		fakeMove.add(O2);
		human.Hand.clear();
		human.Hand.add(R2);
		Collections.sort(human.Hand);
		
		possibleTiles.add(R2);
		
		table.Board.add(fakeMove);
		ArrayList<Tile> oldMove = new ArrayList<Tile>(table.Board.get(0));
		oldMove.add(R2);
		human.turn();
		assertEquals(table.Board.get(0), oldMove);
	}
	
	public void testTwoSEtsOnBoard() {
		human.is30 = true;
		ArrayList<Tile> fakeMove = new ArrayList<Tile>();
		ArrayList<Tile> fakeMove1 = new ArrayList<Tile>();
		ArrayList<Tile> possibleTiles = new ArrayList<Tile>();
		fakeMove.add(B2);
		fakeMove.add(G2);
		fakeMove.add(O2);
		human.Hand.clear();
		human.Hand.add(R2);
		Collections.sort(human.Hand);
		
		//possibleTiles.add(R2);
		
		table.Board.add(fakeMove);
		ArrayList<Tile> oldMove = new ArrayList<Tile>(table.Board.get(0));
		oldMove.add(R2);
		
		human.Hand.add(G4);
		fakeMove1.add(B4);
		fakeMove1.add(O4);
		fakeMove1.add(R4);
		table.Board.add(fakeMove1);
		ArrayList<Tile> oldMove2 = new ArrayList<Tile>(table.Board.get(1));
		oldMove2.add(G4);
		human.turn();
		assertEquals(table.Board.get(0), oldMove);
		human.turn();
		assertEquals(table.Board.get(1), oldMove2);	
	}

}
