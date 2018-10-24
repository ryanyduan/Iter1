package iteration1;

import junit.framework.TestCase;

public class TestStrategy1 extends TestCase {
	
	public void testStrat() {
		boolean win = false;
		Player s1 = new Strategy1(table, "S1");
		s1.Hand.clear();
		assertFalse(s1.turn());
		
	}
	
	Table table = new Table();
	

}
