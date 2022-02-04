package tanks.gobj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import tanks.core.Var;

public class Tank {

	private String name;
	private int x, y, width, height;
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	private double angle;
	private Color color, tmpColor;
	private int[] xPoints = {0,0};
	private int[] yPoints = {0,0};
	private int xPoly[] = {150, 250, 325, 375, 450, 275, 100};
	private int yPoly[] = {150, 100, 125, 225, 250, 375, 300};
	
	public Tank(String name, int x, int y, int width, int height, Color color) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.angle = 0;
		updatePos();
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public void shoot() {
		System.out.println("Tank " + name + " shooting");
		Var.bullets.add(new Bullet(this, 10, 10, 1, 0)); //TODO: change angle
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
	
    public double getAngle() {
    	return angle;
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
		g.fillRect(x, y, width, height);
		angle += 0.001;
		g.drawLine(
				x+width/2, 
				y+height/2,
				(int) (x + width / 2 + 2 * 40 * (Math.cos(angle))), 
				(int) (y + height / 2 + 2 * 40 * (Math.sin(angle)))
			);
		/*g.fillArc(
				(int) (Var.g1.getCoordX(pnow.x)+10*(Math.cos((angel-165)*Math.PI/180))), 
				(int) (Var.g1.getCoordY(pnow.y)+10*(Math.cos((angel-75)*Math.PI/180))), 
				40, 
				40, 
				angel, 
				30
				);*/
//		if(xPoints.length > 0 && yPoints.length > 0) {
//			g.drawPolyline(xPoints, yPoints, xPoints.length);
//		}
		g.setColor(tmpColor);
	}

}
