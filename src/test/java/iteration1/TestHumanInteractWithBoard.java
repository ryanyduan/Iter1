package iteration1;

import java.util.ArrayList;

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

}
