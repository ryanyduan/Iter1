package iteration1;

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

		Rummikub.currentPlayer.Hand.clear();
		Rummikub.currentPlayer.Hand.add(B11);
		Rummikub.currentPlayer.Hand.add(G11);
		Rummikub.currentPlayer.Hand.add(O11);
	}
	
	Tile B11 = new Tile('B',11);
	Tile G11 = new Tile('G',11);
	Tile O11 = new Tile('O',11);
	
}
