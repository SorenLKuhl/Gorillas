import javafx.application.Application;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class TestJavaFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Canvas canvas;
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("Gorillas");
        Scene scene = new Scene(new StackPane(), 800, 800);
        stage.setScene(scene);
        stage.show();
        
        canvas = new Canvas(400,400);
        
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setFill(Color.GOLD);
        g.fillRect(0,0,400,400);
        
    }


}

