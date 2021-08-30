import java.util.LinkedList;
import java.util.Scanner;

public class MainArea {

	public static void main(String[] args) {
		//game rules, if guesses = 6, game over
		boolean gameLoop = true;
		boolean guessLoop = true;
		int guesses = 0;
		String choice1;
		String choice2 = "";
		String wordString = "";
		Scanner myObj = new Scanner(System.in);
		
		LinkedList<String> list = new LinkedList<String>();
		
		while(gameLoop) {
			//gameMenu
			System.out.print("------------ Main Menu ------------- \nNew Game => (1) \nQuit => (2) \n\nI Choose: ");
			choice1 = myObj.next();
			
			if(choice1.equals("1")){
				fillList(list);
				
				String wordChoice = getRandomWord();
				System.out.println("\n*********************************************************************************************");
				
				guesses = 0;
				guessLoop = true;
				
				wordString = initialMenu(guesses, list, wordChoice);
				
				while(guessLoop) {
					//check if word was solved
					if(!wordString.contains("-")) {
						System.out.println("You win!\n\n");
						guessLoop = false;
					}
					else {
						System.out.print("Enter a letter or word here to guess the hidden word (guesses left = " + (6 - guesses) +"): ");
						choice2 = myObj.next();
						
						if(choice2.length() > 1 ) {
							//a word was guessed
							//guess word function set to a bool var
							if(choice2.toLowerCase().equals(wordChoice.toLowerCase())) {
								System.out.println("You win!\n\n");
								guessLoop = false;
							}
							else {
								guesses++;
							}
						}
						else {
							//a letter was guessed
							deleteFromList(list, choice2);
							
							if(wordChoice.contains(choice2)) {
								System.out.println("Correct Guess, next call function to fill in blank correctly\n\n");
								
							}
							else {
								guesses++;
							}
							
							wordString = loopMenu(wordString, guesses, list, wordChoice, choice2, guessLoop);
							System.out.println(wordString + " (" + wordChoice.length() + " Letters)");
						}
						
						if(guesses == 6) {
							System.out.println("*****************************************************************************************");
							System.out.println(getPicture(guesses));
							System.out.println("You lose! Thanks for playing! :)");
							guessLoop = false;
						}
					}
				}
				
			}
			else if(choice1.equals("2")){
				System.out.println("Thanks for playing! \n");
				gameLoop = false;
				myObj.close();
			}
			else {
				System.out.println("Invalid choice. Please choose one of the options above.");
			}
		}
	}
	
	public static String initialMenu(int guesses, LinkedList<String> list, String wordChoice) {
		System.out.println("------- Try to guess the word by typing in a letter at a time or guessing the full word at once.  ----------");
		System.out.println(getPicture(guesses));
		System.out.println("Letters Remaining: " + printList(list));
		System.out.print("Your Word: ");
		System.out.println(getWordBlanks(wordChoice.length()) + " (" + wordChoice.length() + " Letters)");
		
		return getWordBlanks(wordChoice.length());
	}
	
	public static String loopMenu(String wordString, int guesses, LinkedList<String> list, String wordChoice, String choice2, boolean guessLoop) {
		System.out.println("------- Try to guess the word by typing in a letter at a time or guessing the full word at once.  ----------");
		System.out.println(getPicture(guesses));
		System.out.println("Letters Remaining: " + printList(list));
		System.out.print("Your Word: ");
		
		
		for(int i = 0; i < wordChoice.length(); i++) {
			if(wordChoice.charAt(i) == choice2.charAt(0)) {
				wordString = wordString.substring(0,i) + choice2 + wordString.substring(i+1);
			}
		}
		
		return wordString;
	}

	public static String getRandomWord() {
		String[] words = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen"};
		int min = 1;
		int max = words.length;
		int range = max - min;
		int choice = (int) (Math.random()*range) + min;
		
		return words[choice];
	}
	

	public static String printList(LinkedList<String> list) {
		String lettersLeft = "";
		
		for(int i = 0; i < list.size(); i++) {
			lettersLeft = lettersLeft + " " + list.get(i);
		}

		return lettersLeft;
	}
	
	public static LinkedList<String> deleteFromList(LinkedList<String> list, String letter) {
		list.removeIf(j -> j.equals(letter));
		
		return list;
	}
	
	public static String getWordBlanks(int wordLength) {
		String blanks = "";
		for(int i = 0; i < wordLength; i++) {
			blanks = blanks + "-";
		}
		
		return blanks;
	}
	
	public static LinkedList<String> fillList(LinkedList<String> list){
		//empty list
		list.clear();

		//fill list
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		list.add("h");
		list.add("i");
		list.add("j");
		list.add("k");
		list.add("l");
		list.add("m");
		list.add("n");
		list.add("o");
		list.add("p");
		list.add("q");
		list.add("r");
		list.add("s");
		listt.add("t");
		list.add("u");
		list.add("v");
		list.add("w");
		list.add("x");		
		list.add("y");
		list.add("z");
		
		return list;
	}
	
	public static String getPicture(int guesses) {
		String[] picture = {"___", "|", "|  |", "-----", "\\", "/", "0"};
		String gallowsRow1 = "   " + picture[0];
		String gallowsRow2 = "\n   " + picture[2];
		String gallowsRow3 = "\n   " + picture[1];
		String gallowsRow4 = "\n   " + picture[1];
		String gallowsRow5 = "\n   " + picture[1];
		String gallowsRow6 = "\n " + picture[3];
		
		switch(guesses) {
	      case 0:
		    //guess 0 -> default
		    break;
		  case 1:
		    //guess 1 -> head
			  gallowsRow3 = "\n   " + picture[1] + "  " + picture[6];
		    break;
		  case 2:
			//guess 2 -> torso
			  gallowsRow3 = "\n   " + picture[1] + "  " + picture[6];
			  gallowsRow4 = "\n   " + picture[1] + "  " + picture[1];
			break;
		  case 3:
			//guess 3 -> left leg
			  gallowsRow3 = "\n   " + picture[1] + "  " + picture[6];
			  gallowsRow4 = "\n   " + picture[1] + "  " + picture[1];
			  gallowsRow5 = "\n   " + picture[1] + " " + picture[5];
			break;
		  case 4:
			//guess 4 -> right leg
			  gallowsRow3 = "\n   " + picture[1] + "  " + picture[6];
			  gallowsRow4 = "\n   " + picture[1] + "  " + picture[1];
			  gallowsRow5 = "\n   " + picture[1] + " " + picture[5] + " " + picture[4];
			break;
		  case 5:
			//guess 5 -> left arm
			  gallowsRow3 = "\n   " + picture[1] + " " + picture[4] + picture[6];
			  gallowsRow4 = "\n   " + picture[1] + "  " + picture[1];
			  gallowsRow5 = "\n   " + picture[1] + " " + picture[5] + " " + picture[4];
			break;
		  case 6:
			//guess 6 -> right arm
			  gallowsRow3 = "\n   " + picture[1] + " " + picture[4] + picture[6] + picture[5];
			  gallowsRow4 = "\n   " + picture[1] + "  " + picture[1];
			  gallowsRow5 = "\n   " + picture[1] + " " + picture[5] + " " + picture[4];
			break;
		}

		return gallowsRow1 + gallowsRow2 + gallowsRow3 + gallowsRow4 + gallowsRow5 + gallowsRow6;
	}

}

/*
 Reference Picture
Row1   ___
Row2   |  |
Row3   | \0/
Row4   |  |
Row5   | / \
Row6 -----
*/
