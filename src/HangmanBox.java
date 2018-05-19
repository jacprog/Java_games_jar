
/*

Group Hangman

//Jacques Favre
//Kyle Bess
//William Caldwell
April-7-2018
COP2552.001
Project 3 Yahtzee GUI
Java
Version 1.8
JavaFx GUI interface


alert box and tutorial for clean close from here: https://github.com/buckyroberts/Source-Code-from-Tutorials/blob/master/JavaFX/005_creatingAlertBoxes/AlertBox.java





Rounds will roll 5 dices. For 10 times. That is 30 rolls.

10 rounds with 3 rolls
hold and roll remaining
scoring
read and print to file



top: 	buttonBox top for buttons
left: buttons
center: image
right: keybord 
bottom:	 instruction



Order of events
Start programm, display window to get name, get name
get and read file, 
find name (EXTRA 5 POINT)
start game
word 1, gessing game, get and check letter, count till won or lost round
.
.
.
.
last word
get score 
write to file name and score, group by name


cosmetics:
instructions
position of buttons
color and font buttons
improve key board, go to qwerty
comments


*/




import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

//import HangmanBox.letterBox; //inner classs
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

class HangmanBox extends BorderPane{
	
	boolean isFile = false;// if no word file
	boolean disable = false;
	boolean play = true;
	int count = 0;// count number of misses 11 images so 11 miises is a LOST
	int totalMisses = 0;
    int counting = 0;// number of words in file
    int c = 0;// number of words loded in getAword
	String name = "";// get player name
	
	// label
	Label label1 = new Label("Press Play to start the Hangman game");//for one roll score
//	label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	Label label2 = new Label("Welcome");//for one roll score
	Label label3 = new Label("Results.");//for one roll score
	Label label4 = new Label("Results.");//for one roll score
	
	
	//The word array that will allow us to choose a word;
//	String[] wordsToChoose = loadWords();
//	String[] wordsToChoose = new String[];
	ArrayList<String> wordsToChoose = new ArrayList<String>();
//	wordsToChoose.add("ONE");
//	String[] defaultArray = {"PLEASANT", "TILTED", "JOCKEY", "TESTING"};
	
	ArrayList<String> wordsOut = new ArrayList<String>();//from text file
	
	ArrayList<String> names = new ArrayList<String>();//from text file
	
	ArrayList<Integer> scores = new ArrayList<Integer>();//from text file
	
