package tanks.gobj;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {

	int x, y, width, height;
	double speed, angle;
	private Color color, tmpColor;
	Tank origin;
	
	public Bullet(Tank origin, int width, int height, double speed, double angle) {
		this.x = origin.getX() + origin.getWidth() / 2;
		this.y = origin.getY() + origin.getHeight() / 2;
		this.width = width;
		this.height = height;
		this.origin = origin;
		this.speed = speed;
		this.color = origin.getColor();
	}

	public boolean move(Tank... tanks) {
		return false;
	}
	
	public void pause() {
		
	}
	
	public void render(Graphics g) {
		tmpColor = g.getColor();
		g.setColor(color);
		g.fillOval(x, y, width, height);
		g.setColor(tmpColor);
	}
	
}
