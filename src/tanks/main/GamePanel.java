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
	
	private ArrayList<Double> lastDeltas = new ArrayList<>();
	
	private int getFps() {
		double deltaSum = 0;
		for (double d : lastDeltas) {
			deltaSum += d;
		}
		
		return ((int) (lastDeltas.size() / deltaSum));
	}
	
	private int calculateFps(double delta) {
		lastDeltas.add(delta);
		
		if (lastDeltas.size() > 100) {
			lastDeltas.remove(0);
		}
		
		return getFps();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
		long startTime = System.nanoTime();
		double delta = (startTime - lastDrawn) / 1_000_000_000.0;
		move(delta);
		this.lastDrawn = startTime;
		
		ArrayList<Rectangle> newBounds = new ArrayList<>();
		
        super.paintComponent(g);
                
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.setColor(Color.WHITE);
        
        g2.scale(s, s);
       		
        g.setFont(Var.getModifiedFont(Var.pixelFont, 24));
		g.drawString("FPS:      " + calculateFps(delta), 20, 25);
        g.drawString("Bullets:  " + Var.bullets.size(), 20, 50);
        g.drawString("Score P1: " + Var.p1.getScore(), 20, 75);
        g.drawString("Score P2: " + Var.p2.getScore(), 20, 100);
        
        newBounds.add(new Rectangle(10, 10, 100, 200));
        
        //Var.FPSCount.tick();
        
        for (Renderable toRender : renderables) {
			toRender.render(g);
			newBounds.add(toRender.getBounds());
		}
        
        Bullet[] bullets = Var.bullets.toArray(new Bullet[0]);
		for (Bullet bullet : bullets) {
			bullet.render(g);
			newBounds.add(bullet.getBounds());
		}
        
        if (true || Var.didScreenChange) {
        	// tu immer dies hier, weil rotierte Panzer
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
        

		double renderTime = ((double) (System.nanoTime() - startTime)) / 1_000_000_000.0;
		if (renderTime < Var.sPerFrame) {
        	double sleepingTimeMs = (1000.0 * (Var.sPerFrame - renderTime));
        	if((int) sleepingTimeMs > 0) Misc.sleeping((int) sleepingTimeMs);
		}
	}
	
	private void move(double delta) {
		if(Var.P1MoveUP) {
			Var.p1.move(+100 * delta);
		} if(Var.P1MoveDOWN) {
			Var.p1.move(-100 * delta);
		} if(Var.P1MoveLEFT) {
			Var.p1.rotate(-0.5 * delta);
		} if(Var.P1MoveRIGHT) {
			Var.p1.rotate(+0.5 * delta);
		}
		
		if(Var.P2MoveUP) {
			Var.p2.move(-100 * delta);
		} if(Var.P2MoveDOWN) {
			Var.p2.move(+100 * delta);
		} if(Var.P2MoveLEFT) {
			Var.p2.rotate(-0.5 * delta);
		} if(Var.P2MoveRIGHT) {
			Var.p2.rotate(+0.5 * delta);
		}
		
		Bullet[] bullets = Var.bullets.toArray(new Bullet[0]);
		for (Bullet bullet : bullets) {
			if (! bullet.move(delta, Var.p1, Var.p2)) {
				Var.bullets.remove(bullet);
			}
		}
	}
	
}
