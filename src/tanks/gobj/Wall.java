package tanks.gobj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Wall implements Renderable {
	final int x, y, width, height;
	final boolean hasCollisions = true;
	final List<Point> pointsOnLeft, pointsOnRight, pointsOnTop, pointsOnBottom;

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
		pointsOnLeft = new ArrayList<>();
		pointsOnRight = new ArrayList<>();
		for (int i = 0; i < height; i++) {
			pointsOnLeft.add(new Point(x, y + i));
			pointsOnRight.add(new Point(x + width, y + i));
		}

		pointsOnTop = new ArrayList<>();
		pointsOnBottom = new ArrayList<>();
		for (int i = 0; i < width; i++) {
			pointsOnTop.add(new Point(x + i, y));
			pointsOnBottom.add(new Point(x + i, y + height));
		}
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
		
		Color oldCol = g.getColor();
		
		for (Point point : this.interSecting) {
			g.setColor(Color.RED);
			g.fillOval((int) point.getX() - 1, (int) point.getY() - 1, 2, 2);
		}
		
		g.setColor(oldCol);
		
	}
	
	public List<Point> interSecting = new LinkedList<>();
	
	public Intersection intersectsX(Circle circle) {
		int circleRadiusSquared = circle.getRadius() * circle.getRadius();
		if (circle.getCenterX() < this.getCenterX()) {
			for (Point point : pointsOnLeft) {
				if (circle.distanceSquared(point) < circleRadiusSquared) {
					interSecting.add(point);
					return Intersection.Left;
				}
			}
		} else {
			for (Point point : pointsOnRight) {
				if (circle.distanceSquared(point) < circleRadiusSquared) {
					interSecting.add(point);
					return Intersection.Right;
				}
			}
		}
		return Intersection.None;
	}
	
	public Intersection intersectsY(Circle circle) {
		int circleRadiusSquared = circle.getRadius() * circle.getRadius();
		if (circle.getCenterY() < this.getCenterY()) {
			for (Point point : pointsOnTop) {
				if (circle.distanceSquared(point) < circleRadiusSquared) {
					interSecting.add(point);

					return Intersection.Top;
				}
			}
		} else {
			for (Point point : pointsOnBottom) {
				if (circle.distanceSquared(point) < circleRadiusSquared) {
					interSecting.add(point);

					return Intersection.Bottom;
				}
			}
		}
		return Intersection.None;
	}
	
}
