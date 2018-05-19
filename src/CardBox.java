

/*

Jacques Favre
4-24-2018
COP2552.001
Final Project : Blackjack 
Java
Version 1.8

Includes: java.util.Scanner		to read input from user
Includes: java.util.Random		to generate random number for dices
Includes: java.util.Arrays		to sort arrays
Includes: java.util......		to write to files
Includes: java.util.Date date	to get datre and time

"Blackjack" Card game also called 21
This game is a single player against the dealer. 
Here the dealer is the programm, with a set of rules
Dealer gets two cards, one is face up, next is face down.
Player gets two cards, then as a choice to get more cards: Hit, or not: Stand
If player go over 21, he lose
When Stand, Dealer shows both cards
If dealer as 21, it is a win. If both have 21, it is a tie
Else, If dealer less than 17, hit again
If dealer go over 21, he lose
When dealer has a least 17, compare scores
In a tie, no win or loss

Basic with 52 cards
Cards value:(no joker)
* Aces - these are worth 11 or 1 point based upon what is best for the player/dealer
* King, Queen, Jack - These are all worth 10 points
* Any non-face card is worth exactly what its number value is

* Options 
* 6 decks of cards

* number of imageView needed, worst case 2 + 2 + 2 + 2 + 3 + 3 + 3 + 3 + 4 is 24, that is a loss, it is 9 cards fo player, player can make mistake
* using list for card values and scoring, helps with variable size
* dealer 17 will have max 7 cards
* could go for 13 max player start at 1, go up
* dealer start at 12, go down

Program must:
Track cards, done in pickCard() , counting keep track of index od list
GUI, use mouse only
Track win loss ties for player for session
Track 10 best games
Reshufle (return played cards in deck between hands)



external files:
	
	blackjackName.txt				players names, wins
	
	blackjackPlayData.txt           track win loss player dealer on file 
	
	
	Classes
	Objects
	Arrays
	String manipulation
	String comparison
	loops
	

				
				
	At end of game, save files
		
	
	Options : 1 or 6 decks
				
	Buttons: 4
				Play
				Hit
				Stand
				Quit Game
				
	credit:
	Java Programming Y David Liang Companion website at pearsonhighered.com/liang/
				
	Sort function in File.java
	display face vlues		
	Cards image from: 
	
	alert box and tutorial for clean close from here: https://github.com/buckyroberts/Source-Code-from-Tutorials/blob/master/JavaFX/005_creatingAlertBoxes/AlertBox.java	
	
2 extra windows
nameBox.java
one for name input, with very basic input validation try catch, all char are valid
if no name input set to Unknown
ConfirmBox.java to confirm close

Include clean close
 
 
	
	*/






import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

class CardBox extends BorderPane{
	
//	int NUMROUND = 3;// num of rounds 3 for test
	int NUMROUND = 10;// num of rounds 10 for final
	int choice = 1;// select 1 deck or 6 decks// default is 1 for start
    int nextCard = 0;// for player value of index in deck 1-52 or 1-312
    int oneMoreCard = 0;// for dealer
	int counting = 0;// counting deck
	int press = 0;// count press of hit
	int playerPoints = 0;
	int dealerPoints = 0;
	int playerWin = 0;//count player win
	int dealerWin = 0; //count dealer win
	int countRounds= 0;
	int dealerRound = 0;
	int playerRound = 0;
	int ties = 0;
	String name = "";
	
	boolean donegame = false; //true when 10 rounds
	boolean hit = false; //true when 10 rounds
	boolean stand = false; //true when 10 rounds
	
	
//	int[] deckone = new int[52];//final
//	int[] decksix = new int[312];//final    
	ArrayList<Integer> deck = new ArrayList<Integer>();// for list of number that are cards
	ArrayList<Integer> playerHand = new ArrayList<Integer>();// for player hand
	ArrayList<Integer> scorePlayer = new ArrayList<Integer>();// to score  player hand
	ArrayList<Integer> dealerHand =new ArrayList<Integer>();// for  dealer hand
	ArrayList<Integer> scoreDealer = new ArrayList<Integer>();// to score  dealer hand
	
	//button   on   RIGHT
	Button btOne = new Button("Click to play with 1 Deck");
	Button btSix = new Button("Click to play with 6 Decks");
	Button btDeal = new Button("DEAL");
	Button btHit = new Button(" HIT ");
	Button btStand = new Button("STAND");
//    Button btExit = new Button("EXIT");	
    Button btShufle = new Button("Shufle");	
    
