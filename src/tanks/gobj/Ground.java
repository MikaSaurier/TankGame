package tanks.gobj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ground implements Renderable {

	private Color color, tmpColor;
	private int x, y, width, height, tileSize;
	private boolean textured;
	private BufferedImage texture, texture2;
	
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
	
	public void setTexture(String Path, String Path2) {
		textured = true;
		try {
			texture = ImageIO.read(new File(Path));
			texture2 = ImageIO.read(new File(Path2));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		tmpColor = g.getColor();
		g.setColor(color);
		
		if(!textured) {
			//GRID
	        /*for (int i = (int) (x); i <= (int) x+(tileSize * width); i+=(int)tileSize) {
	    		g.drawLine(i, (int)(y), i, (int)(y+(tileSize * height)));
			}
			for (int i = (int) (y); i <= (int) y+(tileSize * height); i+=(int)tileSize) {
	    		g.drawLine((int)(x), i, (int)(x+(tileSize * width)), i);
			}*/
		}else {
			//TEXTURE
	        for (int i = (int) (x); i <= (int) x+(tileSize * width); i+=(int)tileSize) {
	        	for (int j = (int) (y); j <= (int) y+(tileSize * height); j+=(int)tileSize) {
	        		if( (((j + i) /(int)tileSize) % 2) == 0) {
	        			g.drawImage(texture, i, j, tileSize, tileSize, null);
	        		}else {
	        			g.drawImage(texture2, i, j, tileSize, tileSize, null);
	        		}
				}
			}
		}
		
		
		g.setColor(tmpColor);
	}

	
	
}
