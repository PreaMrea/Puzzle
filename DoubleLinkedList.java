import java.awt.Color;

import enigma.core.Enigma;

public class DoubleLinkedList {
	private NodeDouble head;
	private NodeDouble tail;
	public enigma.console.Console cn = Enigma.getConsole("Puzzle Game");
	public DoubleLinkedList()
	{
		head=null;
		tail=null;
	}
	public void add(Object dataToAdd) { // adds to the end of the list
		NodeDouble newnode = new NodeDouble(dataToAdd);
		if(head == null) {
			head = newnode;
			tail = newnode;
		}
		else {
			NodeDouble temp = head;
			if(((Gamer) dataToAdd).getScore()>((Gamer)temp.getData()).getScore()) { // adding to the front
				temp.setPrev(newnode);
				newnode.setNext(temp);
				temp = newnode;
			}
			else {
				int count=0;
				while(temp.getNext()!=null && ((Gamer) dataToAdd).getScore()<=((Gamer)temp.getData()).getScore()) {
					temp = temp.getNext();
					count++;
				}
				if(temp.getNext()==null && ((Gamer) dataToAdd).getScore()>((Gamer)temp.getData()).getScore()) {
					temp.getPrev().setNext(newnode);
					newnode.setPrev(temp.getPrev());
					temp.setPrev(newnode);
					newnode.setNext(temp);
					for (int i = 0; i <= count; i++) {
						temp = temp.getPrev();
					}
				}
				else if(temp.getNext() != null) {
					temp.getPrev().setNext(newnode);
					newnode.setPrev(temp.getPrev());
					temp.setPrev(newnode);
					newnode.setNext(temp);
					for (int i = 0; i <= count; i++) {
						temp = temp.getPrev();
					}
				}
				else { // adding to the end
					newnode.setPrev(temp);
					temp.setNext(newnode);
					tail = newnode;	
					for (int i = 0; i < count; i++) {
						temp = temp.getPrev();
					}
				}	
			}
			head = temp;
		}
	}
	public void Display() {
		int count=1;
		int a=25;
		int b=58;
		int counter = 0;
		cn.setTextAttributes(new enigma.console.TextAttributes(Color.PINK, Color.BLACK));
		cn.getTextWindow().setCursorPosition(b, a);
		System.out.println("-High Score Table-");
		a=a+1;
		if(head==null) 
		{
			cn.getTextWindow().setCursorPosition(b, a);
			cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.BLACK));
			System.out.println("linked list is empty");
		}
		else 
		{
			NodeDouble temp=head;
			while(temp!=null && counter < 10) 
			{
				cn.getTextWindow().setCursorPosition(b, a);
				cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.BLACK));
				System.out.println(count+". "+((Gamer) temp.getData()).getName()+": "+((Gamer) temp.getData()).getScore());
				a++;			
				count++;
				temp=temp.getNext();
				counter++;
			}	
		}
		System.out.println();
	
	}
}
