package Gorillas;

import java.awt.Point;

import javafx.scene.control.TextField;

public class SimpModel {
	public static double angleShoot, velocityShoot;
	public static int m, n, p1Pos, p1R, p2Pos, p2R;
	static Vector position = new Vector();
	static Vector positionPlayer1 = new Vector(0,SimpModel.m - 40);
	static Vector positionPlayer2 = new Vector(SimpModel.n - 40,SimpModel.m - 40);
	
	
	public static boolean isValidAngle(TextField in) { //Checks id the userinput is an angle
		try {
			Double.parseDouble(in.getText());
			if (Double.parseDouble(in.getText()) >= 0 && Double.parseDouble(in.getText()) <=90) {
				System.out.println("hej");
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
	
	public static boolean isPosInt(TextField in) {
		if (isInt(in) && 0 < Integer.parseInt(in.getText())) {
			System.out.println("hej");
			return true;
		}
		else {
			System.out.println("Error: " + in.getText() + " is not a positive integer");
			return false;
		}
	}
	
	public static boolean isPosDouble(TextField in) {
		if (isDouble(in) && 0 < Double.parseDouble(in.getText())) {
			System.out.println("hej");
			return true;
		}
		else {
			System.out.println("Error: " + in.getText() + " is not a positive number");
			return false;
		}
	}
	
	public static boolean isInt(TextField in) { //Checks id the userinput is a integer
		try {
			Integer.parseInt(in.getText());
			return true;
		}catch(NumberFormatException e) {
			System.out.println("Error: " + in.getText() + " is not an integer" );
			return false;
		}
	}
	
	public static boolean isDouble(TextField in) { //Checks id the userinput is a double
		try {
			Double.parseDouble(in.getText());
			return true;
		}catch(NumberFormatException e) {
			System.out.println("Error: " + in.getText() + " is not a number" );
			return false;
		}
	}
	
	public static double KasteparabelY(double velocity, double angle, double x) {
		return -(-(9.8/(2*velocity*velocity*Math.cos(angle)*Math.cos(angle)))*x*x+Math.tan(angle)*x)+ SimpModel.m;
	}
	
	public static double KasteparabelX(double velocity, double angle, double t, double x0) {
		return velocity*Math.cos(angle)*t+x0;
	}
	
	public static void KasteparabelPoint(double velocity, double angle, double t, double x0) {
		
		position.x = KasteparabelX(velocity, angle, t, x0);
		position.y = KasteparabelY(velocity, angle, position.x);
	}
	
	public static double changeDir(double angle) {
        double rad = Math.toRadians(angle);
        rad = Math.PI - rad;
        return Math.toDegrees(rad);
    }
	
	
    // Beregner Distancen mellem 2 punkter
    public static double dist(double x1, double y1, double x2, double y2) {
    	return Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
    }
	
    
	public static boolean collision(double x1, double y1, double x2, double y2, double rad1, double rad2) {
		return ( dist(x1,y1,x2,y2) < (rad1 + rad2) );
	}
	
	public static boolean collision(Vector v1, Vector v2, double rad1, double rad2) {
		return ( dist(v1.x,v1.y,v2.x,v2.y) < (rad1 + rad2) );
	}
	

}
