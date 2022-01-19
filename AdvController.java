package Gorillas;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import javax.sound.sampled.*;
import java.io.File;



public class AdvController {
	
	static boolean p1Turn = true, p2Turn = false;
	
	public static void button1(Stage stage) {		//Initializes the first button which starts the game
		AdvViewer.button.setOnAction(e -> { 	//sets the difficulty and dimensions of the game and checks if the values are valid
			if (AdvModel.isValidInt(AdvViewer.nValue) && AdvModel.isValidInt(AdvViewer.mValue) && AdvModel.checkChoice()) {
				AdvModel.difficulty = AdvViewer.choicebox.getValue();
				InitializeGameInterface(stage);		//Initializes the game
			}
			else {
				AdvViewer.textFieldRed1(AdvViewer.nValue);		//If the values are invalid then the textfields' borders turn red to indicate this
				AdvViewer.textFieldRed1(AdvViewer.mValue);
			}
		});
	}
	
	public static void InitializeGameInterface(Stage stage) {		//Initializes the game
		AdvViewer.angleAndVelocityInterface();		//Initializes the layout of the game
		//Sets position and sizes:
		AdvModel.setXPos();
		AdvModel.setYPos();
		AdvModel.setProjectileSize(AdvModel.n/75);
		AdvModel.setPlayerSize(AdvModel.n / 25);
		//sets game scene
		stage.setScene(AdvViewer.scene2); 		
		//Generates pillars and wind if necessary and draws the map
		AdvModel.pillarHeight(); 	
		AdvViewer.drawMap();
		AdvModel.addAcceleration();
		AdvViewer.drawAceleration();
		//Initializes the second button
		button2();
	}
	
	public static void button2() {		//Initializes the the second button which throws the projectile
		AdvViewer.button2.setOnAction(event -> {
			//The button only works if the entered values are valid
			if(AdvModel.isValidAngle(AdvViewer.angle) && AdvModel.isPosDouble(AdvViewer.velocity)) {
				//assigns value to angleShoot and velocityShoot
				AdvModel.angleShoot = Math.toRadians(Double.parseDouble(AdvViewer.angle.getText()));
				AdvModel.velocityShoot = Double.parseDouble(AdvViewer.velocity.getText());
				//Changes the starting point of the shot to fit the player turn
				if(p2Turn) {
					AdvModel.changeDir();
                }
                else {
                	AdvModel.x0 = AdvModel.player1PosX;
                }
				AdvViewer.button2.setVisible(false);		//Hides the button during the throw
				drawProjectile();		//Initializes the projectile
				newTurn();		//Switches player turn
			}
			else {
				//If the values of the angle and velocity are invalid then the textfields' borders turn red to indicate this
				AdvViewer.textFieldRed2();		
			}
		});
	}
	
	public static void button3() {		//Initializes the third button which starts a new game by returning to the first window
		AdvViewer.button3.setOnAction(event1 -> {
			AdvGorillas.primaryStage.setScene(AdvViewer.scene1);
			AdvViewer.player1score = 0;
			AdvViewer.player2score = 0;
		});
	}
	
	public static void drawProjectile() {		//Initializes the projectile
		new AnimationTimer() {		//'AnimationTimer' along with 'handle' creates the animation
			double t = 0;		//t is used as a time unit in the equation for the throw
            public void handle(long currentNanoTime) {		//handle runs the code inside of it every 'tick' of the animation
            	AdvViewer.drawMap();		//Draws the map every tick to hide the projectile's previous images
            	//Updates the projectile's position to the tim et
            	AdvModel.KasteparabelPoint(AdvModel.velocityShoot,AdvModel.angleShoot, t, AdvModel.x0, AdvModel.acceleration);
            	AdvViewer.projectile();		//Draws the prjectile at its current position
            	t += 0.10;		//t is updated every tick which in turn moves the projectile

            	if (checkCollision()) {		//Checks collision every tick and plays a sound, shows the button and stops the animation when true
            		playSound("src/splat.wav");
            		AdvViewer.button2.setVisible(true);
            		stop();
            	}
            	//Delay to be used in case of errors.
	            //try {TimeUnit.MILLISECONDS.sleep(30);} catch (InterruptedException e) {}
            }
        }.start();
	}
	
	public static void newTurn() {		//Switches player turn
        p1Turn = !p1Turn;
        p2Turn = !p2Turn;
    }
	
	public static boolean checkCollision() {		//Checks collision of projectile with borders, pillars and players
		if(AdvModel.ifIntersectBorder()) {		//Checks border collision and then displays text and updates win if true
            AdvViewer.playerTurnText();
            AdvModel.addAcceleration();
    		AdvViewer.drawAceleration();
            return true;
        }
		else if (AdvModel.allPillarIntersect()) {		//Checks collision with pillars and then displays text and updates win if true
			AdvViewer.playerTurnText();
			AdvModel.addAcceleration();
    		AdvViewer.drawAceleration();
			return true;
		}
		//Checks collision with players depending on whose turn it is.
		//The if-statement checks the opposite player turn because the throw changes the turn
        if(p2Turn) {		 
            if(AdvModel.collision(AdvModel.player2PosX,AdvModel.player2PosY, AdvModel.playerSize)) {
            	AdvViewer.player1score++;
            	//If the throw hit and the player's score then increases to three, the player wins the game
            	if(AdvViewer.player1score == 3) {
            		AdvViewer.winText();		//Text addressing that the player has won
            		AdvViewer.newGameButton();		//Button3 for starting a new game
            		button3();
            	}
            	else {		//If a player has not won yet the map resets with new positions and a hit text is displayed
            		InitializeGameInterface(AdvGorillas.primaryStage);
            		AdvViewer.hitText();
            	}
            	return true;
            }
        }
        //Identical to above but for player 2
        else if(p1Turn) {
            if(AdvModel.collision(AdvModel.player1PosX,AdvModel.player1PosY, AdvModel.playerSize)) {
            	AdvViewer.player2score++;
            	if(AdvViewer.player2score == 3) {
            		AdvViewer.winText();
            		AdvViewer.newGameButton();
            		button3();
            	}
            	else {
            		InitializeGameInterface(AdvGorillas.primaryStage);
            		AdvViewer.hitText();
            	}
            	return true;
            }
        }
        return false;
        
	}
	
    public static void playSound(String path) {		//Method used to play a sound defined by its file-path
        try {
            AudioInputStream input = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(input);
            clip.start();
        }
        catch(Exception e) {
            System.out.println("Error: Could not play music");
        }
    }
    
 
}
	        

