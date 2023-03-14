
public class Letter {
	char letter;
	boolean horizontal;
	boolean vertical;
	
	
	public Letter(char letter) {
		this.letter = letter;
		this.horizontal = false;
		this.vertical = false;
	}
	public char getLetter() {
		return letter;
	}
	public void setLetter(char letter) {
		this.letter = letter;
	}
	public boolean isHorizontal() {
		return horizontal;
	}
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	public boolean isVertical() {
		return vertical;
	}
	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}
	
	
}