	private letterBox[] lblletter = new letterBox[28];
	private char[] letters = {'A', 'B', 'C', 'D', 'E',
		    'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
		    'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '-'};//Notice two extra to make 28, empty string and - 
	//Word to guess
//	String wordGuess = getAWord(wordsToChoose);
	String wordGuess = "";
	//The hidden word string
	StringBuilder guessedWord = new StringBuilder();
	
	
	
	Label guessWord = new Label(guessedWord.toString());
	
//	String  tryLetter = "";//letter pick on mouse click
//	  int choice = 0;// int for index keyboard and array letters
	
	 //buttons
	 Button btStart = new Button(" PLAY ");//button at TOP  on
	 Button btPlay = new Button("RE-START");//button at TOP  on
	 Button btExit = new Button("EXIT");
	 
	 

	    Pane pane1 = new Pane(); // pane for picture
	    
	   
	    
	    //Hides the word
	    //Used for starting a new game
	    public void hideWord(){
	    	
//	    	if ( counting > c + 1) {
//	    		play = false;
//	    		 System.out.println("312 LAST WORD: " );
//	    		label1.setText("Good Job, you got THEM ALL! press start for a new ROUND.");
	    	System.out.println("217-------------------------WORD to guess: " + wordGuess);// PRINT TO CONSOLE TESTT
	    	
	    		
//	    	}
//	    	else {
	    	int wLength = wordGuess.length();
	    	
	    	
	    	for(int i = 0; i < wLength; i++){
	    		guessedWord.append("*");
	    	}
	    	guessWord.setText(guessedWord.toString());
	    	
	    	
//	    	}
	    	
	    }
	    
	    
	    // Update word
	    public void updateWord(){
	    	guessWord.setText(guessedWord.toString());
	    }
	    
	    
	    public void resetLetters(){
	    	for(int i = 0; i < lblletter.length; i++)
	    		lblletter[i].reset();
	    }
	    
	    
	    public void guessLetter(char letter){
	    	

	    	
	    	
	    	
	    	
	    	
//		    if(play) {
//		    else {
	    	
	    	
	    	//letter is in the word
	    	if(wordGuess.indexOf(letter) >= 0){
	    		//has the letter been guessed?
	    		//If not load the letters in
	    		if(!(guessedWord.indexOf(letter + "") >= 0)){
	    			int k = wordGuess.indexOf(letter);
	    			while(k >= 0){
	    				
	    				guessedWord.setCharAt(k, letter);
	    				k = wordGuess.indexOf(letter, k + 1);
	    			}
	    			if(guessedWord.indexOf("*") < 0){
	    				//No more * in the word
	    				//It's a win!
	    				label1.setText("Good Job, you got it! press PLAY for next word.");
	    		//		btPlay.setDisable(false);// 4/18 jacques added heree
	    				btStart.setDisable(false);// 4/18 jacques added heree
	    				totalMisses = totalMisses + count;
	    				
	    				label3.setText("You missed:" + count + " times for this word. Total for the game: " + totalMisses);
	    				 deactivateLetters();//4/18 jacques added 
	    		//		resetLetters();
	    		//		guessedWord.delete(0, guessedWord.length());
	    		//		wordGuess = getAWord(wordsToChoose);
	    		//		hideWord();
	    		//		count = 0;
	    		//		output(count, pane1);
	    				
	    			}
	    		}
	    		else{
	    			//If it's already a letter that's been guessed
	    			System.out.println("Letter already exists");
	    		}
	    	}
	    	//letter is not in the word
	    	else{
	    		count++;// count missed
	    		output(count, pane1);
	    		
	    		System.out.println("COUNT missed: " + count);// PRINT TO CONSOLE TESTT
	    		
	    		if (count >= 10) {
	    			label1.setText(wordGuess +" was the word. Sorry, you lost! Press start for next word.");// end of game message moved to scoreGame function
	    		 deactivateLetters();//4/18 jacques added 
	 //   		 btPlay.setDisable(false);// 4/18 jacques added heree
 				btStart.setDisable(false);// 4/18 jacques added heree	
 				totalMisses = totalMisses + count;
 				label3.setText("You missed:" + count + " times for this word. Total for the game: " + totalMisses);
	    		}
//	    		label3.setText("You missed:" + count + " times for this word. Total for the game: " + totalMisses);	
	    	}
	    	updateWord();// update hidden word
	    	

//	    	label3.setText("You missed:" + count + " times for this word. Total for the game: " + totalMisses);
	    	
//		    } // end else
	    }
	    
/*	    
	    // Pick random word in aaray
	    public String getAWord(ArrayList<String> wordsToChoose2){
//	    	public String getAWord(ArrayList<String> wordsToChoose2){	
	    	
			int numOfWords = wordsToChoose2.size();
			Random randNum = new Random();
			
			int choice = randNum.nextInt(numOfWords);
//			int choice = 0;
			System.out.println(" 281word choice array index: " + choice);
			System.out.println("282word choice array index: " + wordsToChoose2.size());
			
//			String word = wordsToChoose2.get(0);
			return wordsToChoose2.get(choice);
		}
*/		
	    
	    public String getAWord(ArrayList<String> wordsToChoose2) throws IOException{
//	    	int c = 0;
	    
//	    java.util.Collections.shuffle(wordsToChoose2);
	    c = wordsToChoose2.size() ;
	    System.out.println("NUMber of  WORDS : " + c );
//	    int counting = 0;
//		int i = counting;
	    String word = "";
	  
	    System.out.println("340 WORD to guess: " + wordGuess);// PRINT TO CONSOLE TESTT
    	System.out.println("341 get a word Counting: " + counting );	
    	System.out.println("342 NUMber of  WORDS : " + c );	
	    
	    		  
	    		 
	    if (counting > c -1)	{// END OF GAME//4/18 jacques added 
	    	 System.out.println("348 LAST WORD: " );
	    	 System.out.println("Good Job, you got them ALL! press start for a new GAME.");
	    	label4.setText("Good Job, you got them ALL! press RE-START for a new Game.");
	    	loadWords();
	    	deactivateLetters();//4/18 jacques added 
	    	btPlay.setDisable(false);//4/18 jacques added 
	    	btStart.setDisable(true);//4/18 jacques added 

//	    	activateLetters();
//			startRound();
	    	newGame();
	    	counting = 0;
	    	allScoreFile(); //
	    }
	    else {
	    	 System.out.println("353 NEXT WORD: " + word);
	    	word = wordsToChoose2.get(counting);// pull a word out of array of word
	    }
	    counting ++;
		return word;
	    
	    }   
	
private void newGame() {
	// TODO Auto-generated method stub
	 System.out.println("NEW GAME FUNCTION" );
	label4.setText("Good Job, you got THEM ALL! press start for a new Game.");
//	btStart.setDisable(false);//4/18 jacques added 
}


/******  Constructor  
 * @throws IOException *****************/	
	public HangmanBox() throws IOException	{
		// 
		
		
		wordsToChoose.add("ONE");    	// default word if file not found
		wordsToChoose.add("TWO"); 
/*		wordsToChoose.add("THREE"); 
		wordsToChoose.add("ADDED");    	// default word if file not found
		wordsToChoose.add("WORDS"); 
		wordsToChoose.add("JUST"); 
		wordsToChoose.add("IN");    	// default word if file not found
		wordsToChoose.add("CASE"); 
		wordsToChoose.add("NO"); 
		wordsToChoose.add("FILE"); 
		wordsToChoose.add("FOUND"); 
		
		
*/		 
		java.util.Collections.shuffle(wordsToChoose);
		
		
//		HBox title = new HBox();
//		this.setTop(title);
//		title.getChildren().addAll(label1);
//		label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));		
//		  title.setPadding(new Insets(20, 20, 20, 20));
//		  title.setAlignment( Pos.CENTER);
		
		//VBox for buttons
	//	   VBox buttonPane = new VBox(200);
	//	  this.setLeft(buttonPane);
		   HBox buttonPane = new HBox(350); 
		   this.setTop(buttonPane);
	 	   buttonPane.setPadding(new Insets(0, 20, 60, 20));
//	 	   buttonPane.getChildren().addAll(btStart, btPlay, btExit);
	 	  buttonPane.getChildren().addAll(btStart, btPlay);
	 	   buttonPane.setAlignment( Pos.CENTER);	
	 	  this.setStyle("-fx-background-color: gainsboro");//lightgrey
	 	 
	 	  
	 	  //Box for hangman  image and word to guess
	// 	  VBox imagePane = new VBox(20);// distance between picture and word to guess
	 	 HBox imagePane = new HBox(50);
//	 	 this.setPadding(new Insets(0, 0, 0, 0));
	 	 this.setCenter(imagePane);
	 	imagePane.setMinHeight(150);// 
	 	imagePane.setMinWidth(300);// 
	 	imagePane.setStyle("-fx-background-color: wheat");//lightgrey
	// 	imagePane.setPadding(new Insets(40, 100, 40, 105));// 100 105 set position picture hangman
	 	imagePane.setPadding(new Insets(30, 20, 20, 40));// 100 105 set position picture hangman
	 	
	 	output(0, pane1);// this call image hangman
	 	imagePane.setAlignment( Pos.CENTER);
	 
	 	imagePane.getChildren().addAll(pane1);// this place imge
	 	imagePane.getChildren().addAll(guessWord);// label for word to guess ***********
	 	guessWord.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	 	
	 	
	 	//Message below game
	 	 VBox textPane = new VBox(20);
	 	 this.setBottom(textPane);
	 	textPane.setPadding(new Insets(40, 20, 20, 20));
	 	textPane.getChildren().addAll(label2);// this place label for message
	 	label2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	 	textPane.getChildren().addAll(label1);// this place label for message
	 	label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	 	textPane.setAlignment( Pos.CENTER);
	 	textPane.getChildren().addAll(label3);// this place label for message
	 	textPane.getChildren().addAll(label4);// this place label for message
	 	label4.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
//	 	btHit.setDisable(true);

	 	
	 	// box for letters
	 	
	 	GridPane letterPane = new GridPane();
//	 	this.setPadding(new Insets(0, 0, 0, 0));
	 	this.setRight(letterPane);
	 	this.setStyle("-fx-background-color: darkgrey");
	 	 letterPane.setPadding(new Insets(0, 0, 0, 20));
	 	letterPane.setAlignment(Pos.CENTER);  
	    letterPane.setHgap(10);
	    letterPane.setVgap(10);
	 // Create labels for letters
	    for (int i = 0; i < 28; i++) {

	    	lblletter[i] = new letterBox(letters[i]);
		    lblletter[i].setTextAlignment(TextAlignment.CENTER);
	        letterPane.add(lblletter[i], i % 7, i / 7);//set up letterPane grid with col and row, 7 col, 7 row
	        lblletter[i].setText(letters[i] + "");
	        lblletter[i].setStyle("-fx-background-color: lightgrey");
	        lblletter[i].setMinHeight(60);
	        lblletter[i].setMinWidth(60);
	        lblletter[i].setPadding(new Insets(20, 20, 20, 20));
	        lblletter[i].setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 16));
	        
	      }
	    btPlay.setDisable(true);
  
