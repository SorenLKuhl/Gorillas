package Gorillas;

import javafx.scene.control.TextField;

public class SimpModel {
	public static Double angleShoot, velocityShoot;
	public static int m, n;
	
	public static boolean isInt(TextField in) { //Checks id the userinput is a integer
		try {
			Integer.parseInt(in.getText());
			System.out.println("hej");
			return true;
		}catch(NumberFormatException e) {
			System.out.println("Error: " + in.getText() + " is not a number" );
			return false;
		}
		
	}
	public static boolean isDouble(TextField in) { //Checks id the userinput is a double
		try {
			Double.parseDouble(in.getText());
			System.out.println("hej");
			return true;
		}catch(NumberFormatException e) {
			System.out.println("Error: " + in.getText() + " is not a number" );
			return false;
		}
		
	}
	public static boolean isValidAngle(TextField in) { //Checks id the userinput is an angle
		try {
			Double.parseDouble(in.getText());
			if (Double.parseDouble(in.getText()) >= 0 && Double.parseDouble(in.getText()) <=90) {
				return true;
			}
			else {
				return false;
			}
		}catch(NumberFormatException e) {
			System.out.println("Error: " + in.getText() + " is not a number" );
			return false;
		}
		
	}

}
