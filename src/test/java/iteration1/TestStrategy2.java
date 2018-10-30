package iteration1;

import junit.framework.TestCase;

public class TestStrategy2 extends TestCase {

	public void testStrategy30() {
		
		s1.Hand.clear();
		s3.turn();
		assertTrue(s3.condition);
		
	}
	
	
	Table table = new Table();
	Strategy1 s1 = new Stragegy1(table, "strategy1"); 
	Strategy2 s2 = new Strategy2(table, "Strategy 2");
}
