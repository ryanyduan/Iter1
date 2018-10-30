package iteration1;

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
	
	
	Table table = new Table();
	Strategy1 s1 = new Strategy1(table, "strategy1"); 
	Strategy2 s2 = new Strategy2(table, "Strategy 2");
	Tile B11 = new Tile('B',11);
	Tile G11 = new Tile('G',11);
	Tile O11 = new Tile('O',11);
}
