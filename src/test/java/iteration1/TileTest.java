package iteration1;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import junit.framework.TestCase;

public class TileTest extends TestCase {

	public void testCompare2Tiles() {
		Table test = new Table();
		Player player = new Player("Human", test,"Human");
		Tile B_one = new Tile('B', 1);
		Tile B_two = new Tile('B',2);
		player.Hand.add(B_two);
		player.Hand.add(B_one);
		Collections.sort(player.Hand);
		
		ArrayList<Tile> confirmationArray = new ArrayList<Tile>();
		confirmationArray.add(B_one);
		confirmationArray.add(B_two);
		assertEquals(confirmationArray, player.Hand);
	}
	
	public void testCompare3Tiles() {
		Table test = new Table();
		Player player = new Player("Human", test,"Human");
		Tile B_one = new Tile('B', 1);
		Tile B_two = new Tile('B',2);
		Tile O_one = new Tile('O', 1);
		player.Hand.add(B_two);
		player.Hand.add(O_one);
		player.Hand.add(B_one);
		Collections.sort(player.Hand);
		
		ArrayList<Tile> confirmationArray = new ArrayList<Tile>();
		confirmationArray.add(B_one);
		confirmationArray.add(B_two);
		confirmationArray.add(O_one);
		assertEquals(confirmationArray, player.Hand);
		
	}
	
	public void test8Ones() {
		Table test = new Table();
		Player player = new Player("Human", test,"Human");
		Tile B_one_one = new Tile('B',1);
		Tile B_one_two = new Tile('B',1);
		Tile G_one_one = new Tile('G', 1);
		Tile G_one_two = new Tile('G', 1);
		Tile R_one_one = new Tile('R', 1);
		Tile R_one_two = new Tile('R', 1);
		Tile O_one_one = new Tile('O', 1);
		Tile O_one_two = new Tile('O', 1);
		player.Hand.add(B_one_two);
		player.Hand.add(O_one_one);
		player.Hand.add(B_one_one);
		player.Hand.add(G_one_two);
		player.Hand.add(R_one_one);
		player.Hand.add(O_one_two);
		player.Hand.add(R_one_two);
		player.Hand.add(G_one_one);
		Collections.sort(player.Hand);
		
		ArrayList<Tile> confirmationArray = new ArrayList<Tile>();
		confirmationArray.add(B_one_one);
		confirmationArray.add(B_one_two);
		confirmationArray.add(G_one_one);
		confirmationArray.add(G_one_two);
		confirmationArray.add(O_one_one);
		confirmationArray.add(O_one_two);
		confirmationArray.add(R_one_one);
		confirmationArray.add(R_one_two);
		
		assertEquals(confirmationArray, player.Hand);
	}
	
	public void testPrintHand() {
		Table table = new Table();
		Player player = new Player("Human", table,"Human");
		Tile B_one_one = new Tile('B',1);
		Tile B_one_two = new Tile('B',1);
		Tile G_one_one = new Tile('G', 1);
		Tile G_one_two = new Tile('G', 1);
		Tile R_one_one = new Tile('R', 1);
		Tile R_one_two = new Tile('R', 1);
		Tile O_one_one = new Tile('O', 1);
		Tile O_one_two = new Tile('O', 1);
		player.Hand.add(B_one_two);
		player.Hand.add(O_one_one);
		player.Hand.add(B_one_one);
		player.Hand.add(G_one_two);
		player.Hand.add(R_one_one);
		player.Hand.add(O_one_two);
		player.Hand.add(R_one_two);
		player.Hand.add(G_one_one);
		Collections.sort(player.Hand);
		
		assertEquals("B1B1G1G1O1O1R1R1", player.displayHand());
		
	}

}
