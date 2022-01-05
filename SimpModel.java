package Gorillas;

import java.awt.Point;

import javafx.scene.control.TextField;

public class SimpModel {
	public static double angleShoot, velocityShoot;
	public static int m, n, playerSize = 40;
	static Vector position = new Vector();
	
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
		return -(-(9.8/(2*velocity*velocity*Math.cos(angle)*Math.cos(angle)))*x*x+Math.tan(angle)*x);
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

}
