
/*
Jacques Favre
April-7-2018
COP2552.001
Final Project 
Java
Version 1.8
JavaFx GUI interface

Instruction for Yahtzee

*/


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;



class YahtzeePane extends BorderPane {	
	
	  
	  //NEW LABEL 
	  private Label oneDice = new Label("Instructions.\n\nPress ROLL to roll the dices.\nThere are 3 rolls for each Round.\n"
	  		+ "That gives 2 chances to improve the hand.\nClick on a dice to hold it. Hold 1 to 5 dices.\nClick again to un-hold.\nPress ROLL to roll the remaining.\n© ajfavre.com");
	  

	 
	  		//constructor******************************************
		  public YahtzeePane() {
	    
		    this.setCenter(oneDice);
		    BorderPane.setAlignment(oneDice, Pos.CENTER);
		    this.setStyle("-fx-background-color: gainsboro");//darkgrey
		    this.setPadding(new Insets(20, 20, 20, 20));
	  }// end 
		//constructor******************************************

	}//end class Yatzee Pane