	    //start or start new word reset and get word
	    //PLAY
	    btStart.setOnAction(e -> {
//			    loadWords();
			    	//Kyle 4/18 activateLetters function sets all the letter boxes playing boolean to true
			    activateLetters();
			    try {
					startRound();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btStart.setDisable(true);// 4/18 jacques added heree
//				startRound();
//				btPlay.setDisable(true);
//				label3.setText("You missed:" + count + " times for this word. Total for the game: " + totalMisses);
		});
	    //REPLAY
	    btPlay.setOnAction(e -> {// replay
	    	loadWords();
			    	//Kyle 4/18 activateLetters function sets all the letter boxes playing boolean to true
			    
	    	btStart.setDisable(false);// 4/18 jacques added heree
				btPlay.setDisable(true);// 4/18 jacques added heree
				label1.setText("Press Play to start guessing!");
				label4.setText("Score Is: " + totalMisses);
				totalMisses = 0;
		});
	    
/*	    
	    // call a closeProgram method to  save files than close
	    btExit.setOnAction(e -> {
	
	    	try {
				closeProgram();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		});// call a closeProgram method to  save files thaen close
*/		
		
		
	    //Event handler, close window, 2 ways of closing
	    //Event handler, close window THIS IS NOT EXIT BUTTON!!!!
	    //https://www.youtube.com/watch?v=ZuHcl5MmRck
//	    pane.setOnCloseRequest(e -> {
//	    	e.consume();					// consume to override
//	    	try {
//				HangmanBox.closeProgram();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//	    });

	    //Calling Set-Up functions
	    

	    startGame();// get name// get window to get name
	    
//	    hideWord();
	    
	    loadWords();
	    processFile();
	 	
	}//End constructor
/******************************************************/
	//Kyle 4/18 added this function to disable/enable the letterboxes
	//Activate letters will set the playing value to true
	//This will allow the player to manipulate the keyboard
	void activateLetters(){
		for(int i = 0; i < lblletter.length; i++)
    		lblletter[i].setPlaying(true);
	}
	//Deactivate letters sets the playing value to false
	//This will no longer allow access to the keyboard
	//Should be used between rounds
	private void deactivateLetters(){
		for(int i = 0; i < lblletter.length; i++)
    		lblletter[i].setPlaying(false);
	}
	
	
	void startRound() throws IOException {
		System.out.println(" STARTING");
		System.out.println("Counting in Start: " + counting );	
		// TODO Auto-generated method stub
		System.out.println(" IS FILE" + isFile);
		
	    updateWord();
		resetLetters();
		guessedWord.delete(0, guessedWord.length());
		
		if (isFile) {
			wordGuess = getAWord(wordsOut);
		}
		else if (!isFile){
			wordGuess = getAWord(wordsToChoose);
		}
//		getAWord(wordsToChoose);
//		wordGuess = getAWord(wordsToChoose);
//		wordGuess = getAWord(wordsOut);
		hideWord();
		count = 0;
		output(count, pane1);
		label1.setText("Use on-screen keybord to play!");// end of game message// moved to scoreGame function



	}
	
	
	