    Label label1 = new Label("Player");
    Label label2 = new Label("Dealer");
    Label label3 = new Label("Chose an option:");
//    Label label4 = new Label("Play with 52 cards");
//    Label label5 = new Label("Play with 312 card");
    Label label6 = new Label("                                  ");
    Label label7 = new Label("Player Points: " + playerPoints);
    Label label8 = new Label("Dealer Points: " + dealerPoints);
    Label label9 = new Label("Chose to play with one deck, or 6 decks of cards. click a button above.");
    Label label10 = new Label(" Round: 0  Player: 0  Dealer: 0  Ties: 0");// score player/dealer
	
	
	Pane pane = new Pane();
	
	Pane pane1 = new Pane();
    Pane pane2 = new Pane();
    Pane pane3 = new Pane();
    Pane pane4 = new Pane();  
    Pane pane5 = new Pane();
    Pane pane6 = new Pane();
    Pane pane7 = new Pane();
    Pane pane8 = new Pane();
    Pane pane9 = new Pane();
    Pane pane10 = new Pane();  
    Pane pane11= new Pane();
    Pane pane12 = new Pane();
    Pane pane13 = new Pane();  
    Pane pane14= new Pane();
    Pane pane15 = new Pane();
    Pane pane16 = new Pane();  

    
    Image back = new Image("image/back.png");// back of card image
    
 //   boolean showBut = true;
 //	 final boolean showBut = false;

/************** Constructor*************/
    public CardBox()	{
    
    	 this.setPadding(new Insets(20, 0, 20, 0)); //  this sets frame aroud green
    	
    	  btHit.setDisable(true);
		  btStand.setDisable(true);	
		  btDeal.setDisable(true);
		  btShufle.setDisable(true);
	
    //HBox for buttons at top
 	   HBox startPane = new HBox(40);  
 	   this.setTop(startPane);
 	   startPane.setPadding(new Insets(0, 20, 60, 20));
 	  startPane.getChildren().addAll( label3, btOne, btSix, label6); 
 	 label3.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
 	 startPane.setAlignment( Pos.CENTER);	
 	  this.setStyle("-fx-background-color: darkgrey");//lightgrey

 	  
 	  
 	  
	//VBox for buttons on left
	   VBox buttonPane = new VBox(80);  
	   this.setLeft(buttonPane);
	   buttonPane.setPadding(new Insets(20, 20, 20, 20));
	   buttonPane.getChildren().addAll( btDeal, btHit, btStand, btShufle);
	   buttonPane.setAlignment( Pos.CENTER);	
	  this.setStyle("-fx-background-color: darkgrey");//lightgrey
	  

	  
	  
	  
	  HBox playerPane = new HBox(20);
	  playerPane.getChildren().addAll(pane1, pane2, pane3, pane4, pane5, pane6, pane7, pane8, pane9);
	  playerPane.setPadding(new Insets(20,20,20,0));
	  
	  HBox dealerPane = new HBox(20);
	  dealerPane.getChildren().addAll(pane10, pane11, pane12, pane13, pane14, pane15, pane16);
	  dealerPane.setPadding(new Insets(20,20,20,0));

	  
	  
	  
		 //setup Box for images	    
		 	    VBox imagePane = new VBox();  
		 	    this.setCenter(imagePane);
		 	    imagePane.setPadding(new Insets(20,20,20,20));
		 	    imagePane.setStyle("-fx-background-color: MediumSeaGreen");		//gainsboro lightsteelblue forestgreen
	//	 	    imagePane.setAlignment(Pos.CENTER);
		 	    imagePane.getChildren().add(label1);//player
		 	    label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		 	    imagePane.getChildren().add(label7);//player points label
		 	    imagePane.getChildren().add(playerPane);//player cards
		 	    imagePane.getChildren().add(label9);//WINNER IS
		 	    label9.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 16)); 
		 	    imagePane.getChildren().add(label10);
		 	    imagePane.getChildren().add(dealerPane);// dealer cards
		 	    imagePane.getChildren().add(label2);///dealer
		 	    label2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		 	    imagePane.getChildren().add(label8);//dealer points label 
		 	  
