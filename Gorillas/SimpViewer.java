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

public class SimpViewer extends Application {
	
		Button button, button3;
		Scene scene1, scene2;
		Stage gameStage;
		int m;
		int n;
		

		public static void main(String[] args) {
			launch(args);
		}
		@Override
		public void start(Stage primaryStage) throws Exception {
			
			primaryStage.setTitle("Gorillas"); //stage
			
			TextField nValue = new TextField(); //textfield for width
			TextField mValue = new TextField(); //textfield for height
			
			Label label1 = new Label("Enter width and height of the game below"); //description
			
			button = new Button();
			button.setText("Continue to game"); //button for applying width and height
			

			button.setOnAction(e -> { //sets the dimensions of the game and checks if the values are valid
			//	boolean b = SimpController.isInt(nValue);
			//	boolean c = SimpController.isInt(mValue);
				//if (b && c == true) {
					n = Integer.parseInt(nValue.getText());
					m = Integer.parseInt(mValue.getText());
					
					StackPane layout2 = new StackPane(); //layout of game
					layout2.getChildren().add(button3);
					scene2 = new Scene(layout2, n, m); //game scene made with the layout predefined
					primaryStage.setScene(scene2); //sets game scene

			//	}
			});
			
			VBox layout1 = new VBox(10); //layout of initialization scene 
			layout1.setPadding(new Insets(20, 20, 20, 20)); //space from edges of the scene
			layout1.getChildren().addAll(label1,  nValue, mValue, button); //adds button and textfield
			
			scene1 = new Scene(layout1, 400, 180); //initialization scene with the predefined layout
			
			button3 = new Button();
			button3.setText("Go to scene 1");
			
			button3.setOnAction(e -> {
				primaryStage.setScene(scene1);
			});
			
			
			
			primaryStage.setScene(scene1);
			primaryStage.show();
		}
		

	
	}

