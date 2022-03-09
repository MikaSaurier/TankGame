package tanks.gobj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import tanks.core.Var;

public class Tank implements Renderable, Circle {

	private String name;
	private final int radius, diameter;
	private double x, y, angle, lastX, lastY;
	private Color color, tmpColor;
	private long lastShot = 0;
	private static final int DEFAULT_LIFES = 10;
	private int lives = DEFAULT_LIFES;
	private long score = 0;

	private final double startX, startY, startAngle;

	public Tank(String name, int x, int y, int radius, double angle, Color color) {
		this.name = name;
		this.x = x;
		this.startX = x;
		this.lastX = x;
		this.y = y;
		this.startY = y;
		this.lastY = y;
		this.radius = radius;
		this.diameter = radius * 2;
		this.color = color;
		this.angle = angle;
		this.startAngle = angle;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, diameter, diameter);
	}

	public void shoot() {
		if (isDead())
			return;
		// every 100ms
		if (lastShot + 200_000_000 < System.nanoTime()) {
			Var.bullets.add(new Bullet(this, 5, 2));
			lastShot = System.nanoTime();
		}
	}

	public void revive() {
		lives = DEFAULT_LIFES;
		this.setX(startX);
		this.setY(startY);
		this.angle = startAngle;
	}

	public void increaseScore() {
		score++;
	}

	public long getScore() {
		return score;
	}

	public boolean hit() {
		// returns true if the hit killed.
		if (isDead())
			return false;
		return --lives <= 0;
	}

	public boolean isDead() {
		return lives <= 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAngle() {
		return angle;
	}

	public void rotate(double angle) {
		this.angle += angle;
	}

	public void move(double val) {
		moveAngle(val, angle, true);
	}

	private void moveAngle(double val, double angle, boolean collisionCheck) {
		move(Math.cos(angle) * val, Math.sin(angle) * val, collisionCheck);
	}

	private void move(double dx, double dy, boolean collisionCheck) {
		setX(this.x + dx);
		setY(this.y + dy);

		if (!collisionCheck)
			return;

		// wall collisions
		for (Wall wall : Var.gamepanel.getWalls()) {
			Intersection inter = wall.intersects(this);
			this.x += inter.getToMoveX();
			this.y += inter.getToMoveY();
		}

		Tank other = Var.p1 == this ? Var.p2 : Var.p1;

		if (!(isDead() || other.isDead()) && this.intersects(other)) {
			// https://flatredball.com/documentation/tutorials/math/circle-collision/
			double angle = Math.atan2(other.getCenterY() - this.getCenterY(), other.getCenterX() - this.getCenterX());
			double distToMove = this.distanceTo(other) - (this.getRadius() + other.getRadius());
			this.moveAngle(+0.7 * distToMove, angle, false);
			other.moveAngle(-0.3 * distToMove, angle, false);
		}
	}
	
	private void setX(double x) {
		this.lastX = this.x;
		this.x = Math.max(0, Math.min(x, Var.FrameWidth - this.diameter));
	}

	private void setY(double y) {
		this.lastY = this.y;
		this.y = Math.max(0, Math.min(y, Var.FrameHeight - this.diameter));
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void render(Graphics g) {
		int x = (int) this.x;
		int y = (int) this.y;
		tmpColor = g.getColor();

		if (isDead()) {
			g.setColor(color.darker());
		} else {
			g.setColor(color);
		}

		String overPlayer = name + " (" + (lives > 0 ? lives + "" : "dead") + ")";
		g.drawString(overPlayer, (int) getCenterX() - g.getFontMetrics().stringWidth(overPlayer) / 2,
				(int) y - diameter / 10);

		Graphics2D g2d = (Graphics2D) g;
		AffineTransform old = g2d.getTransform();
		g2d.rotate(angle, (int) getCenterX(), (int) getCenterY());
		g.fillRect(x, y, diameter, diameter);

		g.fillRect((int) getCenterX() + radius / 2, (int) getCenterY() - 1, (int) (radius * 1.5), 2);

		if (Var.OutlineColor != null) {
			g.setColor(Var.OutlineColor);
			g.drawRect(x, y, diameter, diameter);
			g.drawRect((int) getCenterX() + radius / 2, (int) getCenterY() - 1, (int) (radius * 1.5), 2);
		}

		g2d.setTransform(old);

		g.setColor(tmpColor);
	}

	@Override
	public int getRadius() {
		return radius;
	}

	@Override
	public double getLastX() {
		return lastX;
	}

	@Override
	public double getLastY() {
		return lastY;
	}

}
