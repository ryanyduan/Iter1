package iteration1;

public class Rummikub {	
	
	public static Table table;
	public static Human human;
	public static Strategy1 s1;
	public static Strategy2 s2;
	public static Strategy3 s3;
	public static Player[] players;
	public static Player currentPlayer;
	public static boolean win;

	public static void main(String[] args) {
		
		win = false;
		
		table = new Table();
		players = new Player[4];
		human = new Human(table, "Human");
		s1 = new Strategy1(table, "Strategy1");
		s2 = new Strategy2(table, "Strategy2");
		s3 = new Strategy3(table, "Strategy3");
		players[0] = human;
		players[1] = s1;
		players[2] = s2;
		players[3] = s3;
		human.displayHand();
		s1.displayHand();
		s2.displayHand();
		s3.displayHand();
		
		int counter = 0;
		while (!win) {
			
			currentPlayer = players[counter%4];
			
			while (currentPlayer.turn()) { //this line will also execute the player's turn
				if (currentPlayer.Hand.isEmpty()) {
					System.out.println(currentPlayer.getName() + " is the winner!");
					win = true;
					break;
				}
			}
			
			counter++; //next player's turn
			if (players[0].checkTurn() && players[1].checkTurn() && players[2].checkTurn() && players[3].checkTurn() && table.Deck.isEmpty()) {
				System.out.println("No cards left and no moves left for any player to play.  Game over with no winner.");
				win = true;
				break;
			}
		}
	}
		
}
