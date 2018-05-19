
// THIS Class dice box called by YahtzeeGUI

//  change for NEW  holdDice() and added showDice()  bofore scoreGame()
/*
Jacques Favre
April-23-2018
COP2552.001
Final Project 
Java
Version 1.8
JavaFx GUI interface

1 start window
3 games

credit:

	Java Programming Y David Liang Companion website at pearsonhighered.com/liang/	
	Sort function in File.java

//Get Dices pics from https://openclipart.org/detail/282132/die-6
//License here: https://openclipart.org/share


https://github.com/buckyroberts/Source-Code-from-Tutorials/blob/master/JavaFX/005_creatingAlertBoxes/AlertBox.java	
nameBox.java  to get name
confirmBox.java  to confirm at closing

*/




import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
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

//public class DiceBox {
//
//}


//CLASS DICE BOX
class DiceBox extends BorderPane{
	
	int NUMROUND = 2;// num of rounds 2 for test
//	int NUMROUND = 10;// num of rounds 10 for final
	
	 int counter = 0;// count every press of roll button
	 int roll = 1;// counter. 1, 2, 3 roll in one round. IF roll = 0 , roll all 5 dices, else hold  using a hold array
	 int rounds = 0; //10 rounds in a game	 
	 int score = 0;//Total score after 10 rounds
	 int tenTh = 0;// the tenth score to triger save to file or not
	 
	 String topTen = "";//string of top ten scores from text file to display in label
	 String name = "";// for player name
	 
	 //buttons
//	 	Button btRoll = new Button("ROLL");										//button at bottom  on
//	    Button btExit = new Button("EXIT");	
	 
	 
//	   public Pane pane;
	   //NEW LABEL
	   Label scorelbl = new Label("Score");//for one roll score
	   Label count = new Label("roll count: " + counter);//no text here need update label
	   Label endGame = new Label("Roll count: " + 0);
	   Label oneDice = new Label("Click on ROLL to start the game.");
	   Label topten = new Label("topten");
	   

	   
//	   Label rectangle = new Label("              ");
	   Label rectangle2 = new Label("              ");
	   
	   int [] randRoll = new int[5]; // array of dice, with 5 random number, used for first roll
	   int [] holdRoll = new int[5]; // array of dice, with 5 random number, used for first roll
	   int [] holdRoll1 = new int[5]; // array of dice, updated for second roll
	   int [] oneRoll = new int[5]; // array of dice updated
	   int[]scoreRoll = new int[5];// for scoring
	   
	   int score1roll = 0;// score of one roll
	   //for message score with string builder
	   int d1;
	   int d2;
	   int d3;
	   int d4;
	   int d5;
	   String message = "";//message for output what kind of score
	   
	   
	 //value for dices for display
		int dice1;
		int dice2;
		int dice3;
		int dice4;
		int dice5;
		
	    Pane pane1 = new Pane();
	    Pane pane2 = new Pane();
	    Pane pane3 = new Pane();
	    Pane pane4 = new Pane();  
	    Pane pane5 = new Pane();
	  
