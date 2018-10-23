package iteration1;

import junit.framework.TestCase;

public class TestGame extends TestCase {
	
	public void testInitialHand() {
		game.newGame();
		assertEquals(14, game.human.Hand.size());
	}
	
	public void testSizeDeck() {
		game.newGame();
		assertEquals(106, game.table.Deck.size());
	}
	
	Rummikub game = new Rummikub();

}
