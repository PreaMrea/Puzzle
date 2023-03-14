
public class ColumnNode {
	private String data;
	private ColumnNode next;
	
	public ColumnNode(String dataToAdd) {
		this.data = dataToAdd;
		this.next = null;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public ColumnNode getNext() {
		return next;
	}
	public void setNext(ColumnNode next) {
		this.next = next;
	}
	
}
