package Gorillas;

import java.awt.Point;

import javafx.scene.control.TextField;

public class SimpModel {
	public static double angleShoot, velocityShoot;
	public static int m, n, playerSize, x0 = 0;
	static Vector position = new Vector();
	
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
	
	public static boolean isPosInt(TextField in) {		//Checks if input is a positive integer
		if (isInt(in) && 0 < Integer.parseInt(in.getText())) {
			return true;
		}
		else {
			System.out.println("Error: " + in.getText() + " is not a positive integer");
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
	
	public static void KasteparabelPoint(double velocity, double angle, double t, double x0) {	//Updates the position of the projectile to a given time t
        position.x = KasteparabelX(velocity, angle, t, x0);
        position.y = KasteparabelY(velocity, angle, position.x) + SimpModel.m;
    }
	
	public static void changeDir() {	//Changes the angle of the shot to fit player 2
        angleShoot = Math.PI - angleShoot;
        x0 = n;
    }
	
	public static boolean ifIntersectBorder() {		//Checks if the position of the projectile intersects the border of the screen
		if(position.x > n || position.x < 0 || position.y > m) {
			return true;
		}
		return false;
	}
	
    public static double dist(double x1, double y1, double x2, double y2) {		// Beregner Distancen mellem 2 punkter
        return Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
    }
    
    public static boolean collision(double x1, double y1, double x2, double y2) {	//Checks if the projectile hit the other player
        return ( dist(x1,y1,x2,y2) < (n/50) );
    }





}
