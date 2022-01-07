package Gorillas;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
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
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;



import javafx.animation.*;

public class SimpViewer {
		
	
		static TextField nValue, mValue, angle, velocity;
		static Label label1, angleText, velocityText;
		static Button button, button2;
		static Scene scene1, scene2;
		static Stage gameStage;
		static VBox vbox, layout1;
		static Group group;
		static Canvas canvas;
		static GraphicsContext gc;		
		static Image projectile = new Image("banana.png");
		static Image background = new Image("background.jpeg");
		static Image player1 = new Image("player1.png");
		static Image player2 = new Image("player1.png");
		static int player1score = 0, player2score = 0;
		


		
		
		public static void widthAndHeightInterface() {
			nValue = new TextField(); //textfield for width
			mValue = new TextField(); //textfield for height
			label1 = new Label("Enter width and height of the game below"); //description
			button = new Button();
			button.setText("Continue to game"); //button for applying width and height
			
			layout1 = new VBox(10); //layout of initialization scene 
			layout1.setPadding(new Insets(20, 20, 20, 20)); //space from edges of the scene
			layout1.getChildren().addAll(label1,  nValue, mValue, button); //adds button and textfield
			
			scene1 = new Scene(layout1, 400, 180); //initialization scene with the predefined layout
		}
		
		
		public static void angleAndVelocityInterface() {
			SimpModel.n = Integer.parseInt(nValue.getText());
			SimpModel.m = Integer.parseInt(mValue.getText());
			SimpModel.playerSize = SimpModel.n / 50;
			
			group = new Group(); //layout of game
			
			canvas = new Canvas(SimpModel.n,SimpModel.m);
			gc = canvas.getGraphicsContext2D();
			
			vbox = new VBox(10);
			//textfields for shooting specifications and the field's size
			angle = new TextField();
			angle.setPrefWidth(25);
			velocity = new TextField();
			velocity.setPrefWidth(25);
			
			//"Fire!!" button
			button2 = new Button();
			button2.setText("Fire!!");
			
			//air with the borders
			vbox.setPadding(new Insets(20,20,20,20));
			
			angleText = new Label("Please enter angle to shoot with: ");
			velocityText = new Label("Please enter velocity to shoot with: ");
			
			vbox.getChildren().addAll(angleText, angle, velocityText, velocity, button2);

			
			group.getChildren().add(canvas);
			group.getChildren().add(vbox);
			
			scene2 = new Scene(group); //game scene made with the layout predefined
		}
		
		public static void projectile(double x, double y, double w, double h) {	
			gc.drawImage(projectile, x, y, w, h);

			
		}
		
		public static void players() {
			gc.drawImage(player1, 0, SimpModel.m - SimpModel.playerSize, SimpModel.playerSize, SimpModel.playerSize);
			gc.drawImage(player2, SimpModel.n-SimpModel.playerSize, SimpModel.m - SimpModel.playerSize, SimpModel.playerSize, SimpModel.playerSize);
		}
		
		public static void background() {
            gc.drawImage(background, 0, 0, SimpModel.n, SimpModel.m);
        }
		
		public static void playerTurnText() {
			gc.setFont(new Font(30));
			gc.setFill(Color.rgb(5,184,255));
			
			if(SimpController.p1Turn) {
				gc.fillText("Player 2 missed! Player 1's turn to shoot!", SimpModel.n/3, SimpModel.m/6);
			}
			else {
				gc.fillText("Player 1 missed! Player 2's turn to shoot!", SimpModel.n/3, SimpModel.m/6);
				
			}
		}
		
		public static void hitText() {
			gc.setFont(new Font(30));
			gc.setFill(Color.GREEN);
			if(SimpController.p1Turn) {
				gc.fillText("Player 2's banana hit!", SimpModel.n/2, SimpModel.m/6);
			}
			else {
				gc.fillText("Player 1's banana hit!", SimpModel.n/2, SimpModel.m/6);
				
			}
		}
		
		public static void scoreBoard() {
			gc.setFont(new Font(30));
			gc.setFill(Color.BLACK);
			gc.fillText(player1score + " | " +  player2score, SimpModel.n/2, SimpModel.m/16);
		}
		
		public static void drawMap() {
	        SimpViewer.background();
	        SimpViewer.players();
	        SimpViewer.scoreBoard();
		}
		
		

	
	}