		boolean flag1 = false;// default is fase, so dice will be re-rolled
		boolean flag2 = false;
		boolean flag3 = false;
		boolean flag4 = false;
		boolean flag5 = false;
	   
	   
	   //constructor***************************
	   public DiceBox() throws IOException {
		   
		   this.setPadding(new Insets(20, 0, 20, 0));   
		   
/*		   //HBox for buttons
		   HBox buttonPane = new HBox(400);  
//	 	    this.setTop(buttonPane);
	 	   buttonPane.setPadding(new Insets(20, 20, 60, 20));
	 	   buttonPane.getChildren().addAll(btRoll, btExit);
	 	   buttonPane.setAlignment( Pos.CENTER);
*/			

		 //BOX for dice images	    
		 //setup HBox for images	    
		 	    HBox imagePane = new HBox(20);  
		 	    this.setCenter(imagePane);
//		 	   imagePane.setMinHeight(140);// set grey box size of dices
		 	    imagePane.setPadding(new Insets(20, 20, 20, 20));
		 	    imagePane.setStyle("-fx-background-color: lightgrey");		//gainsboro lightsteelblue or darkgrey
		 	    imagePane.setAlignment(Pos.CENTER);
		 	    imagePane.getChildren().addAll(pane1, pane2, pane3, pane4, pane5);// 5 panes for dices

		 	    
		//set up VBox at top for LABELS, custom Text for score result, and counting row and rounds, display top ten scores
				   VBox labelPane = new VBox(5);    //20 is space between
				   this.setBottom(labelPane); 

				   labelPane.getChildren().add(scorelbl);
				   labelPane.getChildren().add(count);
				   labelPane.getChildren().add(endGame);
				   labelPane.getChildren().add(oneDice);
				   labelPane.getChildren().add(topten);

				    labelPane.setPadding(new Insets(20, 0, 20, 0));
				    labelPane.setAlignment(Pos.CENTER);
				    labelPane.setStyle("-fx-background-color: wheat");
		
				   
	/*		    
				  //Button ROLL and EXIT				    
				   
				   btExit.setOnAction(e -> {
						try {
							closeProgram();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					});// call a closeProgram method to  save files thaen close
				    
				    // ROLL ROLL
				    btRoll.setOnAction(e -> {// Rolling
				     
				        rollingDice(); //    GET RANDOM NUM
				        printoneRoll();// to print one roll 5 dices to console
//				        diceBox.count(count + 1);//count press of button
				        showLabels();// call to update count
				        try {
							holdDice();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				       showDice();
				         
				    });	  
				    */  
	    
		// press on dice action
	    
		pane1.setOnMousePressed(e ->{
			setFlag1();
			showDice();// to refresh display	
		});
		pane2.setOnMousePressed(e ->{
			setFlag2();
			showDice();// to refresh display	
		});
		pane3.setOnMousePressed(e ->{
			setFlag3();
			showDice();// to refresh display	
		});
		pane4.setOnMousePressed(e ->{
			setFlag4();
			showDice();// to refresh display	
		});
		pane5.setOnMousePressed(e ->{
			setFlag5();
			showDice();// to refresh display	
		});
		
//		showHeader();//for label text
//		showDice();
		rollingDice();//value of dice, not updating// but need for first round
		showLabels();//did not work here got to button
		processFile();// method to start TextFile processing
		   
	   }// End constructor***************************


//read txt file
		private void processFile() throws IOException {
		//at start of game, read file, get top ten, create a string with top ten scores, display those
			// get file and .....
			
			//READ file for processing and ordering, but first read file, do some sorting  and  write file
			 // Create a File instance
//		    java.io.File file = new java.io.File("bestScore.txt");
			StringBuilder topTen = new StringBuilder();
			int countTopten = 0;
		    java.io.File file = new java.io.File("yahtzeetopScore.txt");
			if(!file.exists()){// IF no file, create one
		  	   file.createNewFile();
		  	}

		    try (Scanner s = new Scanner(file); ) {// Create a Scanner for the file
		      // Read data from a file

		    	
		    	
		    	int ctr1 = 0;
		    		while(s.hasNext()) {//first while loop to count element to set up size of array idea from: https://www.youtube.com/watch?v=k9pf8KKPcwI
		    			ctr1++;//counter for size of arrays
		    			s.nextInt();
		    			s.nextLine();
//		    			 System.out.println("ctr"+ ctr);
		    		}
		    		//now I know the size, I can create array, ctr is the size
		    		int[] scorelist = new int[ctr1];
		   		String[] datelist = new String[ctr1];
		   			Scanner s1 = new Scanner(file);
		  					int k= 0;//to count number of entries...Think I need only one of theses?

		  //					while((input.hasNext()) && counter < 99)
					       while ((s1.hasNext())  && countTopten < 10){//second while loop to read and pass int score and string date
					    	   countTopten++;
					        int score1 = s1.nextInt();// split score and date for sorting
					         String date1 = s1.nextLine();

								 scorelist[k] = score1;// array of score for sorting
								 datelist[k] = date1;//array of date to follow score

						         topTen.append(scorelist[k]);
						         topTen.append(".  ");
						           
						         k++;
						         
					      }//end second while, finished reading
					       
					  if (ctr1 < 10) { //counter for lenght of array is 0, no entry
						  
						  tenTh = 0;
					  }
					  else {
				//		  tenTh = scorelist[scorelist.length-1];// switch to last of array
						  tenTh = scorelist[9];// switch to last of array
					  }
					       
					       
					  System.out.println("Line269  Count in array : " + ctr1);
					       
					       System.out.println("Line271  TENth score : " + tenTh);	
					       
		    }//end try reading
		    
		  //  tenth best score = 
		    
		    System.out.println("Line 277 Array of TOP TEN: " + topTen);	
	//	topTen = "250, 240, 230, 220, 210, 200, 190, 180, 175, 170";
		topten.setText("Top 10 scores: " + topTen);
		
	}



/*****END STUFF
* @throws IOException ********/

