

/*
Jacques Favre
April-7-2018
COP2552.001
Final Project 
Java
Version 1.8
JavaFx GUI interface

Instruction for Hangman

*/



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class HangmanInfoPane extends BorderPane{


		
		  
		  //NEW LABEL 
		  private Label oneDice = new Label("Instructions.\n\nPress Start to play the game.\nChose a letter using the on-screen keyboard.\n"
		  		+ "You have some  chances to guess right or wrong.... .\nTry again."
		  		+ "\nWhen you get a WIN, or a LOSS, press start for the next word.");
		  

		 
		  		//constructor*****************************
			  public HangmanInfoPane() {
		    
				  this.setCenter(oneDice);
			    BorderPane.setAlignment(oneDice, Pos.CENTER);
			    this.setStyle("-fx-background-color: gainsboro");//lightgrey
			    this.setPadding(new Insets(20, 20, 20, 20));
		  }// end 
			//constructor********************************

		}//end class instructions


