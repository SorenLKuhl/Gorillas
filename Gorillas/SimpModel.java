package Gorillas;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class SimpModel {

	// Variabler
	
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
	
	// Loader billeder
	public static Image player1		= new Image( "MIG.png" );
	public static Image player2		= new Image( "MIG.png" );
	public static Image baggrund	= new Image( "ral-2005.jpeg" );
	public static Image shot		= new Image( "MIG.png" );
	
	
	// Funktioner
	
	//Checks id the userinput is a integer
	public static boolean isInt(TextField in) { 
		try {
			int k = Integer.parseInt(in.getText());
			System.out.println("hej");
			return true;
		}catch(NumberFormatException e) {
			System.out.println("Error: " + in.getText() + " is not a number" );
			return false;
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------
	
	//Spillet
	public static void spillet() {
        // Tilføjer hastighed og tyngdekraft på kuglen
        bullet.add(speed);
        speed.add(gravity);
        
        /*
        //Tegner billederne
        gc.drawImage( baggrund, 0, 0 ,width,height);
        gc.drawImage( shot, bullet.x - bulletSize*0.5, bullet.y - bulletSize*0.5,bulletSize,bulletSize);
        gc.drawImage( player1, p1.x - playerSize*0.5, p1.y - playerSize*0.5 ,playerSize,playerSize);
        gc.drawImage( player2, p2.x - playerSize*0.5, p2.y - playerSize*0.5,playerSize,playerSize);
        
        */
        
        checkCollision();
        
        //Delay 30 millisekunder
        try {TimeUnit.MILLISECONDS.sleep(30);} catch (InterruptedException e) {}
	}

	//--------------------------------------------------------------------------------------------------------------------------------------------
    
    // Funktion til at skalere fra det ene interval til et andet
    public static int map(int oldValue, int oldMin, int oldMax, int newMin, int newMax) {
    	return (((oldValue - oldMin) * (newMax - newMin)) / (oldMax - oldMin)) + newMin;
    	
    }
    
    //--------------------------------------------------------------------------------------------------------------------------------------------

    // Beregner Distancen mellem 2 punkter
    public static double dist(double x1, double y1, double x2, double y2) {
    	return Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
    }
    
    //--------------------------------------------------------------------------------------------------------------------------------------------

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
    
  //--------------------------------------------------------------------------------------------------------------------------------------------
    
    // Sætter vinkel og hastighed
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
	
  //--------------------------------------------------------------------------------------------------------------------------------------------
    
    //Sætter variabler
    public static void setAllVar() {
        //Opsætning af frame/vindue
        Group root = new Group();
        //Scene theScene = new Scene( root );
        //theStage.setScene( theScene );
        Canvas canvas = new Canvas( width, height );
        root.getChildren().add( canvas );
        GraphicsContext gc = canvas.getGraphicsContext2D();
    }
}

//--------------------------------------------------------------------------------------------------------------------------------------------

	


/*
     class Vector {

    	
    	public double x;
    	public double y;
    	
    	Vector(){
    		x = 0;
    		y = 0;
    	}
    	
    	
    	Vector(double x, double y){
    		this.x = x;
    		this.y = y;
    	}
    	
    	Vector(Vector v){
    		this.x = v.x;
    		this.y = v.y;
    	}
    	
    	public void add(double dx, double dy) {
    		this.x += dx;
    		this.y += dy;
    	}
    	
    	public void add(Vector v) {
    		this.x += v.x;
    		this.y += v.y;
    	}
    	
    	public double getLength() {
    		return (Math.sqrt(Math.pow(this.x, 2)+Math.pow(y, 2)));
    	}
    	
    	public void setLengthOne() {
    		double length = getLength();
    		this.x = this.x/length;
    		this.y = this.y/length;
    	}
    	
    	public double getAngle() {
    		return Math.asin(this.x/getLength());
    	}
    	
    	public void setAngle(double angle) {
    		angle = Math.toRadians(angle);
    		double length = getLength();
    		this.x = Math.cos(angle);
    		this.y = Math.sin(-angle);
    		this.mult(length);
    	}
    	
    	public void mult(double a) {
    		this.x *= a;
    		this.y *= a;
    	}
    	
    	public void setLength(double l) {
    		this.setLengthOne();
    		this.mult(l);
    	}
    	
    	public void limit(double limit) {
    		if(getLength() > limit) {
    			this.setLength(limit);
    		}
    	}
    	
    }
	*/

