package iteration1;

import java.util.ArrayList;

import junit.framework.TestCase;

public class TestSetandRun extends TestCase {
	
	public void testRun() {
		//this is testing a method that checks that if you remove a tile from a run, it still remains a run
		Player player = new Player("Human",test);
		ArrayList<Tile> run = new ArrayList<Tile>();
		run.add(B1);
		run.add(B3);
		player.runs.add(run);
		assertFalse(player.isRun(player.runs.get(0)));
		
	}
	
	Table test = new Table();
	Tile B1 = new Tile('B',1);
	Tile B2 = new Tile('B',2);
	Tile B3 = new Tile('B',3);
	Tile B4 = new Tile('B',4);
	Tile B5 = new Tile('B',5);
	
	
	
}