/*		 	  
		 	  // EXIT button
		 	   btExit.setOnAction(e -> {// 
			 		  try {
						closeProgram();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  }); 	   	  
 */	 	
		 	  
		 	  
	  //Button Start click to play with one deck
	  btOne.setOnAction(e -> {// Start game, playing with 52 cards
		  choice = 1;// 1 deck
		  countRounds = 0;
		  playerWin = 0;
		  dealerWin = 0;
		  ties = 0;
		  shufleDeck(choice);
		  btDeal.setDisable(false);
		  btOne.setDisable(true);
//		  btShufle.setDisable(false);
		  label9.setText("Press DEAL to get your first 2 cards.");

	  });
	  
	//Button Start click to play with one deck
	  btSix.setOnAction(e -> {// Start game, playing with 6 decks of 52 cards, that is 312 card
		  choice = 6; // 6 decks
		  countRounds = 0;
		  playerWin = 0;
		  dealerWin = 0;
		  ties = 0;
		  shufleDeck(choice);
		  btDeal.setDisable(false);
		  btSix.setDisable(true);
		  label9.setText("Press DEAL to get your first 2 cards.");
//		  btShufle.setDisable(false);

	  });
	  // button DEAL, 
	  btDeal.setOnAction(e -> {// Deal 4 cards
		  System.out.println("DEAL press");
		  playerHand.clear();// clear the list 
		  dealerHand.clear();
//		  showLabels();
		  press = 0;// clear number of press hit button
		  pane1.setVisible(true);
		  pane2.setVisible(true);
		  
		  pane3.setVisible(false);//remove/hide player cards
		  pane4.setVisible(false);
		  pane5.setVisible(false);
		  pane6.setVisible(false);
		  pane7.setVisible(false);
		  pane8.setVisible(false);
		  pane9.setVisible(false);
		  
			pane10.setVisible(true);
			pane11.setVisible(true);
		  pane12.setVisible(false);// remove/hide dealer cards
		  pane13.setVisible(false);
		  pane14.setVisible(false);
		  pane15.setVisible(false);
		  pane16.setVisible(false);

		  btOne.setDisable(true);
		  btSix.setDisable(true);
		  btDeal.setDisable(true);
		  btShufle.setDisable(true);
		  btHit.setDisable(false);
		  btStand.setDisable(false);
		  label8.setText("");
		  label9.setText("Player, press HIT to get cards, or press STAND for the dealer to go");
		  label10.setText(" Round: " + (countRounds + 1) +  "   Player: " + playerWin + "  Dealer: " + dealerWin + "  Ties: " + ties);// score player/dealer
		  try {
	startGame();
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
		  countRounds++;

		  
		  });
	  // button HIT, gey a card
	  btHit.setOnAction(e -> {// HIT, add one card for player
		 press++;
		  hit = true;
		  hitOrStand(press);
		  scorePlayer();// get new score after each hit
//		  showLabels();
		  label10.setText(" Round: " + (countRounds + 0) +  "   Player: " + playerWin + "  Dealer: " + dealerWin + "  Ties: " + ties);// score player/dealer
//		  System.out.println("hit: " + hit);
		  });
	  // button STAND, dealer turn 
	  btStand.setOnAction(e -> {// Dealer turn, mow
		  stand = true;
		  DealerPlay();
//		  showLabels();
		  label10.setText(" Round: " + (countRounds + 0) +  "   Player: " + playerWin + "  Dealer: " + dealerWin + "  Ties: " + ties);// score player/dealer
		  });
	  // button to shuffle deck, refresh all cards back
	  btShufle.setOnAction(e -> {// Dealer turn, mow
		  		resetDeck();
		  });
	  
	  loadAndHide();
	
    }// end constructor	/************** END Constructor*************/
  
   public String  getPlayerName() {
	   String name1 = null;
	return name1;
  }

   
   
//   public int getPlayerWin() {
//	    return playerWin;
//	  }
   
   public void setPlayerName(String newName) {
		 name = newName; 
	}   
	
	public void setPlayerWin( int newPlayerWin) {
		playerWin = newPlayerWin;
	} 
   

    //	void closeProgram() {//not working static
    		// TODO Auto-generated method stub
	
	//	}
		public void closeProgram() throws IOException {
			// Get boolean from ConfirmBox window
//			boolean answer = ConfirmBox.display("Alert", "Are you sure you want to exit?");// call a confirm window
			boolean answer = true;// hard code answer, to avoid comfirm window pooping up while testing
			if (answer) {
				
				
				
				File.allScoreFile(name, playerWin);// save files
				System.out.println(" Clean close");
//				System.exit(0);
			}
		}



//This is not really needed but gives spacing so labels don't move when images are loaded    
private void loadAndHide() {
	// TODO Auto-generated method stub
	pane1.setVisible(false);
	pane10.setVisible(false);
	
	pane1.getChildren().add(new ImageView(new Image("image/back.png")));
	pane10.getChildren().add(new ImageView(new Image("image/back.png")));
	
}




