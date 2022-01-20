package Gorillas;


import javafx.animation.AnimationTimer;
import javafx.stage.Stage;



public class SimpController {
	
	static boolean p1Turn = true, p2Turn = false;
	
	public static void button1(Stage stage) {//Initializes the first button which starts the game
		SimpViewer.button.setOnAction(e -> { //sets the difficulty and dimensions of the game and checks if the values are valid
			if (SimpModel.isPosInt(SimpViewer.nValue) && SimpModel.isPosInt(SimpViewer.mValue) == true) {
				SimpViewer.angleAndVelocityInterface();
				
				stage.setScene(SimpViewer.scene2); //sets game scene
				SimpViewer.drawMap(); //draws the map
				button2(); //"activates" button2
				
			}
		});
	}
	
	public static void button2() {
		//assigns value to angleShoot and velocityShoot if the entered values are valid
		SimpViewer.button2.setOnAction(event -> { 
					
			if(SimpModel.isValidAngle(SimpViewer.angle) && SimpModel.isPosDouble(SimpViewer.velocity)) {
				SimpModel.angleShoot = Math.toRadians(Double.parseDouble(SimpViewer.angle.getText()));
				SimpModel.velocityShoot = Double.parseDouble(SimpViewer.velocity.getText());
				if(p2Turn) {
                    SimpModel.changeDir();
                	}
                else {
                    SimpModel.x0 = 0;
                	}
				drawProjectile();
				newTurn();
			}
		});
	}
	public static void drawProjectile() {		//Initializes the projectile
		new AnimationTimer() {		//'AnimationTimer' along with 'handle' creates the animation
			double t = 0;		//t is used as a time unit in the equation for the throw
            public void handle(long currentNanoTime) {		//handle runs the code inside of it every 'tick' of the animation
            	SimpViewer.drawMap();		//Draws the map every tick to hide the projectile's previous images
            	//Updates the projectile's position to the tim et
            	SimpModel.KasteparabelPoint(SimpModel.velocityShoot,SimpModel.angleShoot, t, SimpModel.x0);
            	SimpViewer.projectile();	//Draws the prjectile at its current position
            	t += 0.10;		//t is updated every tick which in turn moves the projectile

            	if (checkCollision()) {		//Checks collision every tick and plays a sound, shows the button and stops the animation when true
            		stop();
            	}
            	//Delay to be used in case of errors.
	            //try {TimeUnit.MILLISECONDS.sleep(30);} catch (InterruptedException e) {}
            }
        }.start();
	}
	//Switches player turn
	public static void newTurn() {
        if(p1Turn) {
            p1Turn = false;
            p2Turn = true;
        }
        else {
            p1Turn = true;
            p2Turn = false;
        }
        SimpViewer.drawMap();
    }
	
	public static boolean checkCollision() {//Checks collision of projectile with borders and players
		if(SimpModel.ifIntersectBorder() == true) {//checks with border
            SimpViewer.playerTurnText();
            return true;
        }
        if(p2Turn) {
            if(SimpModel.collision(SimpModel.n-1,SimpModel.m)) { //checks with player1
            	SimpViewer.player1score++;
            	SimpViewer.hitText();
            	return true;
            }
        }
        else if(p1Turn) {
            if(SimpModel.collision(0,SimpModel.m)) { //checks with player1
            	SimpViewer.player2score++;
            	SimpViewer.hitText();
            	return true;
            }
        }
        return false; //false if no collision
        
	}

	
}

