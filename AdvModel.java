package Gorillas;

import java.awt.Point;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AdvModel {
	public static double angleShoot, velocityShoot;
	public static int m, n, playerSize, x0 = 0, player1PosY , player2PosY, projectileSize, acceleration, player1PosX , player2PosX, pillar1H, pillar2H, pillar3H; 
	static Vector position = new Vector();
	public static String difficulty;
	
	public static boolean isValidAngle(TextField in) {		//Checks if the userinput is a valid angle
		try {
			Double.parseDouble(in.getText());		//Checks if input is a double between 0 and 90
			if (Double.parseDouble(in.getText()) >= 0 && Double.parseDouble(in.getText()) <=90) {
				return true;
			}
			else {
				System.out.println("Error: " + in.getText() + " is not a valid angle" );
				return false;
			}
		}catch(NumberFormatException e) {
			System.out.println("Error: " + in.getText() + " is not a number" );
			return false;
		}
	}
	
	public static boolean isValidInt(TextField in) {		//Checks if input is a positive integer
		if (isInt(in) && 500 <= Integer.parseInt(in.getText())) {
			return true;
		}
		else {
			System.out.println("Error: " + in.getText() + " is not a valid integer");
			return false;
		}
	}
	
	public static boolean isPosDouble(TextField in) {		//Checks if the input is a positive double
		if (isDouble(in) && 0 < Double.parseDouble(in.getText())) {
			return true;
		}
		else {
			System.out.println("Error: " + in.getText() + " is not a positive number");
			return false;
		}
	}
	
	public static boolean isInt(TextField in) { //Checks if the userinput is a integer
		try {
			Integer.parseInt(in.getText());
			return true;
		}catch(NumberFormatException e) {
			System.out.println("Error: " + in.getText() + " is not an integer" );
			return false;
		}
	}
	
	public static boolean isDouble(TextField in) { //Checks if the userinput is a double
		try {
			Double.parseDouble(in.getText());
			return true;
		}catch(NumberFormatException e) {
			System.out.println("Error: " + in.getText() + " is not a number" );
			return false;
		}
	}
	
	//Equations for a projectile's movement:
	public static double KasteparabelY(double velocity, double angle, double x) {	//Function for the projectile's y-position to a given x position
		return -(-(9.8/(2*velocity*velocity*Math.cos(angle)*Math.cos(angle)))*(x-x0)*(x-x0)+Math.tan(angle)*(x-x0));
	}
	
	public static double KasteparabelX(double velocity, double angle, double t, double x0) {	//Function for the projectile's x position to a given time t
		return velocity*Math.cos(angle)*t+x0;
	}
	
	public static void KasteparabelPoint(double velocity, double angle, double t, double x0, double a) {	//Updates the position of the projectile to a given time t
        position.x = KasteparabelX(velocity, angle, t, x0);
        //position.y = KasteparabelY(velocity, angle, position.x) + SimpModel.m;
        if(AdvController.p2Turn) {
        	position.y = KasteparabelY(velocity, angle, position.x) + player1PosY;
        } else {
        	position.y = KasteparabelY(velocity, angle, position.x) + player2PosY;
        }
        
		if(difficulty.equals("Hard")) {
			position.x = (velocity+a*t)*Math.cos(angle)*t+x0;
		}
        
    }
	
	public static void changeDir() {	//Changes the angle of the shot to fit player 2
        angleShoot = Math.PI - angleShoot;
        x0 = player2PosX;
    }
	
	public static boolean ifIntersectBorder() {	//Checks if the position of the projectile intersects the border of the screen
		if(position.x > n || position.x < 0 || position.y > m) {
			return true;
		}
		return false;
	}

    public static double dist(double x2, double y2) {// calculates the distance between a point (most likely a player or obstacle) and the projectile
        return Math.sqrt(Math.pow((x2-position.x),2)+Math.pow((y2-position.y),2));
    }
    
    public static boolean collision(double x2, double y2) {//Checks if the projectile is within "hit distance" of the given point
        return ( dist(x2,y2) < projectileSize );
    }
    
    public static boolean collision(double x2, double y2, int objectSize) {//Checks if the projectile is within "hit distance" of the given point
        return ( dist(x2,y2) < objectSize/2 + projectileSize/2 );
    }
    
    public static void pillarHeight() {
    	pillar1H =  (int) (m - (Math.random()*m)*0.75);
    	pillar2H =  (int) (m - (Math.random()*m)*0.75);
    	pillar3H =  (int) (m - (Math.random()*m)*0.75);
    }
    
    public static boolean ifIntersectPillar( double xDim, double yDim) {
    	if ((xDim - projectileSize/2) <= position.x && (xDim + n/10 + projectileSize/2) >= position.x && (yDim <= position.y)) { //
    		return true;
    	}
    	else {
    		return false;
    	}
    }    
    public static void setXPos() {
    	player1PosX = n/10;
    	player2PosX = n - n/10;
    }


    public static void setYPos() {
    	player1PosY = (int) (m - (Math.random()*m)*0.5) - playerSize;//
    	player2PosY = (int) (m - (Math.random()*m)*0.5) - playerSize;//
    }
    
    
    public static void setProjectileSize(int newSize) {
    	projectileSize = newSize;
    }
    
    public static boolean checkChoice() { //Checks if the userinput is a double
        String s = AdvViewer.choicebox.getValue();
        if (s.equals("Beginner") || s.equals("Moderate") || s.equals("Hard")) {
            return true;
        }
        else {
            return false;
        }
        
    }
    
    public static void addAcceleration() {
    	if(difficulty.equals("Hard")) {
    		acceleration = (int) (Math.random() * 10 - 5);
    	}
    }
    
    public static void setPlayerSize(int size) {
    	playerSize = size;
    }
	

}
