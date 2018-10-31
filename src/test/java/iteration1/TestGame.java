package iteration1;

import java.util.Collections;

import junit.framework.TestCase;

public class TestGame extends TestCase {
	
	public void testInitialHand() {
		assertEquals(14, Rummikub.currentPlayer.Hand.size());
	}
	
	public void testSizeDeck() {
		assertEquals(104, Rummikub.table.Deck.size());
	}
	
	public void testGameEnd() {
		
		Rummikub.main(null);
		
		assertEquals(true, Rummikub.win);
	}
	
	public void testDeckEmpty() {
		Rummikub.main(null);
		
	}
	
	public void testGameEnd1() {
		Table table = new Table();
		Human human = new Human(table, "Human");
		Strategy1 s1 = new Strategy1(table, "Strategy1");
		Strategy2 s2 = new Strategy2(table, "Strategy2");
		Strategy3 s3 = new Strategy3(table, "Strategy3");
		
		human.Hand.clear();
		s1.Hand.clear();
		s2.Hand.clear();
		s3.Hand.clear();
		boolean over = false;
		if (human.checkTurn() && s1.checkTurn() && s2.checkTurn() && s3.checkTurn()) {
			over = true;
		}
		assertTrue(over);
		s1.is30 = true;
		over = false;
		s1.Hand.add(B11);
		s1.Hand.add(G11);
		s1.Hand.add(O11);
		Collections.sort(s1.Hand);
		
		if (human.checkTurn() && s1.checkTurn() && s2.checkTurn() && s3.checkTurn()) {
			over = true;
		}
		
		assertFalse(over);
	}
	
	Tile B11 = new Tile('B',11);
	Tile G11 = new Tile('G',11);
	Tile O11 = new Tile('O',11);
	
}
