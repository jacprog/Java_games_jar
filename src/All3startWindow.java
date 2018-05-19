
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

Yahtzee in Dicebox.java
Blackjack in Cardbox.java
Hangman in hangmanbox.java


credit:

Modality windows:
//   https://examples.javacodegeeks.com/desktop-java/javafx/javafx-stage-example/
Java Code Geeks and all content copyright © 2010-2018, Exelixis Media P.C.

alert box and tutorial for clean close from here: https://github.com/buckyroberts/Source-Code-from-Tutorials/blob/master/JavaFX/005_creatingAlertBoxes/AlertBox.java

	Java Programming Y David Liang Companion website at pearsonhighered.com/liang/	
	Sort function in File.java
	cards pictures
	
Our teacher at SantaFe College: Melody A. J. Kaufmann

For all the chalenges and examples provided, there are many bits and pices in here
	
extra windows
nameBox.java:    		one for name input, with very basic input validation try catch, all char are valid, if no name input set to Unknown
ConfirmBox.java 		to confirm close
Include clean close, save on close window

most of file for file are from my previous project



note: All closwe button here to work well with modality and save on close, button and window on x



*/

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class All3startWindow extends Application
{
	public static void main(String[] args) 
	{
		Application.launch(args);
	}
	// WINDOW 1		
	public void start(final Stage stage) {//stage primary window
	

		
		//Start YAHTZEE button
		
		Button playYahtzeebt = new Button("  Play YAHTZEE  ");
		playYahtzeebt.setOnAction(new EventHandler <ActionEvent>() 
		{
            public void handle(ActionEvent event) 
            {
            	try {
					startYahtzee(stage, Modality.APPLICATION_MODAL);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });	
		
		//Start Blackjack button
		
		Button playBlackjackbt = new Button("Play BLACKJACK");
		playBlackjackbt.setOnAction(new EventHandler <ActionEvent>() 
		{
            public void handle(ActionEvent event) 
            {
            	try {
					startBlackjack(stage, Modality.APPLICATION_MODAL);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });	
		
		//Start Hangman button
		
		Button playHangmanbt = new Button("  Play HANGMAN  ");
		playHangmanbt.setOnAction(new EventHandler <ActionEvent>() 
		{
            public void handle(ActionEvent event) 
            {
            	try {
					startHangman(stage, Modality.APPLICATION_MODAL);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });	
		
		//add more game buttons here
		
		Button closeButton = new Button("Close");
		// Add the EventHandler to the Button
		closeButton.setOnAction(new EventHandler <ActionEvent>() 
		{
            public void handle(ActionEvent event) 
            {
            	stage.close();
            }
        });		
	
		
		
		 Label label1 = new Label("Welcome!");
		    label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		    Label label2 = new Label("Enjoy these 3 games\nYahtzee is game of dices"
		    		+ "\nBlackjack is a card game\nHangman is a word guessing game");
		
		
		
		// Create the VBox
		VBox root = new VBox(40);
		root.setPadding(new Insets(20, 20, 20, 20));
		root.setStyle("-fx-background-color: darkgrey");//gainsboro lightsteelblue lightgrey
		root.setAlignment(Pos.CENTER);
		// Add the children to the VBox
//		playYahtzeebt.setStyle("-fx-background-color: wheat");
		root.getChildren().add(label1);
		root.getChildren().addAll(playYahtzeebt, playBlackjackbt, playHangmanbt);
		root.getChildren().add(label2);
		root.getChildren().add(closeButton);
		// Create the Scene
		Scene scene = new Scene(root, 700, 500);
		// Add the Scene to the Stage
		stage.setScene(scene);
		// Set the Title of the Stage
		stage.setTitle("Start Game Window");
		// Display the Stage
		stage.show();
	}//END  START stage primary window 
	
	/********************************************* Starting games method **********************************************/	

	
	/********************************************* Start Yahtzee **********************************************/	
	// WINDOW START YAHTZEE		
	private void startYahtzee(Window owner, Modality modality) throws IOException {//secondary window
	
		// Create a Stage with specified owner and modality
		final Stage stage = new Stage();
		stage.initOwner(owner);
		stage.initModality(modality);
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(20, 0, 0, 0));
		pane.setStyle("-fx-background-color: darkgrey");//lightgrey grey darkgrey
		
		HBox hBox = new HBox(400);
		hBox.setPadding(new Insets(20, 20, 20, 20));
	 	hBox.setAlignment( Pos.CENTER);
	 	hBox.setStyle("-fx-background-color: darkgrey");//lightgrey grey darkgrey

		DiceBox diceBox = new DiceBox();// game class
		
		// Create the Button
		Button closeButton = new Button("Close");
		// Add the EventHandler to the Button
		closeButton.setOnAction(new EventHandler <ActionEvent>() 
		{
            public void handle(ActionEvent event) 
            {	
            	try {
					diceBox.allScoreFile();// process and save file
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	stage.close();
            }
        });	
		

		Button rollButton = new Button("ROLL");
		rollButton.setOnAction(new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				diceBox.rollingDice(); //    GET RANDOM NUM
		        diceBox.printoneRoll();// to print one roll 5 dices to console
//		        diceBox.count(count + 1);//count press of button
		        diceBox.showLabels();// call to update count
		        try {
					diceBox.holdDice();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        diceBox.showDice();
			}
		});			

		//close window on X, clean close save files anyway
		 stage.setOnCloseRequest(e -> {
			 System.out.println(" Is it happening Clean close");
		    	e.consume();					// consume to override
							try {
								diceBox.allScoreFile();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						 stage.close();
		    });
		
		Scene scene = new Scene(pane, 800, 610);//yahtzee window size 
		hBox.getChildren().addAll( rollButton, closeButton); 
		pane.setTop(hBox); 
		pane.setCenter(diceBox);
		YahtzeePane yahtzeePane = new YahtzeePane();// call class in new file for instruction at bottom
	    pane.setBottom(yahtzeePane);
	    stage.setScene(scene);
		stage.setTitle("Yahtzee");
		// Display the Stage
		stage.show();
	}//END start Yahtzee window	
	
/********************************************* Start Blackjack **********************************************/	
	// WINDOW START BLACKJACK		
		private void startBlackjack(Window owner, Modality modality) throws IOException {//secondary window
		
			// Create a Stage with specified owner and modality
			final Stage stage = new Stage();
			stage.initOwner(owner);
			stage.initModality(modality);
			
			BorderPane pane = new BorderPane();
			pane.setPadding(new Insets(0, 20, 20, 20));
			pane.setStyle("-fx-background-color: darkgrey");//lightgrey grey darkgrey
			
			CardBox cardBox = new CardBox();// game class
					
//			File file = new File();
				
			HBox hBox = new HBox(400);
			
			 hBox.setPadding(new Insets(20, 20, 0, 0));
		 	 hBox.setAlignment( Pos.TOP_RIGHT);
			 hBox.setStyle("-fx-background-color: darkgrey");//lightgrey grey darkgrey

			// Create the Button
			Button closeButton = new Button("Close");
			// Add the EventHandler to the Button
			closeButton.setOnAction(new EventHandler <ActionEvent>() 
			{
	            public void handle(ActionEvent event) 
	            {
	            	try {
	            		cardBox.closeProgram();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	stage.close();
	            }
	        });	
				
			//close window on X, clean close save files anyway
			 stage.setOnCloseRequest(e -> {
				 System.out.println(" Is it happening Clean close");

			    	e.consume();					// consume to override
								try {
									cardBox.closeProgram();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							 stage.close();
			    });
			 
			 
			Scene scene = new Scene(pane, 1000, 800);//blackjack window size 
			hBox.getChildren().addAll( closeButton);
			pane.setTop(hBox);	
			pane.setCenter(cardBox);
			BlackjackPane blackjackPane = new BlackjackPane();// call class in new file for instruction at bottom 
		    pane.setBottom(blackjackPane);
		    stage.setScene(scene);
			stage.setTitle("BlackJack");
			// Display the Stage
			stage.show();
		}//END start Blackjack window	
	
		/********************************************* Start Hangman **********************************************/	
		
		// WINDOW START HANGMAN		
		private void startHangman(Window owner, Modality modality) throws IOException {//secondary window
		
			// Create a Stage with specified owner and modality
			final Stage stage = new Stage();
			stage.initOwner(owner);
			stage.initModality(modality);
			
			BorderPane pane = new BorderPane();
			pane.setPadding(new Insets(20, 20, 20, 20)); 
			pane.setStyle("-fx-background-color: darkgrey");//lightgrey grey darkgrey
			
			HBox hBox = new HBox(350);
		 	hBox.setAlignment( Pos.TOP_RIGHT);
		 	hBox.setStyle("-fx-background-color: darkgrey");//lightgrey grey darkgren
		 	
		 	HangmanBox hangmanBox = new HangmanBox();// game class
		 	 		 	    
		 	   // Create  buttons
		 		 Button btExit = new Button("Close");    

			// Add the EventHandler to the Close Button
			btExit.setOnAction(new EventHandler <ActionEvent>() 
			{			
	            public void handle(ActionEvent event) 
	            {  	
	            	try {
						hangmanBox.processFile();// process and save file
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	stage.close();
	            }
	        });	
		
			//close window on X, clean close save files anyway
			 stage.setOnCloseRequest(e -> {
				 System.out.println(" Is it happening Clean close");
			    	e.consume();					// consume to override
								try {
									hangmanBox.processFile();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							 stage.close();
			    });
				
			Scene scene = new Scene(pane, 1000, 790);//yahtzee window size
			hBox.getChildren().addAll(btExit); 
			pane.setTop(hBox);
			pane.setCenter(hangmanBox);
			HangmanInfoPane hangmanInfoPane = new HangmanInfoPane();// call class in new file for instruction at bottom
		    pane.setBottom(hangmanInfoPane);
		    stage.setScene(scene);
			stage.setTitle("Hangman");
			// Display the Stage
			stage.show();
		}//END start Yahtzee window	
		
	// add more games here	
	
}// end class