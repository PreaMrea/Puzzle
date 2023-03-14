import java.awt.Color;

public class SingleLinkedList {
	private Node head;

	public SingleLinkedList() {
		head = null;
	}

	public void addBetween(Object dataToAdd) {
		if (head != null) {
			Node temp = head;
			Node previous = null;
			while (temp != null && (((Word)dataToAdd).getEnglish()).compareTo(((Word)temp.getData()).getEnglish())>0) {
				previous = temp;
				temp = temp.getLink();
			}
			if (temp == null) {
				Node newNode = new Node(dataToAdd);
				previous.setLink(newNode);
			} else if(previous == null) {
				Node newNode = new Node(dataToAdd);
				newNode.setLink(temp);
				head = newNode;
			}
			else{
				Node newNode = new Node(dataToAdd);
				newNode.setLink(temp);
				previous.setLink(newNode);
			}
		} else {
			Node newNode = new Node(dataToAdd);
			head = newNode;
		}

	}

	public void addLast(Object dataToAdd) {
		if (head == null) {
			Node newNode = new Node(dataToAdd);
			head = newNode;
		} else {
			Node temp = head;
			while (temp.getLink() != null) {
				temp = temp.getLink();
			}
			Node newNode = new Node(dataToAdd);
			temp.setLink(newNode);
		}
	}

	public void addFirst(Object dataToAdd) {
		if (head == null) {
			Node newNode = new Node(dataToAdd);
			head = newNode;
		} else {
			while (head != null) {
				if ((int) dataToAdd < (int) head.getData()) {
					Node newNode = new Node(dataToAdd);
					newNode.setLink(head);
					head = newNode;
				} else {

				}
			}

		}
	}

	public void delete(Object dataToDelete) {
		if (head == null)
			System.out.println("Linked List is empty");
		else {
			while (((String) head.getData()).equals(((String) dataToDelete)))
				head = head.getLink();
			Node temp = head;
			Node previous = null;
			while (temp != null) {
				if (((String) temp.getData()).equals(((String) dataToDelete))) {
					previous.setLink(temp.getLink());
					temp = previous;
				}
				previous = temp;
				temp = temp.getLink();
			}

		}
	}

	public void display() {
		if (head == null) 
			System.out.print(" ");
			
		else {
			Node temp = head;
			int counter = 0;
			int a = 2;
			int b = 23;
			int sayac = 1;
			Game.cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.BLACK));
			while (temp != null) {
				Game.cn.getTextWindow().setCursorPosition(b, a);
				if(((Word)temp.getData()).isCompleted() == true)
					System.out.print("[x]" +((Word)temp.getData()).getEnglish() + " ");
				else
					System.out.print("[ ]" +((Word)temp.getData()).getEnglish() + " ");
				temp = temp.getLink();
				counter++;
				a++;
				if(counter == 20) {
					counter = 0;
					b = b + 20;
					a = 2;
				}
			}
		}
		System.out.println("");
	}

	public boolean search(Object dataToSearch) {
		boolean flag = false;
		int item = (int)dataToSearch;
		if (head == null) {
			System.out.println("Linked list is empty");
		} else {
			Node temp = head;
			while (temp != null) {
				if (item == (int)temp.getData())
					flag = true;
				temp = temp.getLink();

			}
		}
		return flag;
	}

	public int size() {
		int count = 0;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			Node temp = head;
			while (temp != null) {
				count++;
				temp = temp.getLink();
			}
		}
		return count;
	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}
}
