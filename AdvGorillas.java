package Gorillas;
import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.image.Image;


public class AdvGorillas extends Application {	
	public static Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {		//Runs the application
		AdvGorillas.primaryStage = primaryStage;
		primaryStage.setTitle("Gorillas"); //stage
        Image icon = new Image(ClassLoader.getSystemResource("banana.png").toString());		//Sets the icon of the application
        primaryStage.getIcons().add(icon);
		AdvViewer.widthAndHeightInterface();		//Initializes the first window
		AdvController.button1(primaryStage);		//Initializes the first button
		

		
		primaryStage.setScene(AdvViewer.scene1);
		primaryStage.setResizable(false);		//Prevents the user from resizing the windows
		primaryStage.show();
	}


}
