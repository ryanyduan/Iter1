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
		s3 = new Strategy3(table, "S3");
		Player s1 = new Strategy1(table, "S1");
		s1.Hand.clear();
		s3.turn();
		assertTrue(s3.condition);
		
		s3.Hand.clear();
		s1.Hand.add(B1);
		s3.Hand.add(B1);
		s3.Hand.add(B2);
		s3.turn();
		assertFalse(s3.condition);
	}
	
	Strategy3 s3 = new Strategy3(table, "S3");
	
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
}	