	public void closeProgram() throws IOException {

//		allScoreFile();// save files
		System.out.println(" Clean close");
//		System.exit(0);
		allScoreFile();// files at end of game
	}
	
	


	
	public void loadWords(){
		
		System.out.println("loading");
		
		wordsOut.clear();//4/18 jacques added
//		File wordFile = new File("words.txt");
		int wordCount = 0;
		
		//Does the word file exist?
//		if(wordFile.exists()){
			try{
				System.out.println("loading");
				isFile = true;

				Scanner inFile = new Scanner(new File("words.txt"));
//				 while (fileInput.hasNext()) {// counts words, not line  !!!white space!!!
				while(inFile.hasNext()){
					String word = inFile.nextLine().toUpperCase();
//					String word.toUpperCase():
					wordsOut.add(word);
					wordCount++;
//					inFile.nextLine();
//					System.out.println("wordCount" + wordCount);
					java.util.Collections.shuffle(wordsOut);

//					word.toUpperCase():

					
					
				}
				inFile.close();

				
				System.out.println("wordsOut" + wordsOut);
			}
			catch(Exception e){
			//	No File Found
				isFile = false;

				//We shouldn't get this error, but just in case
				System.out.println("No File Found");

			}

	}//endloadwords
	
	
	
	//to get name, to get file 
	public void startGame() { 

		name = getName();//get name in new window to avoid crownding main window
		
		System.out.println("Name in start: " + name);
		
	}
	
	
	private String getName() {//call a new window get and validate name, try, catch very basic
		// TODO Auto-generated method stub

		//open a window to get name
		name = nameBox.display("Welcome to the Hangman", "Enter your name to start the game, \n"
				+ "Press Enter\nOr simply press Enter to be unkwnon! ");// call a  window to enter name
		
		return name;
	}		
	
	
	
