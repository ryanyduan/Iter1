package iteration1;

import junit.framework.TestCase;

public class TestGame extends TestCase {
	
	public void testInitialHand() {
		Rummikub game = new Rummikub();
		game.newGame();
		assertEquals(14, game.human.Hand.size());
	}

}
