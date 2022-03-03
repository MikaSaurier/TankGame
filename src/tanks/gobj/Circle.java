package tanks.gobj;

import java.awt.Point;

public interface Circle {
	
	double getX();
	
	double getY();
	
	double getLastX();
	
	double getLastY();
	
	int getRadius();
	
	default double getCenterX() {
		return getX() + getRadius();
	}
	
	default double getCenterY() {
		return getY() + getRadius();
	}
	
	default double distanceSquared(Circle circle) {
		double diffX = getCenterX() - circle.getCenterX();
		double diffY = getCenterY() - circle.getCenterY();
		
		return diffX * diffX + diffY * diffY;
	}
	
	default double distanceSquared(Point point) {
		double diffX = getCenterX() - point.getX();
		double diffY = getCenterY() - point.getY();
		
		return diffX * diffX + diffY * diffY;
	}
	
	default double distanceTo(Circle circle) {
		return Math.sqrt(distanceSquared(circle));
	}
		
	default boolean intersects(Circle circle) {
		return Math.pow(getRadius() + circle.getRadius(), 2) > distanceSquared(circle);
	}

}