		public void closeProgram() throws IOException {
		// Get boolean from ConfirmBox window
//			boolean answer = ConfirmBox.display("Alert", "Are you sure you want to exit?");// call a confirm window
			boolean answer = true;// hard code answer, to avoid comfirm window pooping up while testing
			if (answer) {
				allScoreFile();// save files
				System.out.println(" Clean close");
				System.exit(0);
//				borderPane.close();
//				startYahtzee.stage.close();
//				FxStageExample6.startYahtzee.stage.close();
				
				
//				stage.close();
//				System.close();
//				DiceBox();// 
//				YahtzeeGUI.window.close();// close window in yahtzeeGUI.java ?? does close window but then crash if try tp lay game again
//				YahtzeeGUI.gameYathtzee();
			}

		}// close program
		
//END OF GAME, display message, score, compare saved scores on file. If in top ten, prompt for user to enter name, if no name or blank create unknown entry 
		private void scoreGame() throws IOException {
			// get the tenth best score, for test set at 170
	//		tenTh = 30;// now value come from reading file in processFile
			
			
			endGame.setText("This is the end of the game. The score is: " + score + " points.");// end of game message	
			if(score <= tenTh) {//if not in 10 best, display message
				
				oneDice.setText("Click on ROLL to start a new game");
				System.out.println(score + " NOT top ten " + tenTh);
			}
			if(score > tenTh) {
				oneDice.setText("This score is in the TOP TEN. Enter your name to be on record.");
				// open a text field, validate input, replace an empty string with unknown
				System.out.println("line 319 Score is: " + score + "  TENth is: " + tenTh);
			updateFile();
			}
		
			
			score = 0;// reset score for next game
	//		return name;
		}



		private void updateFile() throws IOException {
	// TODO Auto-generated method stub
	//get name, read file get top 9 score add new name and score, sort the now ten name and score, print to file
	//call function to get input: Name		
		name = getName();//get name function to call in new window to avoid crownding main window
		System.out.println(" Main NAME: " + name);//got name
		//call scoring function with name and score
		allScoreFile();
		// build string name and score
	// get txt file
		//add new name and score
		//sort
		// print to file	 
	// and reset label
			oneDice.setText("Click on ROLL to start a new game");		
}
		
