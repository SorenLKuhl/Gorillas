package Gorillas;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;


public class SimpController {
	
	
	
	public static void buttons(Stage stage) {
		SimpViewer.button.setOnAction(e -> { //sets the dimensions of the game and checks if the values are valid
			if (SimpModel.isInt(SimpViewer.nValue) && SimpModel.isInt(SimpViewer.mValue) == true) {
				SimpViewer.angleAndVelocityInterface();
				
				stage.setScene(SimpViewer.scene2); //sets game scene
				
				//assigns value to angleShoot and velocityShoot if the entered values are valid
				SimpViewer.button2.setOnAction(event -> { 
					
					
					if(SimpModel.isValidAngle(SimpViewer.angle) && SimpModel.isDouble(SimpViewer.velocity)) {
						SimpModel.angleShoot = Double.parseDouble(SimpViewer.angle.getText());
						SimpModel.velocityShoot = Double.parseDouble(SimpViewer.velocity.getText());	
					}
				});
				
			}
		});
	}
}