public void showLabels() {
		
		label7.setText("Player Points: " + playerPoints);// count rounds and roll

		label8.setText("");// prevents showing dealer points before the card is showed

	}
    
    
    
    private void startGame() throws IOException {// called by btDeal

   		if(countRounds < NUMROUND) {// number is the number of rounds in game, constant at top of class
			getTwoPlayer();
			scorePlayer();
			getTwoDealer();
			System.out.println("COUNT ROUNDS: " + countRounds);
    		}
    		else {
 //   			countRounds--;
    			label10.setText("");
    			System.out.println("GAME OVER COUNT ROUNDS: " + countRounds);
    			btDeal.setDisable(true);
    			btHit.setDisable(true);
    			btStand.setDisable(true);
    			btOne.setDisable(false);
    			btSix.setDisable(false);
    			label7.setText("");// end of game clar
    			label8.setText("");// end of game clear
    			label9.setText("This is the end of the game. "
    					+ "  FINAL SCORES: Player: "+ playerWin + "    " +  " Dealer: " + dealerWin  + "    " + "  Ties: " + ties);
    			
    			pane1.setVisible(false);
   			  	pane2.setVisible(false);
    			pane10.setVisible(false);
    			pane11.setVisible(false);
    			
    			if(playerWin > dealerWin) {//if player win and not a tie at end of game., ask for name and save score
    				getName();
    				File.allScoreFile(name, playerWin);// save files
    			}
    			dataGame();//track win loss player dealer on file blackjackPlayData.txt
    		}
   
    }//end startgame
    
    
    
    //GET NAME and save to file
    //get name
	private String getName() {//call a new window get and validate name, try, catch very basic

		name = nameBox.display("Alert", "With " + playerWin + " wins, You are the winner.\nYou deserve to be on Record.\nEnter your name, "
				+ "Press Enter\nOr simply press Enter to be unkwnon! ");// call a  window to enter name
		
		return name;
	}		
    
    
    
    
    
    
    													/*********  PLAYER  ******/
    
    
	private void getTwoPlayer() {// deal first 2 cards
			
	    	nextCard = pickCard();// get next card in deck
	    	playerHand.add(nextCard);
	    	nextCard = pickCard();// get next card in deck
	    	playerHand.add(nextCard);
	//    	showPlayer1();
	    	showPlayer2();//show card 1 and 2
	    	System.out.println("playerHand1: " + playerHand);
	    }    
    
    

    private void hitOrStand(int press2) {
		// display dealer, player, get sum player, hit or stand
    	System.out.println("532hit2: " + hit);
    	System.out.println("533 num of Press bt hit: " + press2);
    	if (hit == true) {
    		nextCard = pickCard();// get next card in deck
        	playerHand.add(nextCard);
//        	if( press ==0) {//
//        		System.out.println("clear playerHand: " + playerHand);
//        	}
        	
        	if( press ==1) {// adds a 3rd card
        		pane3.setVisible(true);
        		showPlayer2();
        		showPlayer3();
        	}
        	else if( press ==2) {
        		pane4.setVisible(true);
        		showPlayer2();
             	showPlayer3();
             	showPlayer4();
            	}
        	else if( press ==3) {
        		pane5.setVisible(true);
        		showPlayer2();
        		showPlayer3();
             	showPlayer4();	
             	showPlayer5();
        	}
        	else if( press ==4) {
        		pane6.setVisible(true);
        		showPlayer2();
        		showPlayer3();
             	showPlayer4();	
             	showPlayer5();
             	showPlayer6();
            	}
        	else if( press ==5) {
        		pane7.setVisible(true);
        		showPlayer2();
        		showPlayer3();
             	showPlayer4();	
             	showPlayer5();
             	showPlayer6();
             	showPlayer7();
            	}
        	else if( press ==6) {
        		pane8.setVisible(true);
        		showPlayer2();
        		showPlayer3();
             	showPlayer4();	
             	showPlayer5();
             	showPlayer6();
             	showPlayer7();
                showPlayer8();
                	}
        	else if( press ==7) {
            		pane9.setVisible(true);
            		showPlayer2();
            		showPlayer3();
                 	showPlayer4();	
                 	showPlayer5();
                 	showPlayer6();
                 	showPlayer7();
                    showPlayer8();
                 	showPlayer9();
                	}
        		if ( press >=8) {
               		btHit.setDisable(true);
                    	}
    	}
    	
    	
    	
    	System.out.println("playerHand: " + playerHand);
		
	}//end hit or stand
    

	
    private void showPlayer2() {//2 cards
    	
//    	System.out.println("playerHand2: " + playerHand);

    	pane1.getChildren().add(new ImageView(new Image("image/" + (1+playerHand.get(0)) + ".png")));
		pane2.getChildren().add(new ImageView(new Image("image/" + (1+playerHand.get(1)) + ".png")));

	}
	private void showPlayer3() {

		pane3.getChildren().add(new ImageView(new Image("image/" + (1+playerHand.get(2)) + ".png")));
	}
	private void showPlayer4() {

		pane4.getChildren().add(new ImageView(new Image("image/" + (1+playerHand.get(3)) + ".png")));
	}
	private void showPlayer5() {

		pane5.getChildren().add(new ImageView(new Image("image/" + (1+playerHand.get(4)) + ".png")));
	}
	private void showPlayer6() {

		pane6.getChildren().add(new ImageView(new Image("image/" + (1+playerHand.get(5)) + ".png")));
	}
	private void showPlayer7() {

		pane7.getChildren().add(new ImageView(new Image("image/" + (1+playerHand.get(6)) + ".png")));
		
	}
	private void showPlayer8() {

		pane8.getChildren().add(new ImageView(new Image("image/" + (1+playerHand.get(7)) + ".png")));
	}
	private void showPlayer9() {

		pane9.getChildren().add(new ImageView(new Image("image/" + (1+playerHand.get(8)) + ".png")));
	}
	
	/*********  Score player  ******/
	
	private int scorePlayer() {
    	// get player score, convert card in playerHand to face value 2 to 11 or 1 to 10 pending Ace 

    	playerPoints = 0;
    	scorePlayer.clear();
 //   	    int[] deck = new int[312];
 //   	    String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
//    	    String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9","10", "Jack", "Queen", "King"};
    	    
    	    int [] values = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};// card value
    	    
    	    							// images: 1- 13spade, , 14 - 26 hearts,  27 - 39 Diamonds, 40 - 52 Clubs
							    	    //clubs (♣), diamonds (♦), hearts (♥) and spades (♠),
							    	    // 0- 52  53 - 104   105 - 156  157 - 208	   
    	    
    	    	//    check for card face value
    	    	for (int i=0; i<playerHand.size(); i++) {	
    	    		
    	    		// filter 1 or 6 decks
    	    		int dec = playerHand.get(i) / 52; //( dec+ 1 ) is wich of six dec    	
    	    		      
 //   		      String suit = suits[((playerHand.get(i)) - (dec * 52)) / 13];
 //   		      String rank = ranks[((playerHand.get(i)) - (dec * 52)) % 13];
    		      int val = values[((playerHand.get(i)) - (dec * 52)) % 13];
    	    	
    		  // for console display only
 //   	      System.out.println("Card pick is " + playerHand.get(i) + ": DECKS: " + (dec + 1) + "  " + rank + " of " + suit);
 //   	      System.out.println("Card number  is " + ( 0 +  (playerHand.get(i) - (dec * 52)) ));// number to get image
 //   	      System.out.println("Card value  is " +  val );// value for scoring 
    	      
    	      //adding value to list of player score
    	      scorePlayer.add(val);
 

    	}// end for loop check card
    	    	Collections.sort(scorePlayer);
