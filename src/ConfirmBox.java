/*
Jacques Favre
April-7-2018
COP2552.001
Project 3 Yahtzee GUI
Java
Version 1.8
JavaFx GUI interface


alert box and tutorial for clean close from here: https://github.com/buckyroberts/Source-Code-from-Tutorials/blob/master/JavaFX/005_creatingAlertBoxes/AlertBox.java
*/


import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {
	
	//bolean
	static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        window.setMinHeight(200);
        Label label = new Label();
        label.setText(message);
 //       Button closeButton = new Button("Close this window");
  //      closeButton.setOnAction(e -> window.close());
        
        //create two buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.setOnAction(e -> {
        answer = true;	
        window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;	
            window.close();
            });
        
        VBox layout = new VBox(10);
 //       layout.getChildren().addAll(label, closeButton);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(80, 100, 80, 100));
        layout.setStyle("-fx-background-color: gainsboro");//lightgrey
        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return answer;
    }

}