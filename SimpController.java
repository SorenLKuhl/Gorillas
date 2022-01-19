package Gorillas;


import javafx.animation.AnimationTimer;
import javafx.stage.Stage;



public class SimpController {
	
	static boolean p1Turn = true, p2Turn = false;
	
	public static void button1(Stage stage) {
		SimpViewer.button.setOnAction(e -> { //sets the dimensions of the game and checks if the values are valid
			if (SimpModel.isPosInt(SimpViewer.nValue) && SimpModel.isPosInt(SimpViewer.mValue) == true) {
				SimpViewer.angleAndVelocityInterface();
				
				stage.setScene(SimpViewer.scene2); //sets game scene
				SimpViewer.drawMap();
				button2();
				
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
	
	public static void drawProjectile() {
		new AnimationTimer() {
			double t = 0;
            public void handle(long currentNanoTime) {
            	SimpViewer.drawMap();
            	SimpModel.KasteparabelPoint(SimpModel.velocityShoot,SimpModel.angleShoot, t, SimpModel.x0);
            	SimpViewer.projectile();
            	t += 0.10;

            	if (checkCollision()) {
            		stop();
            	}
            	
	                //Delay 30 millisekunder
//	            try {TimeUnit.MILLISECONDS.sleep(30);} catch (InterruptedException e) {}
            }
        }.start();
	}
	
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
	
	public static boolean checkCollision() {
		if(SimpModel.ifIntersectBorder() == true) {
            SimpViewer.playerTurnText();
            return true;
        }
        if(p2Turn) {
            if(SimpModel.collision(SimpModel.n-1,SimpModel.m)) {
            	SimpViewer.player1score++;
            	SimpViewer.hitText();
            	return true;
            }
        }
        else if(p1Turn) {
            if(SimpModel.collision(0,SimpModel.m)) {
            	SimpViewer.player2score++;
            	SimpViewer.hitText();
            	return true;
            }
        }
        return false;
        
	}

	
}

