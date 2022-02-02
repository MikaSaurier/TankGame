package tanks.gobj;

import java.awt.Color;
import java.awt.Graphics;

public class Tank {

	String name;
	int x, y, width, height;
	Color color, tmpColor;
	int[] xPoints = {0,0};
	int[] yPoints = {0,0};
	int xPoly[] = {150, 250, 325, 375, 450, 275, 100};
    int yPoly[] = {150, 100, 125, 225, 250, 375, 300};
	
	public Tank(String name, int x, int y, int width, int height, Color color) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		updatePos();
	}
	
	public void shoot() {
		System.out.println("Tank " + name + " shooting");
	}
	
	private void updatePos() {
		xPoints[0] = x;
		xPoints[1] = x+width;
		yPoints[0] = y;
		yPoints[1] = y+height;
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
		updatePos();
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		updatePos();
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
		//g.fillRect(x, y, width, height);
		if(xPoints.length > 0 && yPoints.length > 0) {
			g.drawPolyline(xPoints, yPoints, xPoints.length);
		}
		
		g.setColor(tmpColor);
	}

}
