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


alert box and tutorial for clean close from here: https://github.com/buckyroberts/Source-Code-from-Tutorials/blob/master/JavaFX/005_creatingAlertBoxes/AlertBox.java
*/


import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.io.IOException;

import javafx.geometry.*;

public class nameBox {
	
	//bolean
	static boolean answer;
	static String name = "";
	static String wrong ="";
	static String unknown = "Unknown";
	

    public static String display(String title, String message) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);
        window.setMinHeight(300);
        
        Label label1 = new Label();
        label1.setText(message);
        TextField nameInput = new TextField();
        Label wrongInput = new Label(); 
        wrongInput.setText(wrong);
      //Event handler,  finish action, return name, close window,
        window.setOnCloseRequest(e -> {
        	e.consume();					// consume to override
        	name = isString(nameInput, nameInput.getText(), wrongInput);
        	window.close();
        });
         
        //create  buttons
        Button enterButton = new Button("Enter");
        enterButton.setOnAction(e -> {//Event handler, finish action, return name, close window,
        // call validate method
       name = isString(nameInput, nameInput.getText(), wrongInput);      	
        window.close();
        });

        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, nameInput, enterButton, wrongInput);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(80, 100, 80, 100));
        layout.setStyle("-fx-background-color: gainsboro");//lightgrey
       
        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        System.out.println(" box returnNAME: " + name);
        return name;
    }

//get string and validate
	private static String isString(TextField nameInput, String text, Label wrongInput) {
		// TODO Auto-generated method stub
		try {
			String namein = nameInput.getText();  			
			System.out.println(" box NAME: " + namein);
			if(namein.length() == 0)  {// if no input, set name to unknown
				name = unknown;
			}
			else {// else pass input
			name = namein;
			}
			
		}
		catch(IllegalArgumentException ex){
		
//			message
//			message = ("input not valid");
			wrongInput.setText("Click on ROLL to start a new game");
		}	
		return name;
	}// end is string
}// end programm window 