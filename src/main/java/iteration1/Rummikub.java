package iteration1;

public class Rummikub {
	
	Table table;
	Player human;
	
	private boolean win = false;
	
	public int newGame() {
		
		table = new Table();
		human = new Player("Human", table, "Human");
		human.displayHand();
		
		return -1;
	}
	
	
}
