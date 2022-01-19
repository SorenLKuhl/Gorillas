package Gorillas;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class SimpGorillas extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Gorillas"); //stage
        Image icon = new Image(ClassLoader.getSystemResource("banana.png").toString());
        primaryStage.getIcons().add(icon);
		
		SimpViewer.widthAndHeightInterface();

		SimpController.button1(primaryStage);
		
		primaryStage.setScene(SimpViewer.scene1);
		primaryStage.setResizable(false);
		primaryStage.show();
	}


}
