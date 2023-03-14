
public class RowNode {
	private char rowName;
	private RowNode down;
	private ColumnNode right;
	
	
	public RowNode(char rowName) {
		this.rowName = rowName;
		this.down = null;
		this.right = null;
	}
	public char getRowName() {
		return rowName;
	}
	public void setRowName(char rowName) {
		this.rowName = rowName;
	}
	public RowNode getDown() {
		return down;
	}
	public void setDown(RowNode down) {
		this.down = down;
	}
	public ColumnNode getRight() {
		return right;
	}
	public void setRight(ColumnNode right) {
		this.right = right;
	}
}
