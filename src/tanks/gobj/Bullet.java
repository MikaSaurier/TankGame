package tanks.gobj;

import java.awt.Color;
import java.awt.Graphics;

import tanks.core.Var;

public class Bullet {

	int width, height;
	double x, y, speed, dx, dy;
	private Color color, tmpColor;
	int collCount = 0;
	int maxColls = 3;
	Tank origin;
	
	public Bullet(Tank origin, int width, int height, double speed) {
		double angle = origin.getAngle();
		dx = speed * Math.cos(angle);
		dy = speed * Math.sin(angle);
		this.x = origin.getX() + origin.getWidth() / 2 + (origin.getWidth() * 0.5 * Math.cos(angle));
		this.y = origin.getY() + origin.getHeight() / 2 + (origin.getHeight() * 0.5 * Math.sin(angle));
		this.width = width;
		this.height = height;
		this.origin = origin;
		this.speed = speed;
		this.color = origin.getColor();
	}

	public boolean move(Tank... tanks) {
		this.x += dx;
		this.y += dy;
		if ( x < 0 || x > Var.FrameWidth || y < 0 || y > Var.FrameHeight) {
			if (collCount < maxColls) {
				if ( x < 0 || x > Var.FrameWidth) {
					this.dx = -dx;
				} else {
					this.dy = -dy;
				}
				this.collCount++;
			} else {
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
		g.fillOval((int) x, (int) y, width, height);
		g.setColor(tmpColor);
	}
	
}
