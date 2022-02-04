package tanks.gobj;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Renderable {

	Rectangle getBounds();
	
	void render(Graphics g);

}
