package tanks.gobj;

public interface Circle {
	
	double getX();
	
	double getY();
	
	int getRadius();
	
	default int getRadiusSquared() {
		return getRadius() * getRadius();
	}
	
	default double getCenterX() {
		return getX() + getRadius();
	}
	
	default double getCenterY() {
		return getY() + getRadius();
	}
		
	default boolean intersects(Circle circle) {
		return getRadiusSquared() > Math.pow(getCenterX() - circle.getCenterX(), 2) + Math.pow(getCenterY() - circle.getCenterY(), 2);
	}

}
