
/*
Jacques Favre
April-7-2018
COP2552.001
Final Project 
Java
Version 1.8
JavaFx GUI interface

Instruction for Blackjack

*/



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

class BlackjackPane extends BorderPane {	//Instruction box at bottom
	
  
  //NEW LABEL 
  private Label oneDice = new Label("Instructions."
  		+ "\n\nFirst, chose your options: play with one deck, or play with 6 decks."
  		+ "\nThen, press hit to get a card.\n"
  		+ "Press hold when done. If player go over 21, the dealer wins!.\nIf the dealer go over 21. the player wins."
  		+ "\nClick DEAL to start the next round.\nThere are 10 rounds in a game.\n© ajfavre.com");
  

 
  		//constructor*****************************
	  public BlackjackPane() {
    
	    this.setCenter(oneDice);
	    BorderPane.setAlignment(oneDice, Pos.CENTER);
	    this.setStyle("-fx-background-color: gainsboro");//darkgrey
	    this.setPadding(new Insets(20, 20, 20, 20));
  }// end 
	//constructor********************************

}//end class Intro Pane

