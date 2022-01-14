package Gorillas;
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
