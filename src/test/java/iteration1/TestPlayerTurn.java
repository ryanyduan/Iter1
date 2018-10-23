package iteration1;

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
	
	Tile B1 = new Tile('B',1);
	Tile B2 = new Tile('B',2);
	Tile B3 = new Tile('B',3);
}
