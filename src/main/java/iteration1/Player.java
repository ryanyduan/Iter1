package iteration1;

import java.util.ArrayList;

public class Player extends Observer{
	
	private String playerType;
	public ArrayList<Tile> Hand;
	public ArrayList<ArrayList<Tile>> runs;
	private ArrayList<ArrayList<Tile>> sets;

	public Player(String type, Table table) {
		this.playerType = type;
		this.Hand = new ArrayList<Tile>();
		this.table = table;
	}


	
	public String displayHand(){
		String returnHand = "";
		for (Tile t: this.Hand) {
			returnHand += t.toString();
		}
		return returnHand;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
