package iteration1;

public class Rummikub {
	
	Table table;
	Player human;
	Player[] players;
	Player currentPlayer;
	
	private boolean win = false;
	
	public int newGame() {
		
		table = new Table();
		players = new Player[4];
		human = new Human("Human", table, "Human");
		players[0] = human;
		human.displayHand();
		
//		while (!win) {
//			int counter = 0;
//			currentPlayer = players[counter];
//			currentPlayer.turn();
//			if (currentPlayer.Hand.isEmpty()) {
//				win = true;
//			}
//		}
		return -1;
	}
	
	
}
