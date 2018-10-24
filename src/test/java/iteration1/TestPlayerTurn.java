package iteration1;

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
		assertEquals(human.turnOptions.get(0), human.turnOptions.get(choice)); 
	}
	
	Table table = new Table();
	Tile B1 = new Tile('B',1);
	Tile B2 = new Tile('B',2);
	Tile B3 = new Tile('B',3);
	
	Tile R4 = new Tile('R',4);
	Tile G4 = new Tile('G',4);
	Tile O4 = new Tile('O',4);
	Tile B4 = new Tile('B',4);
	
	Tile G12 = new Tile('G',12);
	Tile O12 = new Tile('O',12);
	Tile R12 = new Tile('R',12);
}
