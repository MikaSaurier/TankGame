package tanks.gobj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ground implements Renderable {

	private Color color, tmpColor;
	private int x, y, width, height, tileSize;
	
	public Ground(int x, int y, int tileSize, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.tileSize = tileSize;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public Rectangle getBounds() {
		return null;
	}
	
	public void render(Graphics g) {
		tmpColor = g.getColor();
		g.setColor(color);
		
		//GRID
        for (int i = (int) (x); i <= (int) x+(tileSize * width); i+=(int)tileSize) {
    		g.drawLine(i, (int)(y), i, (int)(y+(tileSize * height)));
		}
		for (int i = (int) (y); i <= (int) y+(tileSize * height); i+=(int)tileSize) {
    		g.drawLine((int)(x), i, (int)(x+(tileSize * width)), i);
		}
		
		g.setColor(tmpColor);
	}

	
	
}
