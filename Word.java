
public class Word {
	private String english;
	private String turkish;
	
	private int x;
	private int y;
	private int direction;
	private boolean completed;
	
	
	public Word(String english, String turkish) {
		this.english = english;
		this.turkish = turkish;
		x = 1000;
		y = 1000;
		this.direction = 0;
		completed = false;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getTurkish() {
		return turkish;
	}
	public void setTurkish(String turkish) {
		this.turkish = turkish;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
}
