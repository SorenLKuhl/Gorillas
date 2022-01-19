package Gorillas;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

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
		static Image projectile = new Image(ClassLoader.getSystemResource("banana.png").toString());
        static Image background = new Image(ClassLoader.getSystemResource("background.png").toString());
        static Image player1 = new Image(ClassLoader.getSystemResource("player1.png").toString());
        static Image player2 = new Image(ClassLoader.getSystemResource("player2.png").toString());
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
			//assigns values inserted from user to n and m
			SimpModel.n = Integer.parseInt(nValue.getText());
			SimpModel.m = Integer.parseInt(mValue.getText());
			SimpModel.playerSize = SimpModel.n / 50;
			
			group = new Group(); //layout of game
			
			//makes canvas with the right dimensions
			canvas = new Canvas(SimpModel.n,SimpModel.m);
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
			
			//adds the texts, textfields and button to the vbox
			vbox.getChildren().addAll(angleText, angle, velocityText, velocity, button2);

			//adds the vbox and the canvas to the entire group
			group.getChildren().add(canvas);
			group.getChildren().add(vbox);
			
			scene2 = new Scene(group); //game scene made with the layout predefined
		}
		
		public static void projectile() {//draws the projectile
			gc.drawImage(projectile, SimpModel.position.x - (SimpModel.n/70)/2, SimpModel.position.y - (SimpModel.n/70)/2, SimpModel.n/70, SimpModel.n/70);
		}
		
		public static void players() { //draws the players in bottom of both corners of the window
			gc.drawImage(player1, 0, SimpModel.m - SimpModel.playerSize, SimpModel.playerSize, SimpModel.playerSize);
			gc.drawImage(player2, SimpModel.n-SimpModel.playerSize, SimpModel.m - SimpModel.playerSize, SimpModel.playerSize, SimpModel.playerSize);
		}
		
		public static void background() { //draws the background
            gc.drawImage(background, 0, 0, SimpModel.n, SimpModel.m);
        }
		
		public static void playerTurnText() {//draw a specific text if the player misses
			gc.setFont(new Font(30));
			gc.setFill(Color.rgb(5,184,255));
			
			if(SimpController.p1Turn) {//the text depends on whose turn it is
				gc.fillText("Player 2 missed! Player 1's turn to shoot!", SimpModel.n/3, SimpModel.m/6);
			}
			else {
				gc.fillText("Player 1 missed! Player 2's turn to shoot!", SimpModel.n/3, SimpModel.m/6);
				
			}
		}
		
		public static void hitText() {//draws a specific text if the banana hits
			gc.setFont(new Font(30));
			gc.setFill(Color.GREEN);
			if(SimpController.p1Turn) { //the text depends on whose turn it is
				gc.fillText("Player 2's banana hit!", SimpModel.n/2, SimpModel.m/6);
			}
			else {
				gc.fillText("Player 1's banana hit!", SimpModel.n/2, SimpModel.m/6);
				
			}
		}
		
		public static void scoreBoard() {//draws the score of the game
			gc.setFont(new Font(30));
			gc.setFill(Color.BLACK);
			gc.fillText(player1score + " | " +  player2score, SimpModel.n/2, SimpModel.m/16);
		}
		
		public static void drawMap() {//draws the background, the players and the scoreboard
	        SimpViewer.background();
	        SimpViewer.players();
	        SimpViewer.scoreBoard();
		}
	}

