package iteration1;

import junit.framework.TestCase;

public class TestStrategy1 extends TestCase {
	
	public void testStrat() {
		boolean win = false;
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
		s1.Hand.clear();
		s1.Hand.add(G11);
		s1.Hand.add(B11);
		s1.Hand.add(O11);
		
		assertTrue(s1.turn());
	}
	
	Table table = new Table();
	Player s1 = new Strategy1(table, "S1");
	
	Tile B1 = new Tile('B',1);
	Tile B2 = new Tile('B',2);
	Tile B3 = new Tile('B',3);
	
	Tile O11 = new Tile('O',11);
	Tile G11 = new Tile('G',11);
	Tile B11 = new Tile('B',11);

}
