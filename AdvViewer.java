package Gorillas;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

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
        static Image background = new Image(ClassLoader.getSystemResource("background.png").toString());
        static Image player1 = new Image(ClassLoader.getSystemResource("player1.png").toString());
        static Image player2 = new Image(ClassLoader.getSystemResource("player2.png").toString());
        static ImageView iv = new ImageView (new Image("log.png"));
        static ChoiceBox<String> choicebox = new ChoiceBox<>();
		static int player1score = 0, player2score = 0, textSize;
		static MenuBar menuBar;
		
		


		
		
		public static void widthAndHeightInterface() {		//Initializes the first window
			nValue = new TextField(); 		//textfield for width
			nValue.setPromptText("Width"); 		// promptText for width
			
			mValue = new TextField(); 		//textfield for height
			mValue.setPromptText("Height"); 		// promptText for height
			
			choicebox.getItems().addAll("Beginner","Moderate", "Hard");		//choicebox containing the difficulties
			choicebox.setValue("Difficulty");
			
			label1 = new Label("Enter width and height of the game below (min. 500 & max. 1000)"); //Prompts the user to enter values
			
			button = new Button();		//button for applying width and height
			button.setText("Continue to game"); 
			
			layout1 = new VBox(10); 		//layout of first window scene 
			layout1.setPadding(new Insets(20, 20, 20, 20)); 		//space from edges of the scene
			layout1.getChildren().addAll(label1,  nValue, mValue,choicebox, button); 		//adds labels, button and textfields to the layout
			
			scene1 = new Scene(layout1, 400, 250); //initialization scene with the predefined layout
		}
		
		public static void angleAndVelocityInterface() {		//Initializes the second window containing the game
			AdvModel.n = Integer.parseInt(nValue.getText());		//Values put in textfields for width and height gets defined
			AdvModel.m = Integer.parseInt(mValue.getText());
			textSize = AdvModel.n/30;
			menu();		//Initializes the selection menu
			gameLayout();		//Initializes the layout of the game
		}

		public static void menu() {		//Initializes the selection menu
			Menu fileMenu = new Menu("_Menu");		
			
			MenuItem newGame = new MenuItem("New game...");		//New game button added to menu
			newGame.setOnAction(e -> {
        		newGameButton();
        		AdvController.button3();
			});
			//Items are added to the menu
			fileMenu.getItems().add(newGame);
			fileMenu.getItems().add(new SeparatorMenuItem());
			fileMenu.getItems().add(new MenuItem("Back to game..."));
			
			menuBar = new MenuBar();
			menuBar.getMenus().addAll(fileMenu);
		}
		
		public static void gameLayout() {		//Initializes the layout of the game
			group = new Group(); 		//layout of game kept as a group
			canvas = new Canvas(AdvModel.n,AdvModel.m);		//canvas for displaying the game
			gc = canvas.getGraphicsContext2D();		//GraphicsContext for drawing iamges
			vbox = new VBox(10);		//vbox for the textfields, labels, button and menu
			vbox.setPadding(new Insets(20,20,20,20));
			
			//textfields and labels for shooting specifications and the field's size
			angleText = new Label("Banana angle: ");
			angleText.setTextFill(Color.WHITE);
			angle = new TextField();
			angle.setMaxWidth(55);
			velocityText = new Label("Banana velocity: ");
			velocityText.setTextFill(Color.WHITE);
			velocity = new TextField();
			velocity.setMaxWidth(55);
			
			button2 = new Button();		//button2 for throwing tje projectile
			button2.setText("Fire!!");
			
			
			vbox.getChildren().addAll(menuBar, angleText, angle, velocityText, velocity, button2);		//Items are added to the vbox
			//vbox and canvas are added to group
			group.getChildren().add(canvas);
			group.getChildren().add(vbox);
			
			scene2 = new Scene(group); //game scene set to layout defined in group
		}
		
		public static void projectile() {		//Draws the projectile on to the canvas at the position defined in AdvModel
			gc.drawImage(projectile, AdvModel.position.x - (AdvModel.projectileSize)/2, AdvModel.position.y - (AdvModel.projectileSize)/2, AdvModel.projectileSize, AdvModel.projectileSize);
		}
		
		public static void players() {		//Draws the players on to the canvas at their position defined in AdvModel
			gc.drawImage(player1, AdvModel.player1PosX - AdvModel.playerSize/2, AdvModel.player1PosY - AdvModel.playerSize/2, AdvModel.playerSize, AdvModel.playerSize);
			gc.drawImage(player2, AdvModel.player2PosX - AdvModel.playerSize/2, AdvModel.player2PosY - AdvModel.playerSize/2, AdvModel.playerSize, AdvModel.playerSize);
		}
		
		public static void background() {		//Draws the background on to the canvas at the top left corner, with size equal to the size of the window
            gc.drawImage(background, 0, 0, AdvModel.n, AdvModel.m);
        }
		
		public static void playerTurnText() {		//Displays text when a player misses
			gc.setFont(new Font(textSize));
			gc.setFill(Color.RED);
			
			if(AdvController.p1Turn) {
				gc.fillText("Player 2 missed! Player 1's turn to shoot!", AdvModel.n/3, AdvModel.m/6);
			}
			else {
				gc.fillText("Player 1 missed! Player 2's turn to shoot!", AdvModel.n/3, AdvModel.m/6);
				
			}
		}
		public static void hitText() {		//Displays text when a player hits their throw
			gc.setFont(new Font(textSize));
			gc.setFill(Color.WHITE);
			if(AdvController.p1Turn) {
				gc.fillText("Player 2's banana hit! Player 1's turn to shoot!", AdvModel.n/3, AdvModel.m/6);
			}
			else {
				gc.fillText("Player 1's banana hit! Player 2's turn to shoot!", AdvModel.n/3, AdvModel.m/6);
				
			}
		}
		public static void winText(){		//Displays text when a player reaches 3 points and wins the game
			gc.setFont(new Font(textSize));
			gc.setFill(Color.WHITE);
			if(AdvController.p1Turn) {
				gc.fillText("Player 2 has won the game!", AdvModel.n/2, AdvModel.m/6);
			}
			else {
				gc.fillText("Player 1 has won the game!", AdvModel.n/2, AdvModel.m/6);
				
			}
		}
		
		public static void scoreBoard() {		//Displays the current player scores at the top of the screen
			gc.setFont(new Font(textSize));
			gc.setFill(Color.BLACK);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.fillText(player1score + " | " +  player2score, AdvModel.n/2, AdvModel.m/16);
			gc.setTextAlign(TextAlignment.LEFT);
		}
		
		public static void drawMap() {		//Draws visuals of the map. 
	        background();
	        pillar(AdvModel.player1PosX - AdvModel.n/20, AdvModel.player1PosY + AdvModel.playerSize/2);
	        pillar(AdvModel.player2PosX - AdvModel.n/20, AdvModel.player2PosY + AdvModel.playerSize/2);
	        if (!choicebox.getValue().equals("Beginner")) {		//the pillar obstacles are only drawn if difficulty is moderate or hard
	        	pillar(AdvModel.n/3 - AdvModel.n/20, AdvModel.pillar1H);
	        	pillar(AdvModel.n/2 - AdvModel.n/20, AdvModel.pillar2H);
	        	pillar(2 * AdvModel.n/3 - AdvModel.n/20, AdvModel.pillar3H);
	        }
	        players();
	        scoreBoard();
	        
		}
		public static void drawAceleration() {		//Displays the current wind resistance
			gc.setFont(new Font(textSize));
			gc.setFill(Color.rgb(5,184,255));
			gc.fillText("Wind: " + AdvModel.acceleration,AdvModel.n*3/4, AdvModel.m/16);
		}
		
		public static void textFieldRed1(TextField tf) {		//Sets the first 2 texfields' borders red if the values are invalid
			if(!AdvModel.isValidInt(tf)) {
				tf.setStyle("-fx-text-box-border: red;");
			}
			else {
				tf.setStyle("");
			}

		}
		
		public static void textFieldRed2() {		//Sets the second texfields' borders red if the values are invalid
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
		
		 public static void pillar(int x, int y) {		//Draws a pillar at a given point (x,y)
	            iv.setRotate(90);		//Rotates the image 90 degrees as the image used is horizontal
	            SnapshotParameters params = new SnapshotParameters();
	            params.setFill(Color.TRANSPARENT);
	            Image rotatedImage = iv.snapshot(params, null);
	            gc.drawImage(rotatedImage, x, y, AdvModel.n/10, 3 * AdvModel.m/4);
	        }
		 
		 public static void newGameButton() {		//Displays the 'new game' button
			 button3 = new Button();
			 button3.setText("New Game!");
			 button3.setLayoutX(AdvModel.n/2 - AdvModel.n/3/2);
			 button3.setLayoutY(AdvModel.m/2 -AdvModel.m/3/2);
			 button3.setPrefWidth(AdvModel.n/3);
			 button3.setPrefHeight(AdvModel.m/3);
			 group.getChildren().add(button3);
		 }

	}

