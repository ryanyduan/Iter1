package iteration1;


public class Tile implements Comparable<Tile> {
	
	private char colour;
	private int rank;
	public boolean justPlayed = false;
	private static char[] colours = {'R','B','G','O'};
	
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
	
	public int findIndex(char targetColour) {
		int index = 0;
		for (char c : colours) {
			if (c == targetColour) return index;
			else index++;
		}
		return 0;
	}


	public int compareTo(Tile nextTile) {
		
		if (findIndex(this.getColour()) < findIndex(nextTile.getColour())) return -1;
		else if (findIndex(this.getColour()) > findIndex(nextTile.getColour())) return 1;
		else {
			if (this.getRank() < nextTile.getRank()) return -1;
			else if (this.getRank() > nextTile.getRank()) return 1;
			return 0;
		}
		
	}
	
	public String toString() {
		if (this.justPlayed) return "*" + this.colour + Integer.toString(this.rank) + " ";
		return this.colour + Integer.toString(this.rank) + " ";
	}
	
}
