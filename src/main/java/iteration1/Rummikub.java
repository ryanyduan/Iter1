package iteration1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Rummikub {	
	
	public static Table table;
	public static Human human;
	public static ArrayList<Tile> Deck;
	public static Strategy1 s1;
	public static Strategy2 s2;
	public static Strategy3 s3;
	public static Player[] players;
	public static Player currentPlayer;
	public static boolean win;
	public static boolean TA_Test = true;

	public static void main(String[] args) throws IOException {
		
		win = false;
		Deck = new ArrayList<Tile>();
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
		
		if (TA_Test) {
			human.Hand.clear();
			s1.Hand.clear();
			s2.Hand.clear();
			s3.Hand.clear();
			
			String content;
			content = new String(Files.readAllBytes(Paths.get("test2.txt")));
			String[] moves = content.split(" ");
			
			for (int i = 0; i < moves.length; i++) {
				Deck.add(new Tile(moves[i].charAt(0), Integer.valueOf(moves[i].substring(1))));
			}
			
			table.Deck = Deck;
			
			for (Player p: players) {
				for (int i = 0; i < 14; i++) {
					p.draw();
				}
			}
		}
		Collections.sort(human.Hand);
		Collections.sort(s1.Hand);
		Collections.sort(s2.Hand);
		Collections.sort(s3.Hand);
		human.displayHand();
		s1.displayHand();
		s2.displayHand();
		s3.displayHand();
		
		int counter = 0;
		while (!win) {
			
			currentPlayer = players[counter%4];
			
			while (currentPlayer.turn()) {
				if (currentPlayer.Hand.isEmpty()) {
					System.out.println(currentPlayer.getName() + " is the winner!");
					win = true;
					break;
				}
			}
		
			counter++; //next player's turn
			if (players[0].checkTurn() && players[1].checkTurn() && players[2].checkTurn() && players[3].checkTurn() && !s3.s3Condition() && table.Deck.isEmpty()) {
				System.out.println("No cards left and no moves left for any player to play.  Game over with no winner.");
				win = true;
				break;
			}
			
			else {
				for (Player p: players) {
					System.out.println(p.getName());
					System.out.println(p.is30);
					System.out.println(p.runs);
					System.out.println(p.sets);
				}
				
				System.out.println(s3.s3Condition());
			}
		}
	}
		
}