		private String getName() {//call a new window get and validate name, try, catch very basic
			// TODO Auto-generated method stub
			oneDice.setText("Enter your name to be on record.");
			//open a window called nameBox to get name
			name = nameBox.display("Alert", "Score is " + score + "\nYou are in the top Ten\nEnter your name, "
					+ "Press Enter\nOr simply press Enter to be unkwnon! ");// call a  window to enter name
			
			return name;
		}		

/*********************************************************** FILE PROCESS AT END 
* @throws IOException ****************************************/
		void allScoreFile() throws IOException {
			
			System.out.println(" Saving file with name: " + name);//got name
			// TODO Auto-generated method stub
//			java.util.Date date = new java.util.Date(); //create date object
			StringBuilder sb = new StringBuilder();

			//READ file for processing and ordering, but first read file, add new latest score, ovewrite file
			 // Create a File instance
		    java.io.File file = new java.io.File("yahtzeetopScore.txt");
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
				pr.print(score + " "); //add sccore
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
		private void topScoreFile() throws IOException {
			// TODO Auto-generated method stub
			StringBuilder sbOut = new StringBuilder();// string builder for top scores
			
			//READ file for processing and ordering, but first read file, do some sorting  and  write file
				 // Create a File instance
//			    java.io.File file = new java.io.File("bestScore.txt");
			    java.io.File file = new java.io.File("yahtzeetopScore.txt");
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
						    		        if (currentMAX < scorelist[j]) {// made change here was > now <, switch min to max
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
						    	    		java.io.File file11 = new java.io.File("yahtzeetopScore.txt");
						    		    try (java.io.PrintWriter output1 = new java.io.PrintWriter(file11); ) { // Write formatted output to the file
						    		    	output1.print(sbOut);

						    		    }

		}
	}//end top score file
		
/************ END FILE PROCESS AT END  **********************/		


/************************** Process and HOLDING ************************/

		//hold Dice 1
	private void setFlag1() {
		if(flag1 == true ) {	
			System.out.println();
		System.out.println(" BeforeFlag111 is :"+ flag1);
		flag1 = false;
		System.out.println(" AfterFlag111 is :"+ flag1);
		
	}
		else{
		System.out.println(" Before Flag is :"+ flag1);
		
		flag1 = true;
		System.out.println(" AfterFlag222 is :"+ flag1);
	
		}
	}
	//Hold Un-hold flags for picture change
	//hold Dice 2
	private void setFlag2() {
		if(flag2 == true ) {	
		flag2 = false;
	}
		else{
		flag2 = true;
		}
	}
	//hold Dice 3
		private void setFlag3() {
			if(flag3 == true ) {	
	    		flag3 = false;
	    	}
			else{
	   		flag3 = true;
			}
		}
		//hold Dice 4
		private void setFlag4() {
			if(flag4 == true ) {	
			   	flag4 = false;
			 }
			else{
			 flag4 = true;
			}
			}
		//hold Dice 5
			private void setFlag5() {
			if(flag5 == true ) {	
			   flag5 = false;
			    }
			else{
			   		flag5 = true;
				}
			}
	
	
	
	
	
	//counter of total rolls
//	public int count(int i) {
//		counter = counter + i;
//		System.out.println(" Total Counter: " + counter);
//		return counter;
//	}

	public void showLabels() {
		
//		oneDice.setText("Custom text");	//repalce text at set up// move in scoreGame
//		count.setText("Total roll count: " + counter );
		count.setText("Round: " + (rounds + 1) + "  Roll: " + roll);// count rounds and roll
		endGame.setText("Rolling, rolling!");// end of game message// moved to scoreGame function
//		scorelbl.setText("Round score: ");// moved to scoring
//		oneDice.setText("Top Ten scores: " + topTen);	//repalce text at set up// moved to processFile()
		
	}


	
	public int[] rollingDice() {		//SHOW LABEL DICES

	    
	    for (int i = 0; i <5; i++) {
	    	int dice = 0;
			Random r = new Random();
			dice = r.nextInt(6) + 1;
			randRoll[i] = dice;	

//	      lblDice[i].setText("dice is: "+ dice);   
	    }
		return randRoll;
	    	    
	  }

	public  void printoneRoll(){// to print array of dice to console
		
		System.out.println();
		for (int i = 0; i <5; i++) {System.out.print(oneRoll[i] + " ");}
	}
	
//NEW HOLD DICE	
	//hold value and update for re-roll
	//hold value and update for re-roll
	public int holdDice() throws IOException {
//		int roll = 0;// counter. IF roll = 0 , roll all 5 dices, else hold  using 3 arrays
		
		
		System.out.println("line 721 Roll Count: "+ roll);
		

		
		if (roll == 1) {// Reset all flags to false to un- due higlight then roll all 5 dices
			flag1 = false;
			flag2 = false;
			flag3 = false;
			flag4 = false;
			flag5 = false;
		
			for (int i = 0; i <5; i++) {
				oneRoll[i] = randRoll[i];// 5 random value passed to output for roll 1, 2, 3
				holdRoll1[i] = randRoll[i];// 5 random passed to holding array for roll 2 and 3
			}

		}//roll 5
		
		//if holding, combine randRoll and holdRoll
		if (roll == 2) {
			
			for (int i = 0; i <5; i++) {// refresh hold first than refresh
				holdRoll[i] = oneRoll[i];// 5 random passed to holding array for roll 2 and 3
				oneRoll[i] = randRoll[i];// 5 random value passed to output for roll 1, 2, 3
				
			}
			
			
			if( flag1) {// if flag is true, 
				oneRoll[0] = holdRoll[0];// hold the value
			}	
			if(!flag1){
				oneRoll[0] = randRoll[0];//else pass a new random value
			}
			if( flag2) {
				oneRoll[1] = holdRoll[1];
			}	
			if(!flag2){
				oneRoll[1] = randRoll[1];
			}
			if( flag3) {
				oneRoll[2] = holdRoll[2];
			}	
			if(!flag3){
				oneRoll[2] = randRoll[2];
			}
			if( flag4) {
				oneRoll[3] = holdRoll[3];
			}	
			if(!flag4){
				oneRoll[3] = randRoll[3];
			}
			if( flag5) {
				oneRoll[4] = holdRoll[4];
			}	
			if(!flag5){
				oneRoll[4] = randRoll[4];
			}
				
		}//if roll 1 
		
		
		//if holding, combine randRoll and holdRoll
				if (roll == 3) {
					
					for (int i = 0; i <5; i++) {// refresh hold first than refresh
						holdRoll[i] = oneRoll[i];// 5 random passed to holding array for roll 2 and 3
						oneRoll[i] = randRoll[i];// 5 random value passed to output for roll 1, 2, 3
						
					}
					
					
					
					if( flag1) {// if flag is true, 
						oneRoll[0] = holdRoll[0];// hold the value
					}	
					if(!flag1){
						oneRoll[0] = randRoll[0];//else pass a new random value
					}
					if( flag2) {
						oneRoll[1] = holdRoll[1];
					}	
					if(!flag2){
						oneRoll[1] = randRoll[1];
					}
					if( flag3) {
						oneRoll[2] = holdRoll[2];
					}	
					if(!flag3){
						oneRoll[2] = randRoll[2];
					}
					if( flag4) {
						oneRoll[3] = holdRoll[3];
					}	
					if(!flag4){
						oneRoll[3] = randRoll[3];
					}
					if( flag5) {
						oneRoll[4] = holdRoll[4];
					}	
					if(!flag5){
						oneRoll[4] = randRoll[4];
					}
						
				}//if roll 1 
		
		
		

		//when roll 3 reset roll  AND call scoring
		if (roll == 3) {// reset roll counter to 0 , since the counter follow
			roll = 0;
			System.out.println();
			System.out.println("line 817 SCORING: ");
			System.out.print("line 818 score:  ");
			for (int i = 0; i <5; i++) {System.out.print(oneRoll[i] + " ");}//display to console
			for(int j=0; j<5; j++){//pass score to scoring array, just because re-using score method
				scoreRoll[j] = oneRoll[j];	
			}
			rounds++;// count the rounds
			scoring(); // score this roll
			
			/********************** ROUNDS COUNT *****************/
			if( rounds == NUMROUND) {// reach end of game. Normal 10,  SET TO 2 for testing
				rounds = 0;// reset
				showDice();// to update display before scoring, was an issue at last round ask name, before showing dices 
				scoreGame();// end of game scoring, compare scores...
				processFile();// refresh display top ten scores
			}
		}
			
		roll++;	
		
		
		
//		showLabels();// update labels, or roll is 1 behind
		return (roll);

	}//hold dice()

	
	
	
	

	// The main GUI function to dispaly dices
	public void showDice() { 

		dice1 = oneRoll[0];
	dice2 = oneRoll[1];
	dice3 = oneRoll[2];
	dice4 = oneRoll[3];
	dice5 = oneRoll[4];
	System.out.println();
	System.out.println("dice 1 is : " + dice1 + "  dice 5 is : " + dice5 );// for display
	
	//get the image matching the number
	pane1 = getImage(pane1, dice1, flag1);
	pane2 = getImage(pane2, dice2, flag2);
	pane3 = getImage(pane3, dice3, flag3);
	pane4 = getImage(pane4, dice4, flag4);
	pane5 = getImage(pane5, dice5, flag5);
		
	}

//private Pane getImage(Pane pane) {
	  public Pane getImage(Pane pane, int randomNumber, boolean flag) {  


   System.out.print(randomNumber + " ");// print random number to console
 
   output(randomNumber, pane, flag);//function to match image with random number for output

return pane;
}
	
public void output(int X, Pane pane, boolean flag ) {// match variable image with an URL
	    Image di1 = new Image("image/dice1.png");
	    Image di2 = new Image("image/dice2.png");
	    Image di3 = new Image("image/dice3.png");
	    Image di4 = new Image("image/dice4.png");
	    Image di5 = new Image("image/dice5.png");
	    Image di6 = new Image("image/dice6.png");
	  //for highlight
		//alternate darker image
		Image di1d = new Image("image/dice1d.png");
		Image di2d = new Image("image/dice2d.png");
		Image di3d = new Image("image/dice3d.png");
		Image di4d = new Image("image/dice4d.png");
		Image di5d = new Image("image/dice5d.png");
		Image di6d = new Image("image/dice6d.png"); 
		
		if (flag == false) {//If flag false, not on hold

	      if(X==1) {pane.getChildren().add(new ImageView(di1));}// match number and image
	      if(X==2) {pane.getChildren().add(new ImageView(di2));}
	      if(X==3) {pane.getChildren().add(new ImageView(di3));}
	      if(X==4) {pane.getChildren().add(new ImageView(di4));}
	      if(X==5) {pane.getChildren().add(new ImageView(di5));}
	      if(X==6) {pane.getChildren().add(new ImageView(di6));}
	      
		}
		else {// if flag true, dice on hold, switch image to darker
			if(X==1) {pane.getChildren().add(new ImageView(di1d));}// match number and image
		      if(X==2) {pane.getChildren().add(new ImageView(di2d));}
		      if(X==3) {pane.getChildren().add(new ImageView(di3d));}
		      if(X==4) {pane.getChildren().add(new ImageView(di4d));}
		      if(X==5) {pane.getChildren().add(new ImageView(di5d));}
		      if(X==6) {pane.getChildren().add(new ImageView(di6d));}
		}
	  }//end output
/*******************************************  SCORING  *****************************/ 
//scoring method
	private int scoring() {
		// TODO Auto-generated method stub
		Arrays.sort(scoreRoll);//sort the array small to big
		//pass value array to variable
		for(int j=0; j<5; j++){
			d1 = scoreRoll[0];// getting value for each dice from the array
			d2 = scoreRoll[1];
			d3 = scoreRoll[2];
			d4 = scoreRoll[3];
			d5 = scoreRoll[4];
		}

		//And last the tricky Chance that wanted to add itself to other scores!!!
		if(scoreRoll[1] != scoreRoll[2] && scoreRoll[2] != scoreRoll[3] && ((scoreRoll[2]+1) != scoreRoll[3]) || ((scoreRoll[1]+1) != scoreRoll[2]) || scoreRoll[1] != scoreRoll[3]){
			score1roll = 0;
			for (int k = 0; k < scoreRoll.length; k++) {  // CHANCE: sum of all dice
			score1roll += scoreRoll[k];
			message = "Chance";
			}
		}
		// 3 of a kind - Sum of all the dice + 5.... 3 OR 3 OR 3
		if(scoreRoll[0] == scoreRoll[1] && scoreRoll[0] == scoreRoll[2] ) {
			score1roll = scoreRoll[0] + scoreRoll[1] + scoreRoll[2] + 5;
			message = "3 of kind";
		}
		if(scoreRoll[1] == scoreRoll[3] && scoreRoll[2] == scoreRoll[3] ) {
			score1roll = scoreRoll[1] + scoreRoll[2] + scoreRoll[3] + 5;
			message = "3 of kind";
		}
		if(scoreRoll[2] == scoreRoll[4] && scoreRoll[3] == scoreRoll[4] ) {
			score1roll = scoreRoll[2] + scoreRoll[3] + scoreRoll[4] + 5;
			message = "3 of kind";
		}

		// 4 of a kind - Sum of all the dice + 10....4 OR 4
		if(scoreRoll[0] == scoreRoll[1] && scoreRoll[0] == scoreRoll[2] && scoreRoll[0] == scoreRoll[3]) {
			score1roll = scoreRoll[0] + scoreRoll[1] + scoreRoll[2] + scoreRoll[3] + 10;
			message = "4 of kind";
		}
		if(scoreRoll[1] == scoreRoll[4] && scoreRoll[2] == scoreRoll[4] && scoreRoll[3] == scoreRoll[4]) {
			score1roll = scoreRoll[1] + scoreRoll[2] + scoreRoll[3] + scoreRoll[4] + 10;
			message = "4 of kind";
		}	

		// full house (2 matching dice AND 3 matching dice) - 25 pts
		if((scoreRoll[0] == scoreRoll[1] && scoreRoll[0] == scoreRoll[2]  && scoreRoll[3] == scoreRoll[4]) || (scoreRoll[0] == scoreRoll[1] &&  scoreRoll[2] == scoreRoll[4] && scoreRoll[3] == scoreRoll[4] )){
			score1roll = 25;
			message = "Full house";
		}

		// small straight (4 dice in consecutive sequence) - 30 pts .... middle 3 in row AND  (one more OR one less)
		if((scoreRoll[0]+3) == scoreRoll[3] && (scoreRoll[1]+2) == scoreRoll[3] && (scoreRoll[2]+1) == scoreRoll[3] ) {
			score1roll = 30;
			message = "Small straight";
		}
		if((scoreRoll[1]+3) == scoreRoll[4] && (scoreRoll[2]+2) == scoreRoll[4] && (scoreRoll[3]+1) == scoreRoll[4] ) {
			score1roll = 30;
			message = "Small straight";
		}

		// large straight (all dice in consecutive sequence) - 40 pts .......small + 1   1+ 4== 2+ 3== 3+2 == 4+ 1 == 5
		if((scoreRoll[0]+4) == scoreRoll[4] && (scoreRoll[1]+3) == scoreRoll[4] && (scoreRoll[2]+2) == scoreRoll[4] && (scoreRoll[3]+1) == scoreRoll[4]) {
			score1roll = 40;
			message = "Large straight";
		}

		// Yahtzee (all dice match) - 50 pts
		if(scoreRoll[0] == scoreRoll[1] && scoreRoll[0] == scoreRoll[2] && scoreRoll[0] == scoreRoll[3] && scoreRoll[0] == scoreRoll[4]) {
			score1roll = 50;
			message = "Yatzee";
		}

		//int score1roll = 0;
		//System.out.println("\nScore for this round is: "+ score1roll);// dispaly score current roll

		StringBuilder displayRoll = new StringBuilder();// build string to display message end of roll
		displayRoll.append("Roll ");
		displayRoll.append(rounds);
		displayRoll.append(": Dices: ");
		displayRoll.append(d1);
		displayRoll.append(d2);
		displayRoll.append(d3);
		displayRoll.append(d4);
		displayRoll.append(d5);
		displayRoll.append(" - ");
		displayRoll.append(score1roll);
		displayRoll.append(" points");
		displayRoll.append(" - ");
		displayRoll.append(message);

		System.out.println(displayRoll);// dispaly score current roll AND print to file
		
		scorelbl.setText("Score for " + displayRoll);
		
		score = score + score1roll;
		
		return score;
	}



//}


	
}//end class DiceBox
	
