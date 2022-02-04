package tanks.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import tanks.core.Misc;
import tanks.core.Var;
import tanks.gobj.Bullet;
import tanks.gobj.Renderable;


public class GamePanel extends JPanel {

	public static double s = 1;
	private static long m = 0;
	// private static double delta = 0;
	
	private ArrayList<Renderable> renderables = new ArrayList<>();
	private ArrayList<Rectangle> bounds = new ArrayList<>();
	
	public GamePanel() {
		setLayout(null);
		setFocusable(false);
		setBackground(Color.BLACK);
	}
	
	ArrayList<Renderable> getRenderables() {
		return this.renderables;
	}

	private long lastDrawn = System.nanoTime();
	
	@Override
    protected void paintComponent(Graphics g) {
		long startTime = System.nanoTime();
		move((startTime - lastDrawn) / 1_000_000_000.0);
		this.lastDrawn = startTime;
		
		ArrayList<Rectangle> newBounds = new ArrayList<>();
		
        super.paintComponent(g);
        
        m = System.nanoTime();
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.setColor(Color.WHITE);
        
        g2.scale(s, s);
       
        g.setFont(Var.getModifiedFont(Var.pixelFont, 24));
        g.drawString("Bullets: " + Var.bullets.size(), 20, 75);
        
        Var.FPSCount.tick();
        
        for (Renderable toRender : renderables) {
			toRender.render(g);
			newBounds.add(toRender.getBounds());
		}
        
        Bullet[] bullets = Var.bullets.toArray(new Bullet[0]);
		for (Bullet bullet : bullets) {
			bullet.render(g);
			newBounds.add(bullet.getBounds());
		}
        
        if (Var.didScreenChange) {
			repaint();
			Var.didScreenChange = false;
		} else {
        	for (Rectangle bound : newBounds) {
				repaint(bound);
			}
    		for (Rectangle bound : this.bounds) {
				repaint(bound);
			}
		}
		
		this.bounds = newBounds;
        
        
        /*
        delta = System.nanoTime() - m;
        delta = delta/1_000_000.0;
        if((int) (Misc.FPStoMS(Var.fps) - delta) > 0) {
        	long start = System.nanoTime();
        	double sleepingTime = (Misc.FPStoMS(Var.fps) - delta) + Var.SleptDiff;
        	if((int)sleepingTime > 0) {
        		Misc.sleeping((int)sleepingTime);
            	double slept = ((System.nanoTime() - start)/ 1_000_000.0);
            	Var.SleptDiff = sleepingTime - slept;
        	}
        }
        */
	}
	
	private void move(double delta) {
		if(Var.P1MoveUP) {
			Var.p1.incY(-100 * delta);
		} if(Var.P1MoveDOWN) {
			Var.p1.incY(+100 * delta);
		} if(Var.P1MoveLEFT) {
			Var.p1.incX(-100 * delta);
		} if(Var.P1MoveRIGHT) {
			Var.p1.incX(+100 * delta);
		}
		
		if(Var.P2MoveUP) {
			Var.p2.incY(-100 * delta);
		} if(Var.P2MoveDOWN) {
			Var.p2.incY(+100 * delta);
		} if(Var.P2MoveLEFT) {
			Var.p2.incX(-100 * delta);
		} if(Var.P2MoveRIGHT) {
			Var.p2.incX(+100 * delta);
		}
		
		Bullet[] bullets = Var.bullets.toArray(new Bullet[0]);
		for (Bullet bullet : bullets) {
			if (! bullet.move(delta, Var.p1, Var.p2)) {
				Var.bullets.remove(bullet);
			}
		}
	}
	
}
