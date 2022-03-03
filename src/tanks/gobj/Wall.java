package tanks.gobj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tanks.core.Var;

public class Wall implements Renderable {
	final int x, y, width, height;
	final List<Point> pointsOnLeft, pointsOnRight, pointsOnTop, pointsOnBottom;
	final Point[] cornerPoints;

	private final Color color;

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

	public Wall(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		pointsOnLeft = new ArrayList<>();
		pointsOnRight = new ArrayList<>();
		for (int i = 0; i <= height / 2; i += 2) {
			pointsOnLeft.add(new Point(x, y + i));
			pointsOnLeft.add(new Point(x, y + height - i));
			pointsOnRight.add(new Point(x + width, y + i));
			pointsOnRight.add(new Point(x + width, y + height - i));
		}

		pointsOnTop = new ArrayList<>();
		pointsOnBottom = new ArrayList<>();
		for (int i = 0; i <= width / 2; i += 2) {
			pointsOnTop.add(new Point(x + i, y));
			pointsOnTop.add(new Point(x + width - i, y));
			pointsOnBottom.add(new Point(x + i, y + height));
			pointsOnBottom.add(new Point(x + width - i, y + height));
		}
		this.cornerPoints = new Point[] { new Point(x, y), new Point(x + width, y), new Point(x + width, y + height),
				new Point(x, y + height) };
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void render(Graphics g) {
		Color oldCol = g.getColor();

		g.setColor(this.color);
		g.fillRect(x, y, width, height);

		if (Var.OutlineColor != null) {
			g.setColor(Var.OutlineColor);
			g.drawRect(x, y, width, height);
		}

		g.setColor(oldCol);

	}

	public Intersection intersects(Circle circle) {
		int circleRadiusSquared = circle.getRadius() * circle.getRadius();
		Intersection.Type interTypeX = null;
		double distX = 0d;
		if (circle.getY() + circle.getRadius() * 2 > this.getY() && circle.getY() < this.getY() + this.getHeight()) {
			if (circle.getX() < this.getX()) {
				if (circle.getLastX() <= circle.getX()) {
					for (Point point : pointsOnLeft) {
						if (circle.distanceSquared(point) < circleRadiusSquared) {
							distX = point.getX() - (circle.getX() + circle.getRadius() * 2);
							interTypeX = Intersection.Type.Left;
							break;
						}
					}
				}
			} else if (circle.getX() + circle.getRadius() * 2 > this.getX() + this.getWidth()) {
				if (circle.getLastX() >= circle.getX()) {
					for (Point point : pointsOnRight) {
						if (circle.distanceSquared(point) < circleRadiusSquared) {
							distX = point.getX() - circle.getX();
							interTypeX = Intersection.Type.Right;
							break;
						}
					}
				}
			}
		}
		Intersection.Type interTypeY = null;
		double distY = 0d;
		if (circle.getX() + circle.getRadius() * 2 > this.getX() && circle.getX() < this.getX() + this.getWidth()) {
			if (circle.getY() < this.getY()) {
				if (circle.getLastY() <= circle.getY()) {
					for (Point point : pointsOnTop) {
						if (circle.distanceSquared(point) < circleRadiusSquared) {
							distY = point.getY() - (circle.getY() + circle.getRadius() * 2);
							interTypeY = Intersection.Type.Top;
							break;
						}
					}
				}
			} else if (circle.getY() + circle.getRadius() * 2 > this.getY() + this.getHeight()) {
				if (circle.getLastY() >= circle.getY()) {
					for (Point point : pointsOnBottom) {
						if (circle.distanceSquared(point) < circleRadiusSquared) {
							distY = point.getY() - circle.getY();
							interTypeY = Intersection.Type.Bottom;
							break;
						}
					}
				}
			}
		}

		if (interTypeY == null && interTypeX == null) {
			return Intersection.NONE;
		}

		return new Intersection(distX, distY, interTypeY, interTypeX);
	}

}