	// key bord stuff
	class letterBox extends Label{

		char lValue;// letter selected bu keybord press mouse
		boolean used = false;
		//Kyle 4/18 this variable (playing) dictates whether the player can or cannot interact with the board
		boolean playing = false;
		//function to get letter
		letterBox(char letter){
			lValue = letter;
			
			// to get letter value on click on keyboard
			this.setOnMousePressed(e -> {
				//Kyle 4-18 added the playing variable
				if(!used && playing){
					
					System.out.println(this.lValue);// Print letter to console
					used = true;
					this.setStyle("-fx-background-color: grey");
					guessLetter(this.lValue);
				}
			});
//			if(disable) {
//				System.out.println("disable " + disable);	
				
//			this.setDisable(true);
//			}
//			else {
//				this.setDisable(false);	
//			}
			//change to darker color on mouse hover
			this.setOnMouseEntered(e -> {
				//Kyle 4-18 added the playing variable
				if(playing)
					this.setStyle("-fx-background-color: grey");
			});
			//color back to normal
			this.setOnMouseExited(e -> {
				//Kyle 4-18 added the playing variable
				if(!used && playing)
					this.setStyle("-fx-background-color: lightgrey");
			});
		}
		public void reset(){
			used = false;
			this.setStyle("-fx-background-color: lightgrey");
		}
		//Kyle 4/18 I don't plan for this function to be used a whole lot
		//This function will flip whatever the playing value is
		public void togglePlaying(){
			if(!playing)
				this.playing = true;
			else
				this.playing = false;
		}
		//Kyle 4/18 This is probably the most reliable way to set when the keyboard can be used
		//just call the function with (true) or (false)
		public void setPlaying(boolean t){
			this.playing = t;
		}
	}//class letter box






public void output(int X, Pane pane) {// match variable image with an URL
    Image di0 = new Image("image/hangman0.gif");
    Image di1 = new Image("image/hangman1.gif");
    Image di2 = new Image("image/hangman2.gif");
    Image di3 = new Image("image/hangman3.gif");
    Image di4 = new Image("image/hangman4.gif");
    Image di5 = new Image("image/hangman5.gif");
    Image di6 = new Image("image/hangman6.gif");
    Image di7 = new Image("image/hangman7.gif");
    Image di8 = new Image("image/hangman8.gif");
    Image di9 = new Image("image/hangman9.gif");
    Image di10 = new Image("image/hangman10.gif");
  
	

      if(X==0) {pane.getChildren().add(new ImageView(di0));}// match number and image
      if(X==1) {pane.getChildren().add(new ImageView(di1));}
      if(X==2) {pane.getChildren().add(new ImageView(di2));}
      if(X==3) {pane.getChildren().add(new ImageView(di3));}
      if(X==4) {pane.getChildren().add(new ImageView(di4));}
      if(X==5) {pane.getChildren().add(new ImageView(di5));}
      if(X==6) {pane.getChildren().add(new ImageView(di6));}
      if(X==7) {pane.getChildren().add(new ImageView(di7));}
      if(X==8) {pane.getChildren().add(new ImageView(di8));}
      if(X==9) {pane.getChildren().add(new ImageView(di9));}
      if(X==10) {pane.getChildren().add(new ImageView(di10));}
	}//end output

/******************************************************* READ NAME FILE ****************************************/
//read txt file
		void processFile() throws IOException {
			System.out.println(" Reading names" );
		//at start of game, read file, get top ten, create a string with top ten scores, display those
			// get file and .....
			
			//READ file for processing and ordering, but first read file, do some sorting  and  write file
			 // Create a File instance
//		    java.io.File file = new java.io.File("bestScore.txt");
			StringBuilder topTen = new StringBuilder();
//			int countTopten = 0;
		    java.io.File file = new java.io.File("names.txt");
			if(!file.exists()){// IF no file, create one
		  	   file.createNewFile();
		  	}

		    try (Scanner s = new Scanner(file); ) {// Create a Scanner for the file
		      // Read data from a file

		    	int ctrNames = 0; //count names
	    		while(s.hasNext()) {//fir
	    			ctrNames++;
	    			int num = s.nextInt();// split score and date for sorting
	    			String word = s.nextLine().toUpperCase();
			      					
//					String word.toUpperCase():
					names.add(word);// add to name list
					scores.add(num);
				   
					      }//end  while, finished reading
	    		System.out.println("NAMEonFILE: " + names);//list

	    		System.out.println("NAME in file process: " + name);// name input	 
	    		
	    		
				String name = "NEW";
	    		
	    		String check = (" " + name);
	    		
	    		System.out.println("NAME in file process: "	+ names.contains(check));// return true or false
	    		
	    		
	    		
				if ( names.contains(check)) {// if name exist in file, push name in label and add score using index of name
					names.indexOf(check);
					label2.setText("Welcome back " + name + ", your best score is: " + scores.get(names.indexOf(check)));// end of game message// moved to scoreGame function
				}       
	
					       
		    }//end try reading
	

		
	}
		/*************************************  END OF GAME FILING*******************************************/	    

