package iteration1;

import junit.framework.TestCase;

public class TestStrategy3 extends TestCase {
	
	public void testStrat() {
		Player s3 = new Strategy3(table, "S3");
		s3.Hand.clear();
		assertFalse(s3.turn());
	}
	
	Table table = new Table();
	
	public void testStrategy() {
		Strategy3 s3 = new Strategy3(table, "S3");
		Player s1 = new Strategy1(table, "S1");
		s1.Hand.clear();
		s3.turn();
		assertTrue(s3.condition);
	}
}	
