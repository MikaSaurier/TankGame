package tanks.gobj;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall implements Renderable {
	final int x, y, width, height;
	final boolean hasCollisions = true;

	public int getX() {
		return x;
	}
	
	public double getCenterX() {
		return getX() + ((double) getWidth()) / 2;
	}

	public int getY() {
		return y;
	}
	
	public double getCenterY() {
		return getY() + ((double) getHeight()) / 2;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isHasCollisions() {
		return hasCollisions;
	}

	public Wall(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.fillRect(x, y, width, height);
		
	}
	
	public boolean intersects(Circle circle) {
		Rectangle circleBounds = new Rectangle(
				(int) circle.getX(), (int) circle.getY(), circle.getRadius() * 2, circle.getRadius() * 2
		);
		return getBounds().intersects(circleBounds);
		
	}
	
	
}
