package tanks.gobj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import tanks.core.Var;

public class Bullet implements Renderable, Circle {

	private int radius, radiusSquared;
	private double x, y, speed, dx, dy;
	private Color color, tmpColor;
	private int collCount = 0;
	private int maxColls = 3;
	private long ttl = 20_000_000_000l; // time to live in nano seconds (20 s)
	private long spawnTime;
	private Tank origin;
	private boolean dead = false;
	
	public Bullet(Tank origin, int radius, double speed, int maxColls) {
		double angle = origin.getAngle();
		this.dx = 250 * speed * Math.cos(angle);
		this.dy = 250 * speed * Math.sin(angle);
		//       (      center of origin                ) + ( half of width to get outside of player   )
		this.x = (origin.getX() + origin.getWidth() / 2 ) + (origin.getWidth() * 0.5 * Math.cos(angle))  - radius;
		this.y = (origin.getY() + origin.getHeight() / 2) + (origin.getHeight() * 0.5 * Math.sin(angle)) - radius;
		this.radius = radius;
		this.radiusSquared = radius * radius;
		this.origin = origin;
		this.speed = speed;
		this.color = origin.getColor();
		this.maxColls = maxColls;
		this.spawnTime = System.nanoTime();
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public int getRadiusSquared() {
		return radiusSquared;
	}
	
	public void kill() {
		dead = true;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, radius * 2, radius * 2);
	}
	
	public boolean move(double delta, Tank... tanks) {
		if (dead) return false;
		if (spawnTime + ttl < System.nanoTime()) {
			return false; // lived too long
		}
		// move
		this.x += dx * delta;
		this.y += dy * delta;
		// check collision with window bounds
		boolean collidesX = x < 0 || x + radius * 2 > Var.FrameWidth;
		boolean collidesY = y < 0 || y + radius * 2 > Var.FrameHeight;
		if (collidesX || collidesY) {
			if (this.collCount < this.maxColls) {
				if (collidesX) {
					this.dx = -dx;
				}
				if (collidesY) {
					this.dy = -dy;
				}
				this.collCount++;
			} else {
				// collided too often
				return false;
			}
		}
		for (Tank tank : tanks) {
			// can only hit origin if it collided
			if (tank == this.origin && collCount == 0) continue;
			if (!tank.isDead() && tank.getBounds().intersects(getBounds())) {
				// collides
				System.out.println(this.origin.getName() + " shot " + tank.getName());
				if (tank.hit() && this.origin != tank) this.origin.increaseScore();
				return false;
			}
		}
		// check collision with other bullets
		for (Bullet bullet : Var.bullets) {
			if (bullet != this && this.intersects(bullet)) {
				//this.dy = -this.dy;
				//this.dx = -this.dx;
				bullet.kill();
				return false;
			}
		}
		return true;
	}
	
	public void pause() {
		
	}
	
	public void render(Graphics g) {
		tmpColor = g.getColor();
		g.setColor(color);
		g.fillOval((int) x, (int) y, radius * 2, radius * 2);
		g.setColor(tmpColor);
	}
	
}