//    	    	 System.out.println("Sorted scorePlayer: " + scorePlayer);
    	    	 for (int i=0; i<scorePlayer.size(); i++) {
    	    		 playerPoints = playerPoints + scorePlayer.get(i);
    	    		 

    	    	 }
    	    	if (playerPoints > 21 && scorePlayer.contains(11)){

    	    		playerPoints = playerPoints -10;
//    	    		 System.out.println("reset score "  );// value for scoring 
    	    	}
    	    	if(playerPoints > 21) {//player has lost
    	    		comparePoints();
 //   	    		dealerWin++;
//    	    		label9.setText("Player went over, Dealer Wins!");//
//    	    		System.out.println("647  player lost, dealer win "  );//
    	    		btDeal.setDisable(false);
    	  		  	btHit.setDisable(true);
    	  		  	btStand.setDisable(true);
    	  		  	btShufle.setDisable(false);
    	    	}
    	    		
    	    		
    	    	
//    	System.out.println("playerPoints: " + playerPoints);
    	showLabels();
    	return playerPoints;
    }//end player points 
	
	
										/*********  DEALER  ******/
	
	
	private void getTwoDealer() {// deal first 2 cards

    	nextCard = pickCard();// get next card in deck
    	dealerHand.add(nextCard);
    	System.out.println("dealercard1: " + dealerHand);
    	nextCard = pickCard();// get next card in deck
    	dealerHand.add(nextCard);
    	System.out.println("dealercard2: " + dealerHand);
    	showDealer1();
    	System.out.println("dealerrHand1: " + dealerHand);
    } 
    
    
    
    
