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
	
	
	
	public static void buttons(Stage stage) {
		SimpViewer.button.setOnAction(e -> { //sets the dimensions of the game and checks if the values are valid
			if (SimpModel.isPosInt(SimpViewer.nValue) && SimpModel.isPosInt(SimpViewer.mValue) == true) {
				SimpViewer.angleAndVelocityInterface();
				
				stage.setScene(SimpViewer.scene2); //sets game scene
				SimpViewer.players();
				//assigns value to angleShoot and velocityShoot if the entered values are valid
				SimpViewer.button2.setOnAction(event -> { 
					
					
					if(SimpModel.isValidAngle(SimpViewer.angle) && SimpModel.isPosDouble(SimpViewer.velocity)) {
						SimpModel.angleShoot = Double.parseDouble(SimpViewer.angle.getText());
						if(false) { // player 2 turn
							SimpModel.angleShoot = SimpModel.changeDir(SimpModel.angleShoot);
						}
						SimpModel.velocityShoot = Double.parseDouble(SimpViewer.velocity.getText());
						
						SimpModel.angleShoot = Math.toRadians(SimpModel.angleShoot);
						drawProjectile();
					}

				});
				
			}
		});
	}
	
	public static void drawProjectile() {
		new AnimationTimer() {
			int t = 0;
            public void handle(long currentNanoTime) {
            	SimpModel.KasteparabelPoint(SimpModel.velocityShoot,SimpModel.angleShoot, t, 0);
            	SimpViewer.projectile(SimpModel.position.x,SimpModel.position.y,20,20);
            	
            	// I stedet for 20 og 10, skal det v??re radius p?? player2 og skudet
				if(SimpModel.collision(SimpModel.position,SimpModel.positionPlayer2,20, 10)) {
					System.out.println("Hit!");
				}
				
            	t++;
	                //Delay 30 millisekunder
	                try {TimeUnit.MILLISECONDS.sleep(30);} catch (InterruptedException e) {}
            }
        }.start();
	}
}

