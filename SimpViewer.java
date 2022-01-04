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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class SimpViewer extends Application {
	
		Button button, button2;
		Scene scene1, scene2;
		Stage gameStage;
		int m, n;
		Double angleShoot, velocityShoot;
		

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
				boolean b = SimpController.isInt(nValue);
				boolean c = SimpController.isInt(mValue);
				if (b && c == true) {
					n = Integer.parseInt(nValue.getText());
					m = Integer.parseInt(mValue.getText());
					
					Group group = new Group(); //layout of game
					
					Canvas canvas = new Canvas(n,m);
					
					//layout2.getChildren().add(button3);
					VBox vbox = new VBox(10);
					//textfields for shooting specifications and the field's size
					TextField angle = new TextField();
					angle.setPrefWidth(25);
					TextField velocity = new TextField();
					velocity.setPrefWidth(25);
					
					//"Fire!!" button
					button2 = new Button();
					button2.setText("Fire!!");
					
					//assigns value to angleShoot and velocityShoot if the entered values are valid
					button2.setOnAction(event -> { 
						boolean doubleCheck = SimpController.isValidAngle(angle);
						boolean doubleCheck2 = SimpController.isDouble(velocity);
						
						if(doubleCheck && doubleCheck2) {
							angleShoot = Double.parseDouble(angle.getText());
							velocityShoot = Double.parseDouble(velocity.getText());
							
						}
					});
					
					//air with the borders
					vbox.setPadding(new Insets(20,20,20,20));
					
					Label angleText = new Label("Please enter angle to shoot with: ");
					Label velocityText = new Label("Please enter velocity to shoot with: ");
					
					vbox.getChildren().addAll(angleText, angle, velocityText, velocity, button2);

					
					group.getChildren().add(canvas);
					group.getChildren().add(vbox);
					
					scene2 = new Scene(group, n, m); //game scene made with the layout predefined
					primaryStage.setScene(scene2); //sets game scene
					
					
					

				}
			});
			
			VBox layout1 = new VBox(10); //layout of initialization scene 
			layout1.setPadding(new Insets(20, 20, 20, 20)); //space from edges of the scene
			layout1.getChildren().addAll(label1,  nValue, mValue, button); //adds button and textfield
			
			scene1 = new Scene(layout1, 400, 180); //initialization scene with the predefined layout
			
			
			
			
			primaryStage.setScene(scene1);
			primaryStage.show();
		}
		

	
	}

