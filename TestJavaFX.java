package Gorillas;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.scene.image.Image;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Scanner;
import java.util.concurrent.TimeUnit ;


public class TestJavaFX extends Application {
	
	//Højde og bredde af vindue/frame
	public static int width = 512;
	public static int height = 512;
	
	// Andre variabler
	public static Vector p1 = new Vector(width*0.2,height*0.75);
	public static Vector p2 = new Vector(width*0.8,height*0.75);
	public static Vector bullet = new Vector(p1);
	public static Vector speed = new Vector(1,0);
	public static Vector gravity = new Vector(0,1);
	
	public static int playerSize = (int) ((height+width)*0.05);
	public static int bulletSize = (int) (playerSize*0.5);
	
	public static boolean p1Turn = true;
	public static boolean p2Turn = false;
	
	public static double angle;
	public static double vel;
	public static double tyngdeAcceleration;
	
	// Scanner til bruger input
	public static Scanner scanner = new Scanner(System.in);	
	
    public static void main(String[] args) {
        launch(args);
    }
    
    
    @Override
    public void start(Stage theStage) 
    {
        theStage.setTitle( "Gorillas" ); //Titel
     
        
        //Opsætning af frame/vindue
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
     
        Canvas canvas = new Canvas( width, height );
        root.getChildren().add( canvas );
     
        GraphicsContext gc = canvas.getGraphicsContext2D();
     
        //Loader billeder
        Image player1	= new Image( "MIG.png" );
        Image player2   = new Image( "MIG.png" );
        Image baggrund	= new Image( "ral-2005.jpeg" );
        Image shot		= new Image( "MIG.png" );
        
        setAngleAndVel();
        
        // Updatere frame/vindue
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	//if(true) {
	                // Tilføjer hastighed og tyngdekraft på kuglen
	                bullet.add(speed);
	                speed.add(gravity);
	                
	                //Tegner billederne
	                gc.drawImage( baggrund, 0, 0 ,width,height);
	                gc.drawImage( shot, bullet.x - bulletSize*0.5, bullet.y - bulletSize*0.5,bulletSize,bulletSize);
	                gc.drawImage( player1, p1.x - playerSize*0.5, p1.y - playerSize*0.5 ,playerSize,playerSize);
	                gc.drawImage( player2, p2.x - playerSize*0.5, p2.y - playerSize*0.5,playerSize,playerSize);
	                
	                
	                
	                checkCollision();
	                
	                //Delay 30 millisekunder
	                try {TimeUnit.MILLISECONDS.sleep(30);} catch (InterruptedException e) {}
            	//}
            	
            }
            
        }.start();
     
        
       
        
        theStage.show();
    }
    
    // Funktion til at skalere fra det ene interval til et andet
    public static int map(int oldValue, int oldMin, int oldMax, int newMin, int newMax) {
    	return (((oldValue - oldMin) * (newMax - newMin)) / (oldMax - oldMin)) + newMin;
    	
    }
    
    // Beregner Distancen mellem 2 punkter
    public static double dist(double x1, double y1, double x2, double y2) {
    	return Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
    }
    
    // Tjekker kollision mellem spiller og bullet
    public static void checkCollision() {
    	double d = 100000;
    	if(p1Turn) {
    		d = dist(p2.x,p2.y,bullet.x,bullet.y) - playerSize*0.5 - bulletSize*0.5;
    	} else if(p2Turn){
    		d = dist(p1.x,p1.y,bullet.x,bullet.y) - playerSize*0.5 - bulletSize*0.5;
    	} 
    	
    	if(d < 0) {
    	   	if(p1Turn) {
        		System.out.println("Player 1 Vandt!");
        	} else if(!p1Turn) {
        		System.out.println("Player 2 Vandt!");
        	}
    	   	try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {}
    	   	System.exit(0);
    	}
    	
    	// Bullet har ramt væg eller gulv
    	if(bullet.x + bulletSize*0.5 > width || bullet.x - bulletSize*0.5 < 0 || bullet.y+bulletSize*0.5 > height) {
    		if(p1Turn) {
    			bullet.x = p2.x;
    			bullet.y = p2.y;
    		}
    		if(p2Turn) {
    			bullet.x = p1.x;
    			bullet.y = p1.y;
    		}
    		p1Turn = !p1Turn;
    		p2Turn = !p2Turn;
    		setAngleAndVel();
    	}
    	
    }
    
 
    
    
    public static void setAngleAndVel() {
    //	System.out.print("\033[H\033[2J"); // Clear console
    	//System.out.flush();
    	if(p1Turn) {
    		System.out.println("Player 1, din tur!");
    	} else if(p2Turn){
    		System.out.println("Player 2, din tur!");
    	}
        System.out.println("Indtast Vinkel:");
        angle = scanner.nextInt();
        System.out.println("Indtast Hastighed:");
        vel = scanner.nextInt();
        
        tyngdeAcceleration = vel*0.025;
        
        //tyngdeAcceleration = 9.81;
        
        //Vinkel
        if(p1Turn) {
        	speed.setAngle(angle);
        } else if(p2Turn) {
        	speed.setAngle(angle+90); // Er lidt usikker på om man bare kan addere med 90 fr at sende skudet i den anden retning
        }
        //Hastighed
        speed.setLength(vel);
        //Tyngde acceleration
        gravity.setLength(tyngdeAcceleration);
    }
    


}

