package tanks.gobj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import tanks.core.Var;

public class Tank implements Renderable {

	private String name;
	private int width, height;
	private double x, y, angle;
	private Color color, tmpColor;
	private long lastShot = 0;
	private static final int DEFAULT_LIFES = 10;
	private int lives = DEFAULT_LIFES;
	private long score = 0;
	
	public Tank(String name, int x, int y, int width, int height, double angle, Color color) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.angle = angle;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int)y, width, height);
	}
	
	public void shoot() {
		if (lives <= 0) return;
		// every 100ms
		if (lastShot + 100_000_000 < System.nanoTime()) {
			Var.bullets.add(new Bullet(this, 5, 2, 3));
			lastShot = System.nanoTime();
		}
	}
	
	public void revive() {
		if (lives <= 0) {
			lives = DEFAULT_LIFES;
		}
	}
	
	public void increaseScore() {
		score++;
	}
	
	public long getScore() {
		return score;
	}
	
	public boolean hit() {
		// returns true if the hit killed.
		if (lives <= 0) return false;
		return --lives <= 0;
	}
	
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
    	this.x = Math.max(0, Math.min(this.x + Math.cos(angle) * val, Var.FrameWidth - this.width));
    	this.y = Math.max(0, Math.min(this.y + Math.sin(angle) * val, Var.FrameHeight - this.height));
    }
    
	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void render(Graphics g) {
		tmpColor = g.getColor();
		
		if (lives <= 0) {
			g.setColor(color.darker());
		} else {
			g.setColor(color);
		}
		
		String overPlayer = name + " (" + (lives > 0 ? lives + "" : "dead") + ")";
		
		g.drawString(
			overPlayer,
			(int) x + width/2 - g.getFontMetrics().stringWidth(overPlayer) / 2,
			(int) y - width/10
		);
		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform old = g2d.getTransform();
		g2d.rotate(angle, (int) x + width/2, (int) y + height/2);
		g.fillRect((int) x, (int) y, width, height);
		g2d.setTransform(old);
		
		
		g.drawLine(
			(int) x + width/2, 
			(int) y + height/2,
			(int) (x + width / 2 + Math.max(width, height) * (Math.cos(angle))), 
			(int) (y + height / 2 + Math.max(width, height) * (Math.sin(angle)))
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
