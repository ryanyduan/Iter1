package iteration1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Table {
	
	private static char[] COLORS = {'O','G','R','B'};
	public ArrayList<Tile> Deck;
	public ArrayList<ArrayList<Tile>> Board;
	private List<Observer> observers;
	
	public Table() {
		observers = new ArrayList<Observer>();
		Deck = new ArrayList<Tile>();
		Board = new ArrayList<ArrayList<Tile>>();
		createDeck();
	}
	
	public Tile draw() {
		return Deck.remove(0);
	}
	
	private void createDeck() {
		for (int i = 1; i < 14; i++) {
			for (char c: COLORS) {
				for (int j = 0; j < 2; j++) {
					Deck.add(new Tile(c, i));
				}
			}
		}
		Collections.shuffle(Deck);
	}
	
	public String displayBoard() {
		String boardMelds = ""; 
		for (ArrayList<Tile> meld : this.Board) {
			boardMelds += "{" + meld.toString() + "}";
		}
		System.out.println(boardMelds);
		return boardMelds;
	}
	
	public void attach(Observer observer){
	      observers.add(observer);	
	   }
	
	public void notifyAllObservers(){
	      for (Observer observer : observers) {
	         observer.update();
	      }
	   } 
	

}