	//this adds to existing file or create file if not one
		private void allScoreFile() throws IOException {
			System.out.println("NAME in file OUT: "	+ name);// check for name
			System.out.println("SCORE  in file OUT: "	+ totalMisses);// check for name
			
			
			// TODO Auto-generated method stub
//			java.util.Date date = new java.util.Date(); //create date object
			StringBuilder sb = new StringBuilder();

			//READ file for processing and ordering, but first read file, add new latest score, ovewrite file
			 // Create a File instance
		    java.io.File file = new java.io.File("names.txt");
			if(!file.exists()){// IF no file, create one
		  	   file.createNewFile();
		  	}
		  //APPEND to file

			FileWriter fr = null;
			BufferedWriter br = null;
			PrintWriter pr = null;
			try {
				// to append to file, you need to initialize FileWriter using below constructor
				fr = new FileWriter(file, true);
				br = new BufferedWriter(fr);
				pr = new PrintWriter(br);
				pr.print(totalMisses + " "); //add sccore
//				pr.println(date);		//add date
				pr.println(name);		//add name
			}
		    catch (IOException e) {
				e.printStackTrace();
			}
		    finally {
				try {
					pr.close();
					br.close();
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//END APPEND
			
			    //  Get scores in order, need to pass nothing, read from file
			    topScoreFile();// call sorting function for Top Scores
			
		}// end allScorefile()
		/********************************************************** Then: The sorting  **********************************/
		private static void topScoreFile() throws IOException {
			// TODO Auto-generated method stub
			StringBuilder sbOut = new StringBuilder();// string builder for top scores
			
			//READ file for processing and ordering, but first read file, do some sorting  and  write file
				 // Create a File instance
//			    java.io.File file = new java.io.File("bestScore.txt");
			    java.io.File file = new java.io.File("names.txt");
				if(!file.exists()){// IF no file, create one
			  	   file.createNewFile();
			  	}

			    try (Scanner s = new Scanner(file); ) {// Create a Scanner for the file
			      // Read data from a file

			    	
			    	
			    	int ctr = 0;
			    		while(s.hasNext()) {//first while loop to count element to set up size of array idea from: https://www.youtube.com/watch?v=k9pf8KKPcwI
			    			ctr++;//counter for size of arrays
			    			s.nextInt();
			    			s.nextLine();
//			    			 System.out.println("ctr"+ ctr);
			    		}
			    		//now I know the size, I can create array, ctr is the size
			    		int[] scorelist = new int[ctr];
			   		String[] datelist = new String[ctr];
			   			Scanner s1 = new Scanner(file);
			  					int k= 0;//to count number of entries...Think I need only one of theses?

			  					
						       while (s1.hasNext()) {//second while loop to read and pass int score and string date

						        int score1 = s1.nextInt();// split score and date for sorting
						         String date1 = s1.nextLine();

									 scorelist[k] = score1;// array of score for sorting
									 datelist[k] = date1;//array of date to follow score

							         k++;
		 
						      }//end second while, finished reading

						       
						    	   //sorting and processing....find smaller score, append date with same index, pass it , find the next smaller score...
						    	   //selection sort.java p 270// Fliped max to min!!!!!!
						    	   for (int i = 0; i < scorelist.length; i++) {//??CHANGE HERE took off length - 1 !!!!!! IF only 1 in array nothing appens
						    		      // Find the maximum in the list[i..list.length-1]NOW find MAX
						    		      int currentMAX = scorelist[i];// start with max is element 1, TRUE if only 1 element
						    		      		String dateMAX = datelist[i];//date follow score
		      
			    		      											int currentMAXIndex = i;
						    		      								scorelist[i] = currentMAX;
						    		      								dateMAX = datelist[i];//date follow score

						    		      for (int j = i+1; j < scorelist.length; j++) {//j = l +1 or j = l ??
						    		        if (currentMAX > scorelist[j]) {// made change here was > now <, switch min to max   low to high
						    		          currentMAX = scorelist[j];
						    		          		dateMAX = datelist[j];//date follow score checking index, so not needed her
						    		          currentMAXIndex = j;
						    		        }
						    		      }
						    		      // Swap list[j] with list[currentMAXIndex] if necessary;
						    		      if (currentMAXIndex != i) {
						    		        scorelist[currentMAXIndex] = scorelist[i];
						    		        				datelist[currentMAXIndex] = datelist[i];//swap date to follow score
						    		        scorelist[i] = currentMAX;
						    		      }
						    		      //after sorting score, rebuild score plus date
									    	sbOut.append(currentMAX);//create ordered outout, combine score and date, then pass
									    	sbOut.append(dateMAX);
									    	sbOut.append("\n");
						    		            
						    		    }//end sorting loop
						    	   		// Print to file
						    	    		java.io.File file11 = new java.io.File("names.txt");
						    		    try (java.io.PrintWriter output1 = new java.io.PrintWriter(file11); ) { // Write formatted output to the file
						    		    	output1.print(sbOut);

						    		    }

		}
			    
			    
			    
			    
	}//end top score file	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
  }// end class