private void showDealer1() {//2 cards, but one on back
	System.out.println("dealerrHand2: " + dealerHand);
		pane10.getChildren().add(new ImageView(new Image("image/" + (1+dealerHand.get(0)) + ".png")));
		pane11.getChildren().add(new ImageView(new Image("image/back.png")));
	}


		private void DealerPlay() {
			// TODO Auto-generated method stub
			showDealer2();
			scoreDealer();
			label8.setText("Dealer Points: " + dealerPoints);//
			if( dealerPoints < 17) {
				oneMoreCard = pickCard();// get next card in deck
	        	dealerHand.add(oneMoreCard);
	        	pane12.setVisible(true);
        		showDealer2();
        		showDealer3();
        		scoreDealer();
        		label8.setText("Dealer Points: " + dealerPoints);//
        		if( dealerPoints < 17) {
    				oneMoreCard = pickCard();// get next card in deck
    	        	dealerHand.add(oneMoreCard);
    	        	pane13.setVisible(true);
            		showDealer2();
            		showDealer3();
            		showDealer4();
            		scoreDealer();
            		label8.setText("Dealer Points: " + dealerPoints);//
            		if( dealerPoints < 17) {
        				oneMoreCard = pickCard();// get next card in deck
        	        	dealerHand.add(oneMoreCard);
        	        	pane14.setVisible(true);
                		showDealer2();
                		showDealer3();
                		showDealer4();
                		showDealer5();
                		scoreDealer();
                		label8.setText("Dealer Points: " + dealerPoints);//
                		if( dealerPoints < 17) {
            				oneMoreCard = pickCard();// get next card in deck
            	        	dealerHand.add(oneMoreCard);
            	        	pane15.setVisible(true);
                    		showDealer2();
                    		showDealer3();
                    		showDealer4();
                    		showDealer5();
                    		showDealer6();
                    		scoreDealer();
                    		label8.setText("Dealer Points: " + dealerPoints);//
                    		if( dealerPoints < 17) {
                				oneMoreCard = pickCard();// get next card in deck
                	        	dealerHand.add(oneMoreCard);
                	        	pane16.setVisible(true);
                        		showDealer2();
                        		showDealer3();
                        		showDealer4();
                        		showDealer5();
                        		showDealer6();
                        		showDealer7();
                        		scoreDealer();
                        		label8.setText("Dealer Points: " + dealerPoints);//
            		
            		
            		
                    		}
                		}	
            		}
        		}
	
			}
		}// end dealer play


			private void showDealer2() {//2 cards, now show both
//				System.out.println("dealerrHand2: " + dealerHand);
					pane10.getChildren().add(new ImageView(new Image("image/" + (1+dealerHand.get(0)) + ".png")));
					pane11.getChildren().add(new ImageView(new Image("image/" + (1+dealerHand.get(1)) + ".png")));

				}
			private void showDealer3() {//3 cards, now show both

					pane12.getChildren().add(new ImageView(new Image("image/" + (1+dealerHand.get(2)) + ".png")));
				}
			private void showDealer4() {//4 cards, now show both

					pane13.getChildren().add(new ImageView(new Image("image/" + (1+dealerHand.get(3)) + ".png")));
			}
			private void showDealer5() {//5 cards, now show both

					pane14.getChildren().add(new ImageView(new Image("image/" + (1+dealerHand.get(4)) + ".png")));
			}
			private void showDealer6() {//6 cards

					pane15.getChildren().add(new ImageView(new Image("image/" + (1+dealerHand.get(5)) + ".png")));
			}
			private void showDealer7() {//7 cards

					pane16.getChildren().add(new ImageView(new Image("image/" + (1+dealerHand.get(6)) + ".png")));
			}
			
			
			/*********  Score dealer  ******/

			private int scoreDealer() {
		    	// get player score, convert card in playerHand to face value 2 to 11 or 1 to 10 pending Ace 
//		    	for (int i=0; i<playerHand.size(); i++) {
//		    		playerPoints = playerPoints	+ playerHand.get(i);
//		    	  } // end score player 
		    	dealerPoints = 0;
		    	scoreDealer.clear();
//		    	    int[] deck = new int[312];
//		    	    String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
//		    	    String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9","10", "Jack", "Queen", "King"};
		    	    
		    	    int [] values = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};//card values
		    	    
		    	    							// images: 1- 13spade, , 14 - 26 hearts,  27 - 39 Diamonds, 40 - 52 Clubs
									    	    //clubs (♣), diamonds (♦), hearts (♥) and spades (♠),
									    	    // 0- 52  53 - 104   105 - 156  157 - 208	   
		    	    
		    	    	//    check for card face value
		    	    	for (int i=0; i<dealerHand.size(); i++) {	
		    	    		// filter 1 or 6 decks
		    	    		
		    	    		int dec = dealerHand.get(i) / 52; //( dec+ 1 ) is wich of six dec    	
		    	    		      
//		    		      String suit = suits[((dealerHand.get(i)) - (dec * 52)) / 13];// for console print out
//		    		      String rank = ranks[((dealerHand.get(i)) - (dec * 52)) % 13];
		    		      int val = values[((dealerHand.get(i)) - (dec * 52)) % 13];
		    	    	
		    		  // for console display only
//		    	      System.out.println("Card pick is " + dealerHand.get(i) + ": DECKS: " + (dec + 1) + "  " + rank + " of " + suit);
//		    	      System.out.println("Card number  is " + ( 0 +  (dealerHand.get(i) - (dec * 52)) ));// number to get image
//		    	      System.out.println("Card value  is " +  val );// value for scoring 
		    	      
		 //   	      //adding value to list of player score
		    	      scoreDealer.add(val);
		

		    	}// end for loop check card
		    	    	Collections.sort(scoreDealer);
		    	    	 System.out.println("Sorted scorePlayer: " + scoreDealer);
		    	    	 for (int i=0; i<scoreDealer.size(); i++) {
		    	    		dealerPoints = dealerPoints + scoreDealer.get(i);
		    	    		 
		    	    	 }
		    	    	 
		    	    	if (dealerPoints > 21 && scoreDealer.contains(11)){// check for Ace

		    	    		dealerPoints = dealerPoints -10;
		    	    		 System.out.println("reset score "  );// value for scoring 
		    	    	}
		    	    	
		    	    	if(dealerPoints >= 17 && dealerPoints <= 21) {//dealer done
		    	    		
		    	    		comparePoints();
		    	    		btDeal.setDisable(false);
		    	  		  	btHit.setDisable(true);
		    	  		  	btStand.setDisable(true);
		    	  		  	btShufle.setDisable(false);
		    	    		
		    	    		
		    	    	}
		    	    	
		    	    	
		    	    	
		    	    	if(dealerPoints > 21) {//dealer has lost
//		    	    		playerWin++;
//		    	    		label9.setText("Dealer went over, Player Wins!");//
//		    	    		playerRound++;
		    	    		comparePoints();
		    	    		System.out.println("767  dealer lost, player win "  );//
		    	    		btDeal.setDisable(false);
		    	  		  	btHit.setDisable(true);
		    	  		  	btStand.setDisable(true);
		    	  		  	btShufle.setDisable(false);
		    	    		
		    	    	}
		    	    		
		    	    		
		    	    	
		    	System.out.println("dealerPoints: " + dealerPoints);
		    	showLabels();
		    	return dealerPoints;
		    }//end player points 
			




			private void comparePoints() {//player < 21 and dealer < 21

				 if(playerPoints > 21) {
					label9.setText("Player went over, Dealer Wins!");//dealer wins
					dealerWin++;
					
				}
				
				 else if(dealerPoints > 21) {
					label9.setText("Dealer went over, Player Wins!");//player wins
					playerWin++;
					System.out.println("Player win here1 ");
				}
				
				
				
				else if ((dealerPoints <= 21 && playerPoints <= 21) && (dealerPoints > playerPoints)) {// dealer wins
					label9.setText("Dealer has the best score, Dealer Wins!");//
					dealerWin++;
				}
				
				else if((dealerPoints <= 21 && playerPoints <= 21) && (dealerPoints < playerPoints)){// player win
					label9.setText("Player has the best score, Player Wins!");//
					playerWin++;
					System.out.println("Player win here1 TOOOO ");
				}
				else if ((dealerPoints == playerPoints) && (playerPoints <= 21 || dealerPoints <= 21)){//it is a tie and no one lost
					label9.setText("It is a tie, no wins!");//
					ties++;
				}

				
			label10.setText(" Round: " + (countRounds + 0) +  "   Player: " + playerWin + "  Dealer: " + dealerWin + "  Ties: " + ties);// score player/dealer
		
	}// end compare points








			/*********  DECK  ******/

