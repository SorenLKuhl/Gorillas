package Gorillas;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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

public class AdvViewer {
		
	
		static TextField nValue, mValue, angle, velocity;
		static Label label1, angleText, velocityText;
		static Button button, button2, button3;
		static Scene scene1, scene2;
		static Stage gameStage;
		static VBox vbox, layout1;
		static Group group;
		static Canvas canvas;
		static GraphicsContext gc;		
		static Image projectile = new Image(ClassLoader.getSystemResource("banana.png").toString());
        static Image background = new Image(ClassLoader.getSystemResource("background.jpeg").toString());
        static Image player1 = new Image(ClassLoader.getSystemResource("player1.png").toString());
        static Image player2 = new Image(ClassLoader.getSystemResource("player2.png").toString());
        static ImageView iv = new ImageView (new Image("log.png"));
        static ChoiceBox<String> choicebox = new ChoiceBox<>();
		static int player1score = 0, player2score = 0;
		


		
		
		public static void widthAndHeightInterface() {
			nValue = new TextField(); //textfield for width
			mValue = new TextField(); //textfield for height
			nValue.setPromptText("Width"); // promptText for width
			mValue.setPromptText("Height"); // promptText for height
			choicebox.getItems().addAll("Beginner","Moderate", "Hard");
			choicebox.setValue("Difficulty");
			label1 = new Label("Enter width and height of the game below"); //description
			button = new Button();
			button.setText("Continue to game"); //button for applying width and height
			
			layout1 = new VBox(10); //layout of initialization scene 
			layout1.setPadding(new Insets(20, 20, 20, 20)); //space from edges of the scene
			layout1.getChildren().addAll(label1,  nValue, mValue,choicebox, button); //adds button and textfield
			
			
			
			
			
			
			scene1 = new Scene(layout1, 400, 250); //initialization scene with the predefined layout
		}
		
		
		public static void angleAndVelocityInterface() {
			AdvModel.n = Integer.parseInt(nValue.getText());
			AdvModel.m = Integer.parseInt(mValue.getText());
			AdvModel.playerSize = AdvModel.n / 50;
			
			group = new Group(); //layout of game
			
			canvas = new Canvas(AdvModel.n,AdvModel.m);
			gc = canvas.getGraphicsContext2D();
			
			vbox = new VBox(10);
			//textfields for shooting specifications and the field's size
			angle = new TextField();
			angle.setMaxWidth(55);
			velocity = new TextField();
			velocity.setMaxWidth(55);
			
			//"Fire!!" button
			button2 = new Button();
			button2.setText("Fire!!");
			
			//air with the borders
			vbox.setPadding(new Insets(20,20,20,20));
			
			angleText = new Label("Banana angle: ");
			velocityText = new Label("Banana velocity: ");
			
			vbox.getChildren().addAll(angleText, angle, velocityText, velocity, button2);

			
			group.getChildren().add(canvas);
			group.getChildren().add(vbox);
			
			scene2 = new Scene(group); //game scene made with the layout predefined
		}
		
		public static void projectile() {	
			gc.drawImage(projectile, AdvModel.position.x - (AdvModel.projectileSize)/2, AdvModel.position.y - (AdvModel.projectileSize)/2, AdvModel.projectileSize, AdvModel.projectileSize);
		}
		
		public static void players() {
			gc.drawImage(player1, AdvModel.player1PosX - AdvModel.playerSize/2 , AdvModel.m - AdvModel.player1PosY - AdvModel.playerSize/2, AdvModel.playerSize, AdvModel.playerSize);
			gc.drawImage(player2, AdvModel.player2PosX - AdvModel.playerSize/2, AdvModel.m - AdvModel.player2PosY - AdvModel.playerSize/2, AdvModel.playerSize, AdvModel.playerSize);
		}
		
		public static void background() {
            gc.drawImage(background, 0, 0, AdvModel.n, AdvModel.m);
        }
		
		public static void playerTurnText() {
			gc.setFont(new Font(30));
			gc.setFill(Color.rgb(5,184,255));
			
			if(AdvController.p1Turn) {
				gc.fillText("Player 2 missed! Player 1's turn to shoot!", AdvModel.n/3, AdvModel.m/6);
			}
			else {
				gc.fillText("Player 1 missed! Player 2's turn to shoot!", AdvModel.n/3, AdvModel.m/6);
				
			}
		}
		public static void hitText() {
			gc.setFont(new Font(30));
			gc.setFill(Color.GREEN);
			if(AdvController.p1Turn) {
				gc.fillText("Player 2's banana hit!", AdvModel.n/2, AdvModel.m/6);
			}
			else {
				gc.fillText("Player 1's banana hit!", AdvModel.n/2, AdvModel.m/6);
				
			}
		}
		public static void winText(){
			gc.setFont(new Font(30));
			gc.setFill(Color.GREEN);
			if(AdvController.p1Turn) {
				gc.fillText("Player 2 has won the game!", AdvModel.n/2, AdvModel.m/6);
			}
			else {
				gc.fillText("Player 2 has won the game!", AdvModel.n/2, AdvModel.m/6);
				
			}
		}
		
		public static void scoreBoard() {
			gc.setFont(new Font(30));
			gc.setFill(Color.BLACK);
			gc.fillText(player1score + " | " +  player2score, AdvModel.n/2, AdvModel.m/16);
		}
		
		public static void drawMap() {
	        background();
	        playerPillar();
	        players();
	        scoreBoard();
	        
		}
		public static void drawAceleration() {
			gc.setFont(new Font(30));
			gc.setFill(Color.rgb(5,184,255));
			gc.fillText("Wind: " + AdvModel.acceleration,AdvModel.n*3/4, AdvModel.m/16);
		}
		
		public static void textFieldRed1(TextField tf) {
			if(!AdvModel.isPosInt(tf)) {
				tf.setStyle("-fx-text-box-border: red;");
			}
			else {
				tf.setStyle("");
			}

		}
		public static void textFieldRed2() {
			if(!AdvModel.isValidAngle(angle)) {
				angle.setStyle("-fx-text-box-border: red;");
			}
			else {
				angle.setStyle("");
			}
			if(!AdvModel.isPosDouble(velocity)) {
				velocity.setStyle("-fx-text-box-border: red;");
			}
			else {
				velocity.setStyle("");
			}
		}
		
		 public static void playerPillar() {
	            iv.setRotate(90);
	            SnapshotParameters params = new SnapshotParameters();
	            params.setFill(Color.TRANSPARENT);
	            Image rotatedImage = iv.snapshot(params, null);
	            gc.drawImage(rotatedImage, AdvModel.player1PosX - 60/2, AdvModel.m - AdvModel.player1PosY + AdvModel.playerSize/2, 60, AdvModel.m/2);
	            
	            gc.drawImage(rotatedImage, AdvModel.player2PosX - 60/2, AdvModel.m - AdvModel.player2PosY + AdvModel.playerSize/2, 60, AdvModel.m/2);
	        }
		 public static void newGameButton() {
			 button3 = new Button();
			 button3.setText("New Game!");
			 button3.setLayoutX(AdvModel.n/2 - AdvModel.n/3/2);
			 button3.setLayoutY(AdvModel.m/2 -AdvModel.m/3/2);
			 button3.setPrefWidth(AdvModel.n/3);
			 button3.setPrefHeight(AdvModel.m/3);
			 group.getChildren().add(button3);
		 }

	}

