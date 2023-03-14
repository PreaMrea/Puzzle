
import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import enigma.console.TextAttributes;
import java.awt.Color;
import java.util.*;

public class Game {
	public static enigma.console.Console cn = Enigma.getConsole("Game", 160, 45, 18, 0);
	public TextMouseListener tmlis;
	public KeyListener klis;

	// ------ Standard variables for mouse and keyboard ------
	public int mousepr; // mouse pressed?
	public int mousex, mousey; // mouse text coords.
	public int keypr; // key pressed?
	public int rkey; // key (for press/release)
	// ----------------------------------------------------
	
	Game() throws Exception { // --- Contructor
		int ScreenX=24;
		int finishcount=0;
		int ScreenY=1;
		boolean flag=false;
		String whosewin="Player1";
		int wordcount=0;	
		int score=0;
		String player1name;
		String player2name;
		int player1score=0;
		int player2score=0;
		String whoisturn=null;
		System.out.println("Enter player1 name:");
		Scanner input=new Scanner(System.in);
		player1name=input.next();		
		Gamer player1=new Gamer(player1name,player1score);
		System.out.println("Enter player2 name: ");
		player2name=input.next();
		Gamer player2=new Gamer(player2name,player2score);
		whoisturn=player1.getName();
		cn.getTextWindow().setCursorPosition(0, 0);
		for(int i=0;i<4;i++)
		{
			System.out.println("                                                ");
		}
		cn.getTextWindow().setCursorPosition(23, 0);
		cn.setTextAttributes(new enigma.console.TextAttributes(Color.PINK, Color.BLACK));
		System.out.print("Score==> ");
		cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.RED));
		System.out.print(player1.getName()+":"+player1.getScore()+"   ");
		cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.BLACK));
		System.out.println(player2.getName()+":"+player2.getScore());
		cn.getTextWindow().setCursorPosition(0, 0);
		System.out.print("Word: "+wordcount);
		cn.getTextWindow().setCursorPosition(0, 1);
		cn.setTextAttributes(new enigma.console.TextAttributes(Color.CYAN, Color.BLACK));
		for(int i=1;i<23;i++)
		{
			cn.getTextWindow().setCursorPosition(22,ScreenY);
			System.out.print("|");
			ScreenY++;
		}
		ScreenY=1;
		for(int i=1;i<97;i++)
		{
			cn.getTextWindow().setCursorPosition(ScreenX,1);
			System.out.print("-");
			ScreenX++;
		}
		ScreenX=24;
		for(int i=1;i<23;i++)
		{
			cn.getTextWindow().setCursorPosition(122,ScreenY);
			System.out.print("|");
			ScreenY++;
		}
		String fileName = "puzzle.txt";
		for(int i=1;i<97;i++)
		{
			cn.getTextWindow().setCursorPosition(ScreenX,22);
			System.out.print("-");
			ScreenX++;
		}
		char[][] puzzle = new char[17][17];
		String fileName1 = "puzzle.txt";
		int counter = 0;
		int printX = 2;
		int printY = 2;
		int printFrameX = 2;
		int printFrameY = 1;
		if (fileName1.equalsIgnoreCase("puzzle.txt")) {
			File file = new File("C:\\Users\\Acer\\Desktop\\puzzle.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			String st;
			for (int i = 0; i < puzzle.length-2; i++) {
				cn.getTextWindow().setCursorPosition(printFrameX, printFrameY);
				System.out.println(counter);
				printFrameX++;
				if (counter != 9)
					counter++;
				else
					counter = 0;
			}
			counter = 0;
			printFrameX = 1;
			printFrameY = 2;
			for (int i = 0; i < puzzle.length-2; i++) {
				cn.getTextWindow().setCursorPosition(printFrameX, printFrameY);
				System.out.println(counter);
				printFrameY++;
				if (counter != 9)
					counter++;
				else
					counter = 0;
			}
			counter = 0;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < puzzle.length; j++) {
					puzzle[counter][j] = '0';

				}
				counter = 16;
			}
			counter = 0;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < puzzle.length; j++) {
					puzzle[j][counter] = '0';
				}
				counter = 16;

			}

			counter = 1;
			cn.getTextWindow().setCursorPosition(printX, printY);
			while ((st = br.readLine()) != null) {
				int index = 0;
				for (int i = 1; i < 16; i++) {
					puzzle[counter][i] = st.charAt(index);
					if (puzzle[counter][i] == '0') {
						cn.setTextAttributes(new enigma.console.TextAttributes(Color.GRAY, Color.GRAY));
						System.out.print(st.charAt(index));
					} else if (puzzle[counter][i] == '1') {
						cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.DARK_GRAY));
						System.out.print('.');
					} else {
						finishcount+=1;
						cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN ,Color.DARK_GRAY));
						System.out.print(st.charAt(index));
					}
					index++;
				}
				printY++;
				printX = 2;
				cn.getTextWindow().setCursorPosition(printX, printY);
				counter++;

			}
			System.out.println();

		}

		SingleLinkedList sll = new SingleLinkedList();
		MultiLinkedList mll = new MultiLinkedList();
		fileName = "word_list.txt";
		if (fileName.equalsIgnoreCase("word_list.txt")) {
			fileName = "word_list.txt";
			File file = new File("C:\\Users\\Acer\\Desktop\\word_list.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			String st;
			while ((st = br.readLine()) != null) {
				String[] readWords = st.split(",");
				Word element = new Word(readWords[0], readWords[1]);// Taking the words turkish and english meaning
				sll.addBetween(element);
			}
		}

		cn.setTextAttributes(new enigma.console.TextAttributes(Color.BLACK, Color.WHITE));
		cn.getTextWindow().setCursorPosition(3, 20);

		// Creating mll and filling it with the words
		Node temp1 = sll.getHead();
		mll.addRow(((Word) temp1.getData()).getEnglish().charAt(0));
		char letter = ((Word) temp1.getData()).getEnglish().charAt(0);
		while (temp1 != null) {
			if (((Word) temp1.getData()).getEnglish().charAt(0) != letter) {
				mll.addRow(((Word) temp1.getData()).getEnglish().charAt(0));
				letter = ((Word) temp1.getData()).getEnglish().charAt(0);
			}
			while (temp1 != null && ((Word) temp1.getData()).getEnglish().charAt(0) == letter) {
				mll.addColumn(letter, ((Word) temp1.getData()).getEnglish());
				temp1 = temp1.getLink();

			}
		}

		sll.display();// Displaying sll
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		// mll.Display();

		Letter[][] solution = new Letter[15][15];
		counter = 0;
		fileName = "solution.txt";
		if (fileName.equalsIgnoreCase("solution.txt")) {
			fileName = "solution.txt";
			File file = new File("C:\\Users\\Acer\\Desktop\\solution.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			String st;
			while ((st = br.readLine()) != null) {
				for (int i = 0; i < solution.length; i++) {
					Letter element = new Letter(st.charAt(i));// letters taken from solution txt
					solution[counter][i] = element; // adding letters to solution array
				}
				counter++;
			}
		}
		boolean finish = false;
		for (int i = 0; i < solution.length; i++) {
			for (int j = 0; j < solution.length; j++) {
				if (solution[i][j].getLetter() != '0' && j != 15) {
					int data1 = j + 1;
					int data1Backup = 0;
					int countLett = 0;
					String word = String.valueOf(solution[i][j].getLetter());
					while (data1 < 15 && solution[i][data1].getLetter() != '0') {// Horizontal control
						if (data1 != 15 && solution[i][data1].getLetter() != '0'
								&& !solution[i][data1].isHorizontal()) {
							word = word + String.valueOf(solution[i][data1].getLetter());
							solution[i][data1].setHorizontal(true);
							countLett++;
							if (countLett == 1)// To add coordinates to the word correctly , the first coordinate has
												// saved.
								data1Backup = data1;
							finish = true;
							if (data1 == 14 || (data1 != 14 && solution[i][data1 + 1].getLetter() == '0')) {// Is the
																											// word
																											// completely
																											// collected
								Node temp = sll.getHead();
								while (temp != null && word.length() > 2) {
									if ((((Word) temp.getData()).getEnglish()).equals(word)) {

										((Word) temp.getData()).setX(data1Backup);
										((Word) temp.getData()).setY(i + 1);
										((Word) temp.getData()).setDirection(1);// Horizontal
										finish = false;
										break;
									} else {
										finish = false;
									}
									temp = temp.getLink();
								}
								if (word.length() <= 2)
									finish = false;
							}

						}
						if (!finish)
							word = "";
						data1++;
					}
					word = "";// yeni bir harften aramaya baþlanýlacaðý için içini boþalttýk.
				}

				if (i != 15 && solution[i][j].getLetter() != '0') {
					int countLett = 0;
					int data2 = i + 1;
					int data2Backup = 0;
					String word2 = String.valueOf(solution[i][j].getLetter());
					while (solution[data2][j].getLetter() != '0' && data2 <= 15) {// Vertical control
						if (data2 != 15 && solution[data2][j].getLetter() != '0' && !solution[data2][j].isVertical()) {
							word2 = word2 + String.valueOf(solution[data2][j].getLetter());
							solution[data2][j].setVertical(true);
							countLett++;
							if (countLett == 1)
								data2Backup = data2;
							finish = true;
							if (data2 == 14 || (data2 != 14 && solution[data2 + 1][j].getLetter() == '0')) {
								Node temp2 = sll.getHead();
								while (temp2 != null && word2.length() > 2) {
									if ((((Word) temp2.getData()).getEnglish()).equals(word2)) {
										((Word) temp2.getData()).setY(data2Backup);
										((Word) temp2.getData()).setX(j + 1);
										((Word) temp2.getData()).setDirection(2);// Vertical
										finish = false;
										break;
									} else {
										finish = false;
									}
									temp2 = temp2.getLink();
								}
								if (word2.length() <= 2)
									finish = false;
							}
						}
						if (!finish)
							word2 = "";
						data2++;
					}
					word2 = "";
				}
			}
		}
		// whoisturn=player1.getName();
		String[] wordscore = new String[2];
		fileName = "high_score_table.txt";
		DoubleLinkedList dll = new DoubleLinkedList();
		if (fileName.equalsIgnoreCase("high_score_table.txt")) {
			File file = new File("C:\\Users\\Acer\\Desktop\\high_score_table.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			String st;
			while ((st = br.readLine()) != null) {
				wordscore = st.split(";");
				int count = Integer.parseInt(wordscore[1]);
				Gamer playerX = new Gamer(wordscore[0], count);
				dll.add(playerX);

			}
		}
		// dll.Display();

		// ------ Standard code for mouse and keyboard ------ Do not change
		tmlis = new TextMouseListener() {
			public void mouseClicked(TextMouseEvent arg0) {
			}

			public void mousePressed(TextMouseEvent arg0) {
				if (mousepr == 0) {
					mousepr = 1;
					mousex = arg0.getX();
					mousey = arg0.getY();
				}
			}

			public void mouseReleased(TextMouseEvent arg0) {
			}
		};
		cn.getTextWindow().addTextMouseListener(tmlis);

		klis = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		};
		cn.getTextWindow().addKeyListener(klis);
		// ----------------------------------------------------

		cn.getTextWindow().setCursorType(0);
		int px = 2;
		int py = 2;
		score = 0;
		// Is the letter is correct
		boolean isTrue = false;
		int letterCounter = 0;// The counter of the entered letters
		int controlX = 0;// puzzledaki harflerle solution daki harfleri karþýlaþtýrmada index oalrak
							// kullandýðým deðiþkenler
		int controlY = 0;
		boolean isIntersection = false;// Kesiþim noktasý kontrolü
		int direction = 0;// Determining the words direction

		boolean isPressedLetter = false;// Is pressed a letter,
		boolean isPressedDirect = false;// is a direction has entered in an intersection
		char rckey = ' ';
		int turn = 1;
		while (true) {
			int key = 0;
			if(turn==1)
			{
				cn.getTextWindow().setCursorPosition(23, 0);
				cn.setTextAttributes(new enigma.console.TextAttributes(Color.PINK, Color.BLACK));
				System.out.print("Score==> ");
				cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.BLUE));
				System.out.print(player1.getName()+":"+player1.getScore()+"   ");
				cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.BLACK));
				System.out.println(player2.getName()+":"+player2.getScore());
			}
			else
			{
				cn.getTextWindow().setCursorPosition(23, 0);
				cn.setTextAttributes(new enigma.console.TextAttributes(Color.PINK, Color.BLACK));
				System.out.print("Score==> ");
				cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.BLACK));
				System.out.print(player1.getName()+":"+player1.getScore()+"   ");
				cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.BLUE));
				System.out.println(player2.getName()+":"+player2.getScore());
			}
			cn.getTextWindow().setCursorPosition(0, 0);
			System.out.print("Word: "+wordcount);
			cn.getTextWindow().setCursorPosition(px, py);
			if (mousepr == 1) { // if mouse button pressed
//				px = mousex;
//				py = mousey;

				mousepr = 0; // last action
			}
			if (keypr == 1) { // if keyboard button pressed
				if (isPressedLetter == false) {
					if (rkey == KeyEvent.VK_LEFT) {
						px--;
						cn.getTextWindow().setCursorPosition(px, py);
					}
					if (rkey == KeyEvent.VK_RIGHT) {
						px++;
						cn.getTextWindow().setCursorPosition(px, py);
					}
					if (rkey == KeyEvent.VK_UP) {
						py--;
						cn.getTextWindow().setCursorPosition(px, py);
					}
					if (rkey == KeyEvent.VK_DOWN) {
						py++;
						cn.getTextWindow().setCursorPosition(px, py);
					}

					rckey = (char) rkey;
					key = rckey;
					if (key > 64 && key < 91) {
						cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.DARK_GRAY));
						if(puzzle[py - 1][px - 1] != solution[py - 2][px - 2].getLetter()) {
							String character = String.valueOf(rckey);
							character = character.toLowerCase();// Making letter lowercase
							if(character.equals("�"))
								character = "i";
							System.out.print(character);
							cn.getTextWindow().setCursorPosition(px, py);
							rckey = character.charAt(0);
							isPressedLetter = true;
							letterCounter = 1;
							puzzle[py - 1][px - 1] = rckey;
						}
						

					}

				} else {
					if (rkey == KeyEvent.VK_LEFT && px > 2 && direction != 2) {
						if (puzzle[py - 1][px - 2] != '0') {
							px--;
							cn.getTextWindow().setCursorPosition(px, py);
							isPressedDirect = true;
						}
					}
					if (rkey == KeyEvent.VK_RIGHT && px < 16 && direction != 2) {
						if (puzzle[py - 1][px] != '0') {
							px++;
							cn.getTextWindow().setCursorPosition(px, py);
							isPressedDirect = true;
						}
					}
					if (rkey == KeyEvent.VK_UP && py > 2 && direction != 1) {
						if (puzzle[py - 2][px - 1] != '0') {
							py--;
							cn.getTextWindow().setCursorPosition(px, py);
							isPressedDirect = true;
						}
					}
					if (rkey == KeyEvent.VK_DOWN && py < 16 && direction != 1) {
						if (puzzle[py][px - 1] != '0') {
							py++;
							cn.getTextWindow().setCursorPosition(px, py);
							isPressedDirect = true;
						}
					}
					rckey = (char) rkey;
					key = rckey;
					if (key > 64 && key < 91) {
						cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.DARK_GRAY));
						if(puzzle[py - 1][px - 1] != solution[py - 2][px - 2].getLetter()) {
							String character = String.valueOf(rckey);
							character = character.toLowerCase();
							if(character.equals("�"))
								character = "i";
							System.out.print(character);
							cn.getTextWindow().setCursorPosition(px, py);
							rckey = character.charAt(0);
							isPressedLetter = true;
							letterCounter++;
							puzzle[py - 1][px - 1] = rckey;
						}
					
						
						// Buraya aþþaðýda girdiðimiz harfin olduðu yerdeki kelime dikey öi yatay mý
						// olduðunu veren
						// direction ý kontrollere ekle
					}
					cn.getTextWindow().setCursorPosition(0, 27);
					cn.setTextAttributes(new enigma.console.TextAttributes(Color.BLACK, Color.BLACK));

					for(int i=0;i<4;i++)
					{
						System.out.println("                                                               ");
					}
					cn.getTextWindow().setCursorPosition(px, py);
				}

				
				// CONTROLS
				if (isPressedLetter == true && isPressedDirect == false && key > 64 && key < 91) {
					
					String answer = "";
					// Vertical words controls
					if ((puzzle[py - 1][px] == '0' && puzzle[py - 1][px - 2] == '0')
							|| (px != 2 && puzzle[py - 1][px - 2] != '0' && puzzle[py - 1][px] == '0'
									&& puzzle[py - 1][px - 3] == '0')
							|| (px != 16 && puzzle[py - 1][px - 2] == '0' && puzzle[py - 1][px] != '0'
									&& puzzle[py - 1][px + 1] == '0')
							|| direction == 2) {// Controlling
						// letter's
						// rigth
						// and
						// left.
						// If they are '0' then the word
						// stays vertical.
						String character = String.valueOf(rckey);
						character = character.toLowerCase();
						rckey = character.charAt(0);
						int up = py - 2;// Starting one point upper from the written letter.
						int countIndex = 1;

						while (up != 1 && puzzle[up][px - 1] != '0' && puzzle[up - 1][px - 1] != 0) {// Heading to the
																										// first letter
																										// of the word
							up--;
							countIndex++;
						}

						if (up != 1) {
							countIndex--;
							char lett = puzzle[up + 1][px - 1];
							boolean isFound = false;// Is answer has found
							int length = 0;

							Node temp = sll.getHead();
							while (temp != null && isFound == false) {
								if ((((Word) temp.getData()).getEnglish()).charAt(0) == lett) {
									if (((Word) temp.getData()).getX() == px - 1
											&& ((Word) temp.getData()).getY() == up + 1
											&& ((Word) temp.getData()).getDirection() == 2) {
										answer = ((Word) temp.getData()).getEnglish();
										isFound = true;
										length = answer.length();
										direction = 2;
									}
								}
								temp = temp.getLink();
							}

							RowNode temp2 = mll.getHead();
							boolean over = false;
							while (temp2 != null && over == false) {
								if ((char) temp2.getRowName() == lett) {
									ColumnNode control = temp2.getRight();
									while (control != null && over == false) {
										if (control.getData().length() == length
												&& control.getData().charAt(countIndex) == rckey) {
											isTrue = true;
											over = true;
											score++;
											if (turn == 1) {
												player1.setScore(player1.getScore() + score);
											} else
												player2.setScore(player2.getScore() + score);
											score = 0;
										} else
											control = control.getNext();
									}
								} else if ((char) temp2.getRowName() != lett)
									isTrue = false;
								temp2 = temp2.getDown();
							}
							if (!isTrue) {
								score -= 2;
								if (turn == 1) {
									player1.setScore(player1.getScore() + score);
								} else
									player2.setScore(player2.getScore() + score);
								score = 0;
								direction = 0;
								puzzle[py - 1][px - 1] = '1';
								cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.DARK_GRAY));
								cn.getTextWindow().setCursorPosition(px, py);
								System.out.print(".");
							}

						} else {
							char lett = puzzle[up][px - 1];
							boolean isFound = false;
							int length = 0;
							answer = "";
							Node temp = sll.getHead();

							while (temp != null && isFound == false) {
								if ((((Word) temp.getData()).getEnglish()).charAt(0) == lett) {
									if (((Word) temp.getData()).getX() == px - 1 && ((Word) temp.getData()).getY() == up
											&& ((Word) temp.getData()).getDirection() == 2) {
										answer = ((Word) temp.getData()).getEnglish();
										isFound = true;
										direction = 2;
										length = answer.length();
									}
								}
								temp = temp.getLink();
							}

							RowNode temp2 = mll.getHead();
							boolean over = false;
							while (temp2 != null && over == false) {
								if ((char) temp2.getRowName() == lett) {
									ColumnNode control = temp2.getRight();
									while (control != null && over == false) {
										if (control.getData().length() == length
												&& control.getData().charAt(countIndex) == rckey) {
											isTrue = true;
											over = true;
											score++;
											if (turn == 1) {
												player1.setScore(player1.getScore() + score);
											} else
												player2.setScore(player2.getScore() + score);
											score = 0;
										} else
											control = control.getNext();
									}
								} else if ((char) temp2.getRowName() != lett)
									isTrue = false;
								temp2 = temp2.getDown();
							}
							if (!isTrue) {
								score -= 2;
								if (turn == 1) {
									player1.setScore(player1.getScore() + score);
								} else
									player2.setScore(player2.getScore() + score);
								score = 0;
								direction = 0;
								cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.DARK_GRAY));
								cn.getTextWindow().setCursorPosition(px, py);
								System.out.print(".");
							}
						}

					}

					// Horizontal words controls
					else if ((puzzle[py - 2][px - 1] == '0' && puzzle[py][px - 1] == '0')
							|| (py != 16 && puzzle[py - 2][px - 1] == '0' && puzzle[py][px - 1] != '0'
									&& puzzle[py + 1][px - 1] == '0')
							|| (py != 2 && puzzle[py - 2][px - 1] != '0' && puzzle[py - 3][px - 1] == '0'
									&& puzzle[py][px - 1] == '0')
							|| direction == 1) {// Controlling
						// letter's
						// top side
						// and
						// bottom
						// side
						// If they are '0' then the word
						// stays horizontal.
						String character = String.valueOf(rckey);
						character = character.toLowerCase();
						rckey = character.charAt(0);
						int left = px - 2;// Starting one point left from the written letter.
						int countIndex = 1;
						while (left != 1 && puzzle[py - 1][left] != '0') {// Heading
							// word
							left--;
							countIndex++;
						}

						if (left != 1) {
							countIndex--;
							char lett = puzzle[py - 1][left + 1];
							boolean isFound = false;// Is answer has found
							int length = 0;
							answer = "";
							Node temp = sll.getHead();
							while (temp != null && isFound == false) {
								if ((((Word) temp.getData()).getEnglish()).charAt(0) == lett) {
									if (((Word) temp.getData()).getX() == left + 1
											&& ((Word) temp.getData()).getY() == py - 1
											&& ((Word) temp.getData()).getDirection() == 1) {
										answer = ((Word) temp.getData()).getEnglish();
										isFound = true;
										length = answer.length();
										direction = 1;
									}
								}
								temp = temp.getLink();
							}

							RowNode temp2 = mll.getHead();
							boolean over = false;
							while (temp2 != null && over == false) {
								if ((char) temp2.getRowName() == lett) {
									ColumnNode control = temp2.getRight();
									while (control != null && over == false) {
										if (control.getData().length() == length
												&& control.getData().charAt(countIndex) == rckey) {
											isTrue = true;
											over = true;
											score++;
											if (turn == 1) {
												player1.setScore(player1.getScore() + score);
											} else
												player2.setScore(player2.getScore() + score);
											score = 0;
										} else
											control = control.getNext();
									}
								} else if ((char) temp2.getRowName() != lett)
									isTrue = false;
								temp2 = temp2.getDown();
							}
							if (!isTrue) {
								score -= 2;
								if (turn == 1) {
									player1.setScore(player1.getScore() + score);
								} else
									player2.setScore(player2.getScore() + score);
								score = 0;
								direction = 0;
								cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.DARK_GRAY));
								cn.getTextWindow().setCursorPosition(px, py);
								System.out.print(".");
							}

						} else {
							char lett = puzzle[py - 1][left];
							boolean isFound = false;
							int length = 0;
							answer = "";
							Node temp = sll.getHead();

							while (temp != null && isFound == false) {
								if ((((Word) temp.getData()).getEnglish()).charAt(0) == lett) {
									if (((Word) temp.getData()).getX() == left
											&& ((Word) temp.getData()).getY() == py - 1
											&& ((Word) temp.getData()).getDirection() == 2) {
										answer = ((Word) temp.getData()).getEnglish();
										isFound = true;
										length = answer.length();
										direction = 2;
									}
								}
								temp = temp.getLink();
							}

							RowNode temp2 = mll.getHead();
							boolean over = false;
							while (temp2 != null && over == false) {
								if ((char) temp2.getRowName() == lett) {
									ColumnNode control = temp2.getRight();
									while (control != null && over == false) {
										if (control.getData().length() == length
												&& control.getData().charAt(countIndex) == rckey) {
											isTrue = true;
											over = true;
											score++;
										} else
											control = control.getNext();
									}
								} else if ((char) temp2.getRowName() != lett)
									isTrue = false;
								temp2 = temp2.getDown();
							}
							if (!isTrue) {
								score -= 2;
								if (turn == 1) {
									player1.setScore(player1.getScore() + score);
								} else
									player2.setScore(player2.getScore() + score);
								score = 0;
								direction = 0;
								cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.DARK_GRAY));
								cn.getTextWindow().setCursorPosition(px, py);
								System.out.print(".");
							}
						}
					} else {

						if (letterCounter == 1) {
							controlX = px - 1;
							controlY = py - 1;
							isIntersection = true;
						} else if (letterCounter >= 2 && isIntersection == true) {
							if (py == controlY + 1) {
								direction = 1;
							} else if (px == controlY + 1) {
								direction = 2;
							}

							// Vertical words controls
							if (direction == 2) {
								String character = String.valueOf(rckey);
								character = character.toLowerCase();
								rckey = character.charAt(0);
								int up = py - 2;// Starting one point upper from the written letter.
								int countIndex = 1;

								while (up != 1 && puzzle[up][px - 1] != '0' && puzzle[up - 1][px - 1] != 0) {// Heading
																												// to
																												// the
																												// first
																												// letter
																												// of
																												// the
																												// word
									up--;
									countIndex++;
								}

								if (up != 1) {
									countIndex--;
									char lett = puzzle[up + 1][px - 1];
									boolean isFound = false;// Is answer has found
									int length = 0;

									Node temp = sll.getHead();
									while (temp != null && isFound == false) {
										if ((((Word) temp.getData()).getEnglish()).charAt(0) == lett) {
											if (((Word) temp.getData()).getX() == px - 1
													&& ((Word) temp.getData()).getY() == up + 1
													&& ((Word) temp.getData()).getDirection() == 2) {
												answer = ((Word) temp.getData()).getEnglish();
												isFound = true;
												length = answer.length();
												direction = 2;
											}
										}
										temp = temp.getLink();
									}

									RowNode temp2 = mll.getHead();
									boolean over = false;
									while (temp2 != null && over == false) {
										if ((char) temp2.getRowName() == lett) {
											ColumnNode control = temp2.getRight();
											while (control != null && over == false) {
												if (control.getData().length() == length
														&& control.getData().charAt(countIndex) == rckey) {
													isTrue = true;
													over = true;
													score++;
												} else
													control = control.getNext();
											}
										} else if ((char) temp2.getRowName() != lett)
											isTrue = false;
										temp2 = temp2.getDown();
									}
									if (!isTrue) {
										score -= 2;
										if (turn == 1) {
											player1.setScore(player1.getScore() + score);
										} else
											player2.setScore(player2.getScore() + score);
										score = 0;
										direction = 0;
										cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.DARK_GRAY));
										cn.getTextWindow().setCursorPosition(px, py);
										System.out.print(".");
									}

								} else {
									char lett = puzzle[up][px - 1];
									boolean isFound = false;
									int length = 0;
									answer = "";
									Node temp = sll.getHead();

									while (temp != null && isFound == false) {
										if ((((Word) temp.getData()).getEnglish()).charAt(0) == lett) {
											if (((Word) temp.getData()).getX() == px - 1
													&& ((Word) temp.getData()).getY() == up
													&& ((Word) temp.getData()).getDirection() == 2) {
												answer = ((Word) temp.getData()).getEnglish();
												isFound = true;
												direction = 2;
												length = answer.length();
											}
										}
										temp = temp.getLink();
									}

									RowNode temp2 = mll.getHead();
									boolean over = false;
									while (temp2 != null && over == false) {
										if ((char) temp2.getRowName() == lett) {
											ColumnNode control = temp2.getRight();
											while (control != null && over == false) {
												if (control.getData().length() == length
														&& control.getData().charAt(countIndex) == rckey) {
													isTrue = true;
													over = true;
													score++;
												} else
													control = control.getNext();
											}
										} else if ((char) temp2.getRowName() != lett)
											isTrue = false;
										temp2 = temp2.getDown();
									}
									if (!isTrue) {
										score -= 2;
										if (turn == 1) {
											player1.setScore(player1.getScore() + score);
										} else
											player2.setScore(player2.getScore() + score);
										score = 0;
										direction = 0;
										cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.DARK_GRAY));
										cn.getTextWindow().setCursorPosition(px, py);
										System.out.print(".");
									}
								}

							}

							// Horizontal words controls
							else if (direction == 1) {
								String character = String.valueOf(rckey);
								character = character.toLowerCase();
								rckey = character.charAt(0);
								int left = px - 2;// Starting one point left from the written letter.
								int countIndex = 1;
								while (left != 1 && puzzle[py - 1][left] != '0') {// Heading
									// word
									left--;
									countIndex++;
								}

								if (left != 1) {
									countIndex--;
									char lett = puzzle[py - 1][left + 1];
									boolean isFound = false;// Is answer has found
									int length = 0;
									answer = "";
									Node temp = sll.getHead();
									while (temp != null && isFound == false) {
										if ((((Word) temp.getData()).getEnglish()).charAt(0) == lett) {
											if (((Word) temp.getData()).getX() == left + 1
													&& ((Word) temp.getData()).getY() == py - 1
													&& ((Word) temp.getData()).getDirection() == 1) {
												answer = ((Word) temp.getData()).getEnglish();
												isFound = true;
												length = answer.length();
												direction = 1;
											}
										}
										temp = temp.getLink();
									}

									RowNode temp2 = mll.getHead();
									boolean over = false;
									while (temp2 != null && over == false) {
										if ((char) temp2.getRowName() == lett) {
											ColumnNode control = temp2.getRight();
											while (control != null && over == false) {
												if (control.getData().length() == length
														&& control.getData().charAt(countIndex) == rckey) {
													isTrue = true;
													over = true;
													score++;
												} else
													control = control.getNext();
											}
										} else if ((char) temp2.getRowName() != lett)
											isTrue = false;
										temp2 = temp2.getDown();
									}
									if (!isTrue) {
										score -= 2;
										if (turn == 1) {
											player1.setScore(player1.getScore() + score);
										} else
											player2.setScore(player2.getScore() + score);
										score = 0;
										direction = 0;
										cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.DARK_GRAY));
										cn.getTextWindow().setCursorPosition(px, py);
										System.out.print(".");
									}

								} else {
									char lett = puzzle[py - 1][left];
									boolean isFound = false;
									int length = 0;
									answer = "";
									Node temp = sll.getHead();

									while (temp != null && isFound == false) {
										if ((((Word) temp.getData()).getEnglish()).charAt(0) == lett) {
											if (((Word) temp.getData()).getX() == left
													&& ((Word) temp.getData()).getY() == py - 1
													&& ((Word) temp.getData()).getDirection() == 2) {
												answer = ((Word) temp.getData()).getEnglish();
												isFound = true;
												length = answer.length();
												direction = 2;
											}
										}
										temp = temp.getLink();
									}

									RowNode temp2 = mll.getHead();
									boolean over = false;
									while (temp2 != null && over == false) {
										if ((char) temp2.getRowName() == lett) {
											ColumnNode control = temp2.getRight();
											while (control != null && over == false) {
												if (control.getData().length() == length
														&& control.getData().charAt(countIndex) == rckey) {
													isTrue = true;
													over = true;
													score++;
												} else
													control = control.getNext();
											}
										} else if ((char) temp2.getRowName() != lett)
											isTrue = false;
										temp2 = temp2.getDown();
									}
									if (!isTrue) {
										score -= 2;
										if (turn == 1) {
											player1.setScore(player1.getScore() + score);
										} else
											player2.setScore(player2.getScore() + score);
										score = 0;
										direction = 0;
										cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.DARK_GRAY));
										cn.getTextWindow().setCursorPosition(px, py);
										System.out.print(".");
									}
								}
							}
						}

					}

					// Controlling whether if the word has completed
					int controlUp = py - 1;
					int controlDown = py - 1;
					int controlLeft = px - 1;
					int controlRight = px - 1;
					boolean isCompleted = true;
					boolean over = false;
					boolean wordTrue = true;
					if (direction == 1) {
						while (puzzle[py - 1][controlLeft] != '0' && over == false) {
							if (puzzle[py - 1][controlLeft] == '1') {
								isCompleted = false;
								over = true;
							}
							controlLeft--;
						}
						while (puzzle[py - 1][controlRight] != '0' && over == false) {
							if (puzzle[py - 1][controlRight] == '1') {
								isCompleted = false;
								over = true;
							}
							controlRight++;
						}

						if (isCompleted == true) {
							int x = 0;
							int y = 0;
							Node temp = sll.getHead();
							while (temp != null) {
								if (((Word) temp.getData()).getEnglish() == answer) {
									x = ((Word) temp.getData()).getX();
									y = ((Word) temp.getData()).getY();
								}
								temp = temp.getLink();
							}

							while (puzzle[y][x] != '0') {
								if (puzzle[y][x] != solution[y - 1][x - 1].getLetter()) {
									puzzle[y][x] = '1';
									score -= 2;
									wordTrue = false;
									direction = 0;
									cn.getTextWindow().setCursorPosition(x + 1, y + 1);
									cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.DARK_GRAY));
									System.out.println('.');
									cn.getTextWindow().setCursorPosition(23, 0);
									cn.setTextAttributes(new enigma.console.TextAttributes(Color.PINK, Color.BLACK));
									System.out.print("Score==> ");
									cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.BLACK));
									System.out.print(player1.getName()+":"+player1.getScore()+"   "+player2.getName()+":"+player2.getScore());
									
								}
								x++;
							}
							
							isPressedLetter = false;
							if (!wordTrue) {
								if (turn == 1) {
									player1.setScore(player1.getScore() + score);
								} else
									player2.setScore(player1.getScore() + score);
								score = 0;
							}
						}

					} else if (direction == 2) {
						while (puzzle[controlUp][px - 1] != '0' && over == false) {
							if (puzzle[controlUp][px - 1] == '1') {
								isCompleted = false;
								over = true;
							}
							controlUp--;
						}
						while (puzzle[controlDown][px - 1] != '0' && over == false) {
							if (puzzle[controlDown][px - 1] == '1') {
								isCompleted = false;
								over = true;
							}
							controlDown++;
						}

						if (isCompleted == true) {
							int x = 0;
							int y = 0;
							Node temp = sll.getHead();
							while (temp != null) {
								if (((Word) temp.getData()).getEnglish() == answer) {
									x = ((Word) temp.getData()).getX();
									y = ((Word) temp.getData()).getY();
								}
								temp = temp.getLink();
							}

							while (puzzle[y][x] != '0') {
								if (puzzle[y][x] != solution[y - 1][x - 1].getLetter()) {
									puzzle[y][x] = '1';
									score -= 2;
									wordTrue = false;
									direction = 0;
									cn.getTextWindow().setCursorPosition(x + 1, y + 1);
									cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.DARK_GRAY));
									System.out.println('.');
									cn.getTextWindow().setCursorPosition(23, 0);
									cn.setTextAttributes(new enigma.console.TextAttributes(Color.PINK, Color.BLACK));
									System.out.print("Score==> ");
									cn.setTextAttributes(new enigma.console.TextAttributes(Color.GREEN, Color.BLACK));
									System.out.print(player1.getName()+":"+player1.getScore()+"   "+player2.getName()+":"+player2.getScore());
								}
								y++;
							}
							
							isPressedLetter = false;
							if (!wordTrue) {
								if (turn == 1) {
									player1.setScore(player1.getScore() + score);
								} else
									player2.setScore(player1.getScore() + score);
								score = 0;
							}

						}

					}
					if (wordTrue == true && isCompleted == true && direction != 0) {
						score += 10;
						wordcount+=1;
						String Eword = answer;
						String Tword = null;
						String Wronganswer1 = null;
						String Wronganswer2 = null;
						int number1 = 0;
						int number2 = 0;
						
						Random rnd = new Random();
						int number = rnd.nextInt(3);
						while (number1 == number2 || Wronganswer1 == Tword || Wronganswer2 == Tword) {
							number1 = rnd.nextInt(97);
							number2 = rnd.nextInt(97);
							Node tempsll = sll.getHead();
							Node tempsll1 = sll.getHead();
							Node tempsll2 = sll.getHead();
							while (tempsll != null) {
								if (((Word) tempsll.getData()).getEnglish().equals(Eword)) {
									Tword = ((Word) tempsll.getData()).getTurkish();
								}
								tempsll = tempsll.getLink();
							}
							for (int i = 0; i < number1; i++) {
								tempsll1 = tempsll1.getLink();
							}
							Wronganswer1 = ((Word) tempsll1.getData()).getTurkish();
							for (int i = 0; i < number2; i++) {
								tempsll2 = tempsll2.getLink();
							}
							Wronganswer2 = ((Word) tempsll2.getData()).getTurkish();
						}
						cn.getTextWindow().setCursorPosition(0, 27);
						cn.setTextAttributes(new enigma.console.TextAttributes(Color.CYAN, Color.BLACK));
						System.out.println("What is the meaning of " + Eword + " in Turkish?");
						if (number == 0) {
							System.out
									.print("a." + Tword + "    " + "b." + Wronganswer1 + "    " + "c." + Wronganswer2);

						} else if (number == 1) {
							System.out
									.print("a." + Wronganswer1 + "    " + "b." + Tword + "    " + "c." + Wronganswer2);

						} else if (number == 2) {
							System.out
									.print("a." + Wronganswer1 + "    " + "b." + Wronganswer2 + "    " + "c." + Tword);

						}
						System.out.println();
						System.out.print("Answer: ");
						Scanner input1 = new Scanner(System.in);
						String ans = input1.next();
						ans = ans.toLowerCase();
						if (number == 0 && ans.equals("a")) {
							cn.setTextAttributes(new enigma.console.TextAttributes(Color.CYAN, Color.BLACK));
							if (turn == 1)
								System.out.println("Answer is correct. Player1 gets extra 10 points");
							else
								System.out.println("Answer is correct. Player2 gets extra 10 points");
							score += 10;
						} else if (number == 1 && ans.equals("b")) {
							cn.setTextAttributes(new enigma.console.TextAttributes(Color.CYAN, Color.BLACK));
							if (turn == 1)
								System.out.println("Answer is correct. Player1 gets extra 10 points");
							else
								System.out.println("Answer is correct. Player2 gets extra 10 points");
							score += 10;
						} else if (number == 2 && ans.equals("c")) {
							cn.setTextAttributes(new enigma.console.TextAttributes(Color.CYAN, Color.BLACK));
							if (turn == 1)
								System.out.println("Answer is correct. Player1 gets extra 10 points");
							else
								System.out.println("Answer is correct. Player2 gets extra 10 points");
							score += 10;
						} else
							System.out.println("Wrong answer");
						
						if (turn == 1) {
							player1.setScore(player1.getScore() + score);
						} else
							player2.setScore(player2.getScore() + score);
						score = 0;
						direction = 0;
						cn.getTextWindow().setCursorType(1);
						cn.getTextWindow().setCursorPosition(px, py);
						Node temp=sll.getHead();
						while(temp!=null)
						{
							if(((Word)temp.getData()).getEnglish().equals(answer))
							{
								((Word)temp.getData()).setCompleted(true);
							}
							temp=temp.getLink();
						}
						sll.display();
					}
					
				}
				
				if(isPressedLetter == true && isPressedDirect == false && !isTrue) {
					isPressedLetter = false;
					if (turn == 1)
						turn = 2;
					else
						turn = 1;
				}
//				cn.getTextWindow().setCursorPosition(110, 3);
//				System.out.println(isTrue);
				isPressedDirect = false;
				if(wordcount==finishcount)
				{
					dll.add(player2);
					dll.add(player1);
					dll.Display();
					cn.getTextWindow().setCursorPosition(25, 70);
					System.out.println("Game Over");
					break;
				}
				keypr = 0; // last action
				
			}

			Thread.sleep(20);
		}
	}
}