public ArrayList<Integer> shufleDeck(int choice) {// choice is 1 or 6, 1 deck or 6 decks
//	ArrayList<Integer> cards = new ArrayList<>();
	for (int i = 0; i < (52* choice); i++) {
//		for (int i = 0; i < 13; i++) {
//		deck.add(i);
		deck.add(i+1);
	}
	
	Collections.shuffle(deck);// this shuffles cards
	
	System.out.println("Cards: " + deck );
	
	return deck;
}

public int pickCard() {
	
	System.out.println("Choice in pick Cards: " + choice );

			int pullcard = deck.get(counting);// this keep track of cards in deck 1 - 52 0r 1 - 312 (index 0-51 or 0-311)
	
		  counting ++;	
		  System.out.println("Size of deck: " + deck.size() );
		  System.out.println("COUNTING: " + counting );
		  System.out.println("COUNTING*choice: " + counting );
		  
		  if(counting == deck.size()){
			  
			  resetDeck();
			 
		  }
		  
//		  System.out.println("CHOICE IN PICK: " + choice );	  
		  
		  int dec = pullcard / 52;    
		  System.out.println("793Deck: " + dec );
		  nextCard =   (pullcard - (dec * 52)) ;
		  System.out.println("PULL CARD: " + pullcard + "  NEXTCARD: " + nextCard  );
//		  nextCard = nextCard*10;
//		  int val = values[(playerHand.get(i) - (dec * 52)) % 13];	 
		  
//		  System.out.println("Pull Card number  is " + ( 1 +  (pullcard - (dec * 52)) ));// number to get image  
//		  System.out.println("NEXTCards: " + nextCard );
	
	return nextCard;
}


