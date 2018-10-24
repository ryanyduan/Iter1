package iteration1;

import junit.framework.TestCase;

public class TestGame extends TestCase {
	
	public void testInitialHand() {
		assertEquals(14, Rummikub.currentPlayer.Hand.size());
	}
	
	public void testSizeDeck() {
		assertEquals(104, Rummikub.table.Deck.size());
	}
}
