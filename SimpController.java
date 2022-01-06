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


public class SimpController {
	
	static boolean p1Turn = true, p2Turn = false;
	
	public static void button1(Stage stage) {
		SimpViewer.button.setOnAction(e -> { //sets the dimensions of the game and checks if the values are valid
			if (SimpModel.isPosInt(SimpViewer.nValue) && SimpModel.isPosInt(SimpViewer.mValue) == true) {
				SimpViewer.angleAndVelocityInterface();
				
				stage.setScene(SimpViewer.scene2); //sets game scene
				SimpViewer.players();
				
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
            	SimpModel.KasteparabelPoint(SimpModel.velocityShoot,SimpModel.angleShoot, t, SimpModel.x0);
            	SimpViewer.projectile(SimpModel.position.x,SimpModel.position.y,20,20);
            	t += 0.5;
            	if(SimpModel.ifIntersectBorder() == true) {
            		SimpViewer.playerTurnText();
            		stop();
            	}
	                //Delay 30 millisekunder
	            try {TimeUnit.MILLISECONDS.sleep(30);} catch (InterruptedException e) {}
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
        SimpViewer.background();
        SimpViewer.players();
    }
	
}

