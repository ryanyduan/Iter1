package iteration1;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import junit.framework.TestCase;

public class TileTest extends TestCase {

	public void testCompare2Tiles() {
		Player player = new Player();
		Tile B_one = new Tile('B', 1);
		Tile B_two = new Tile('B',2);
		player.Hand.add(B_two);
		player.Hand.add(B_one);
		Collections.shuffle(player.Hand);
		
		ArrayList<Tile> confirmationArray = new ArrayList<Tile>();
		confirmationArray.add(B_two);
		confirmationArray.add(B_one);
		assertEquals(confirmationArray, player.Hand);
	}

}
