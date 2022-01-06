package Gorillas;

import java.awt.Point;

import javafx.scene.control.TextField;

public class SimpModel {
	public static double angleShoot, velocityShoot;
	public static int m, n, playerSize = 40, x0 = 0;
	static Vector position = new Vector();
	
	public static boolean isValidAngle(TextField in) { //Checks id the userinput is an angle
		try {
			Double.parseDouble(in.getText());
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
	
	public static boolean isPosInt(TextField in) {
		if (isInt(in) && 0 < Integer.parseInt(in.getText())) {
			return true;
		}
		else {
			System.out.println("Error: " + in.getText() + " is not a positive integer");
			return false;
		}
	}
	
	public static boolean isPosDouble(TextField in) {
		if (isDouble(in) && 0 < Double.parseDouble(in.getText())) {
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
		return -(-(9.8/(2*velocity*velocity*Math.cos(angle)*Math.cos(angle)))*(x-x0)*(x-x0)+Math.tan(angle)*(x-x0));
	}
	
	public static double KasteparabelX(double velocity, double angle, double t, double x0) {
		return velocity*Math.cos(angle)*t+x0;
	}
	
	public static void KasteparabelPoint(double velocity, double angle, double t, double x0) {
        position.x = KasteparabelX(velocity, angle, t, x0);
        position.y = KasteparabelY(velocity, angle, position.x) + SimpModel.m;
    }
	
	public static void changeDir() {
        angleShoot = Math.PI - angleShoot;
        x0 = n;
    }
	public static boolean ifIntersectBorder() {
		if(position.x > n || position.x < 0 || position.y > m || position.y < 0) {
			return true;
		}
		return false;
	}




}
