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


public class AdvController {
	
	static boolean p1Turn = true, p2Turn = false;
	
	public static void button1(Stage stage) {
		AdvViewer.button.setOnAction(e -> { //sets the dimensions of the game and checks if the values are valid
			if (AdvModel.isPosInt(AdvViewer.nValue) && AdvModel.isPosInt(AdvViewer.mValue) == true) {
				AdvViewer.angleAndVelocityInterface();
				
				stage.setScene(AdvViewer.scene2); //sets game scene
				AdvViewer.drawMap();
				button2();
				
			}
		});
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
                    AdvModel.x0 = 0;
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
            	AdvViewer.drawMap();
            	AdvModel.KasteparabelPoint(AdvModel.velocityShoot,AdvModel.angleShoot, t, AdvModel.x0);
            	AdvViewer.projectile();
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
        AdvViewer.drawMap();
    }
	
	public static boolean checkCollision() {
		if(AdvModel.ifIntersectBorder() == true) {
            AdvViewer.playerTurnText();
            return true;
        }
        if(p2Turn) {
            if(AdvModel.collision(AdvModel.n-1,AdvModel.m)) {
            	AdvViewer.player1score++;
            	AdvViewer.hitText();
            	return true;
            }
        }
        else if(p1Turn) {
            if(AdvModel.collision(0,AdvModel.m)) {
            	AdvViewer.player2score++;
            	AdvViewer.hitText();
            	return true;
            }
        }
        return false;
        
	}

	
}

