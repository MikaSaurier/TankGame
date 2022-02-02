package tanks.gobj;

import java.awt.Color;
import java.awt.Graphics;

public class Tank {

	String name;
	int x, y, width, height;
	Color color, tmpColor;
	
	public Tank(String name, int x, int y, int width, int height, Color color) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void shoot() {
		System.out.println("Tank " + name + " shooting");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void render(Graphics g) {
		tmpColor = g.getColor();
		g.setColor(color);
		g.drawString(name, x, y-width/10);
		g.fillRect(x, y, width, height);
		g.setColor(tmpColor);
	}

}
