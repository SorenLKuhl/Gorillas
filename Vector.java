package Gorillas;

public class Vector {

	
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
