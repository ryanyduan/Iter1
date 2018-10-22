package iteration1;

public class Tile implements Comparable<Tile> {
	
	private char colour;
	private int rank;
	private boolean justPlayed = false;
	
	
	public Tile(char colour, int rank){
		this.colour = colour;
		this.rank = rank;
	}
	
	public char getColour() {
		return colour;
	}

	public int getRank() {
		return rank;
	}

	public void isJustPlayed() {
		this.justPlayed = true;
	}


	public int compareTo(Tile nextTile) {
		if (this.getColour() < nextTile.getColour()) return -1;
		else if (this.getColour() > nextTile.getColour()) return 1;
		else {
			if (this.getRank() < nextTile.getRank()) return -1;
			else if (this.getRank() > nextTile.getRank()) return 1;
			return 0;
		}
		
	}
	
	public String toString() {
		return this.colour + Integer.toString(this.rank);
	}
	
}
