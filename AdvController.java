package Gorillas;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
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
import javax.sound.sampled.*;
import java.io.File;



public class AdvController {
	
	static boolean p1Turn = true, p2Turn = false;
	
	public static void button1(Stage stage) {
		AdvViewer.button.setOnAction(e -> { //sets the dimensions of the game and checks if the values are valid
			if (AdvModel.isPosInt(AdvViewer.nValue) && AdvModel.isPosInt(AdvViewer.mValue) && AdvModel.checkChoice()) {
				AdvModel.difficulty = AdvViewer.choicebox.getValue();
				InitializeGameInterface(stage);
			}
			else {
				AdvViewer.textFieldRed1(AdvViewer.nValue);
				AdvViewer.textFieldRed1(AdvViewer.mValue);
			}
		});
	}
	
	public static void InitializeGameInterface(Stage stage) {
		AdvViewer.angleAndVelocityInterface();
		AdvModel.setXPos();
		AdvModel.setYPos();
		AdvModel.setProjectileSize(AdvModel.n/70);
		AdvModel.setPlayerSize(AdvModel.n / 25);
		stage.setScene(AdvViewer.scene2); //sets game scene
		AdvViewer.drawMap();
		AdvModel.addAcceleration();
		AdvViewer.drawAceleration();
		button2();
	}
	
	public static void button2() {
		//assigns value to angleShoot and velocityShoot if the entered values are valid
		AdvViewer.button2.setOnAction(event -> { 
					
			if(AdvModel.isValidAngle(AdvViewer.angle) && AdvModel.isPosDouble(AdvViewer.velocity)) {
				AdvModel.angleShoot = Math.toRadians(Double.parseDouble(AdvViewer.angle.getText()));
				AdvModel.velocityShoot = Double.parseDouble(AdvViewer.velocity.getText());
				if(p2Turn) {
					AdvModel.changeDir();
                	}
                else {
                	AdvModel.x0 = AdvModel.player1PosX;
                	}
				AdvViewer.button2.setVisible(false);
				drawProjectile();
				
				newTurn();
			}
			else {
				AdvViewer.textFieldRed2();
				
			}
		});
	}
	public static void button3() {
		AdvViewer.button3.setOnAction(event1 -> {
			AdvGorillas.primaryStage.setScene(AdvViewer.scene1);
			AdvViewer.player1score = 0;
			AdvViewer.player2score = 0;
		});
	}
	
	public static void drawProjectile() {
		new AnimationTimer() {
			double t = 0;
            public void handle(long currentNanoTime) {
            	AdvViewer.drawMap();
            	AdvModel.KasteparabelPoint(AdvModel.velocityShoot,AdvModel.angleShoot, t, AdvModel.x0, AdvModel.acceleration);
            	AdvViewer.projectile();
            	t += 0.10;

            	if (checkCollision()) {
            		playSound("src/splat.wav");
            		AdvModel.addAcceleration();
            		AdvViewer.drawAceleration();
            		AdvViewer.button2.setVisible(true);
            		stop();
            	}
            	
	                //Delay 30 millisekunder
//	            try {TimeUnit.MILLISECONDS.sleep(30);} catch (InterruptedException e) {}
            }
        }.start();
	}
	
	public static void newTurn() {
         p1Turn = !p1Turn;
         p2Turn = !p2Turn;
        AdvViewer.drawMap();
    }
	
	public static boolean checkCollision() {
		if(AdvModel.ifIntersectBorder()) {
            AdvViewer.playerTurnText();
            return true;
        }
		else if (AdvModel.ifIntersectPillar(AdvModel.player1PosX - AdvModel.n/20, AdvModel.player1PosY + AdvModel.playerSize/2) || 
				 AdvModel.ifIntersectPillar(AdvModel.player2PosX - AdvModel.n/20, AdvModel.player2PosY + AdvModel.playerSize/2)) {
			AdvViewer.playerTurnText();
			return true;
		}
        if(p2Turn) {
            if(AdvModel.collision(AdvModel.player2PosX,AdvModel.player2PosY, AdvModel.playerSize)) {
            	AdvViewer.player1score++;
            	if(AdvViewer.player1score == 3) {
            		AdvViewer.winText();
            		AdvViewer.newGameButton();
            		button3();
            	}
            	else {
            		AdvViewer.hitText();
            	}
            	return true;
            }
        }
        else if(p1Turn) {
            if(AdvModel.collision(AdvModel.player1PosX,AdvModel.player1PosY, AdvModel.playerSize)) {
            	AdvViewer.player2score++;
            	if(AdvViewer.player2score == 3) {
            		AdvViewer.winText();
            		AdvViewer.newGameButton();
            		button3();
            	}
            	else {
            		AdvViewer.hitText();
            	}
            	return true;
            }
        }
        return false;
        
	}
	
    public static void playSound(String path) {
        try {
            AudioInputStream input = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(input);
            clip.start();
        }
        catch(Exception e) {
            System.out.println("Error: Could not play music");
            e.printStackTrace();
        }
    }
    
 
}
	        

