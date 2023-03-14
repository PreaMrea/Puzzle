
public class MultiLinkedList {
	private RowNode head;

	public MultiLinkedList() {
		this.head = null;
	}

	public RowNode getHead() {
		return head;
	}

	public void setHead(RowNode head) {
		this.head = head;
	}

	public void addRow(char dataToAdd) {
		if (head == null) {
			RowNode newnode = new RowNode(dataToAdd);
			head = newnode;
		} else {
			RowNode temp = head;
			while (temp.getDown() != null) {
				temp = temp.getDown();
			}
			RowNode newnode = new RowNode(dataToAdd);
			temp.setDown(newnode);
		}
	}

	public void addColumn(char letter, String word) {
		if (head == null)
			System.out.println("Add a row before adding elements");
		else {
			RowNode temp = head;
			while(temp != null) {
				if(letter == temp.getRowName()) {
					ColumnNode temp2 = temp.getRight();
					if(temp2 == null) {
						ColumnNode newnode = new ColumnNode(word);
						temp.setRight(newnode);
					}
					else {
						while(temp2.getNext() != null) {
							temp2 = temp2.getNext();
						}
						ColumnNode newnode = new ColumnNode(word);
						temp2.setNext(newnode);
					}
				}
				temp = temp.getDown();
			}

		}
	}

	public int size() {
		int count = 0;
		if (head == null) {
			System.out.println("Linked List is empty");

		} else {
			RowNode temp = head;
			while (temp != null) {
				count++;
				temp = temp.getDown();
			}
		}
		return count;
	}

	public void Display() {
		if (head == null)
			System.out.println("Linked list is empty");
		else {
			RowNode temp = head;
			while (temp != null) {
				System.out.print(temp.getRowName() + "=> ");
				ColumnNode temp2 = temp.getRight();
				while (temp2 != null) {
					System.out.print((String) temp2.getData() + " ");
					temp2 = temp2.getNext();
				}
				temp = temp.getDown();
				System.out.println();

			}
		}
	}
	public boolean search(String dataToSearch) {
		if(head == null) {
			System.out.println("List is null");
			return false;
		}
		else {
			RowNode temp = head;
			while(temp != null) {
				ColumnNode temp2 = temp.getRight();
				while(temp2 != null) {
					if((String)temp2.getData() == dataToSearch) {
						return true;
					}
					else {
						temp2 = temp2.getNext();
					}
				}
				temp = temp.getDown();
			}
			return false;
		}
		
	}

}