//called when all cards are out  and when shuffle button pressed
	private void resetDeck() {
		System.out.println("SHUFLE "  );
		 Collections.shuffle(deck);// this shuffles cards
		  counting = 0;// and reset counter
	}

	
	
	/******************************** track data file  *****************************************/
	
	
	public void dataGame() throws IOException {
	//int score1roll = 0;
	//System.out.println("\nScore for this round is: "+ score1roll);// dispaly score current roll
		//dealerWin
		//playerWin
		//name
		System.out.println("Playerdata " + playerWin);
		
		int number = 0;// sum of player and dealer points
		int newNumber = 0;// sum of player and dealer points
		
	//READ file, add new roll, print to file
	//Create a File instance
	int counter = 0;
	StringBuilder displayAllData = new StringBuilder();
	java.io.File file = new java.io.File("blackjackPlayData.txt");
	if(!file.exists()){
		   file.createNewFile();
		}
	try ( Scanner input = new Scanner(file);) {
	  // Read data from a file, counter to adjust the total to output, 9 for 1 game(10 rolls), 99 for 10 games
		
	//  displayAllRoll.append("message AT Top\n");
		
		
		while((input.hasNext()) && counter < 99){ // to limit lignes of data in output(4 will output 5 lignes 0, 1, 2, 3, plus print the latest entry = 5)
		counter++;	
		
		int numberIn = input.nextInt();// top last number numberIn
		String message  = input.nextLine();
		if(counter == 1) {
			newNumber = numberIn;// get last number, top of file so first
		}
		
		displayAllData.append(numberIn);
		    displayAllData.append(message);
		    displayAllData.append("\n");			
		
			System.out.println("NUMBER IN :" + newNumber);// dispaly score current roll AND print to file
		
		

	  }// end while input
		
	}// end try
	
	// create new line of output
	
	number = newNumber + dealerWin - playerWin;// this will track if dealer wins( positif num) or player wins (negatif number)
	
	StringBuilder displaydata = new StringBuilder();// build string to display message end of roll
	
	displaydata.append(number);
	displaydata.append("  Score points for Player:  ");
//	displaydata.append(name);
//	displaydata.append(": ");
	displaydata.append(playerWin);
	displaydata.append("   Dealer: ");
	displaydata.append(dealerWin);
	displaydata.append("  Ties: ");
	displaydata.append(ties);
	
//	System.out.println(displaydata);// dispaly score current roll AND print to file
	

	java.io.File file1 = new java.io.File("blackjackPlayData.txt");// this only for one game
				//if (file.exists()) {
				//  System.out.println("File already exists");
				 // System.exit(0);
				//}

		try (java.io.PrintWriter output = new java.io.PrintWriter(file1);) {
			  // Write formatted output to the file
		//		output.print("Message\n");
				output.print(displaydata + "\n");// new set of data
				output.print(displayAllData);
				
		}

		System.out.println("NUmber IN :" + newNumber);// dispaly score current roll AND print to file
		System.out.println(displaydata);// dispaly score current roll AND print to file
		System.out.println(displayAllData);// dispaly score current roll AND print to file
		
	
	} //end data game	
	
	
	
	
	
/*	
	
    
	//this adds to existing file or create file if not one
			public void allScoreFile() throws IOException {
				System.out.println("NAME in file OUT: "	+ name);// check for name
				System.out.println("SCORE  in file OUT: "	+ playerWin);// check for name
				
				
				// TODO Auto-generated method stub
//				java.util.Date date = new java.util.Date(); //create date object
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
					pr.print(playerWin + " "); //add sccore
//					pr.println(date);		//add date
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
			
			
			// NOW SORTING
			
			
			private static void topScoreFile() throws IOException {
				// TODO Auto-generated method stub
				StringBuilder sbOut = new StringBuilder();// string builder for top scores
				
				//READ file for processing and ordering, but first read file, do some sorting  and  write file
					 // Create a File instance
//				    java.io.File file = new java.io.File("bestScore.txt");
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
//				    			 System.out.println("ctr"+ ctr);
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
							    		        if (currentMAX > scorelist[j]) {// made change here was > now <, switch min to max
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
	
*/


}//end class cardBox

