package iteration1;

public class Rummikub {	
	
	public static Table table;
	public static Human human;
	public static Strategy1 s1;
	public static Strategy3 s3;
	public static Player[] players;
	public static Player currentPlayer;
	public static boolean win;

	public static void main(String[] args) {
		
		win = false;
		
		table = new Table();
		players = new Player[3];
		human = new Human(table, "Human");
		s1 = new Strategy1(table, "Strategy1");
		s3 = new Strategy3(table, "Strategy3");
		players[0] = human;
		players[1] = s1;
		players[2] = s3;
		human.displayHand();
		s1.displayHand();
		s3.displayHand();
		
		int counter = 0;
		while (!win) {
			currentPlayer = players[counter%3];
			
			while (currentPlayer.turn()) { //this line will also execute the player's turn
				if (currentPlayer.Hand.isEmpty()) {
					System.out.println(currentPlayer.getName() + " is the winner!");
					win = true;
					break;
				}
			}
			counter++; //next player's turn
		}
	}
		
}
