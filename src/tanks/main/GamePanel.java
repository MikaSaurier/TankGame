package tanks.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import tanks.core.Misc;
import tanks.core.Var;
import tanks.gobj.Bullet;
import tanks.gobj.Renderable;
import tanks.gobj.Wall;


public class GamePanel extends JPanel {

	public static double s = 1;
	
	private ArrayList<Renderable> renderables = new ArrayList<>();
	private ArrayList<Wall> walls = new ArrayList<>();
	
	public GamePanel() {
		setLayout(null);
		setFocusable(false);
		setBackground(Color.BLACK);
	}
	
	ArrayList<Renderable> getRenderables() {
		return this.renderables;
	}
	public ArrayList<Wall> getWalls() {
		return this.walls;
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
				
        super.paintComponent(g);
                
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.setColor(Color.WHITE);
        
        g2.scale(s, s);
       		
        g.setFont(Var.getModifiedFont(Var.pixelFont, 24));
                   
        for (Renderable toRender : renderables) {
			toRender.render(g);
		}
        
        for (Renderable toRender : walls) {
			toRender.render(g);
		}
        
        Bullet[] bullets = Var.bullets.toArray(new Bullet[0]);
		for (Bullet bullet : bullets) {
			bullet.render(g);
		}
        
		g.drawString("FPS:      " + calculateFps(delta), 20, 25);
        g.drawString("Bullets:  " + Var.bullets.size(), 20, 50);
        g.drawString("Kills P1: " + Var.p1.getScore(), 20, 75);
        g.drawString("Kills P2: " + Var.p2.getScore(), 20, 100);
		
        repaint();

		double renderTime = ((double) (System.nanoTime() - startTime)) / 1_000_000_000.0;
		if (renderTime < Var.sPerFrame) {
        	double sleepingTimeMs = (1000.0 * (Var.sPerFrame - renderTime));
        	if((int) sleepingTimeMs > 0) Misc.sleeping((int) sleepingTimeMs);
		}
	}
	
	
	private final int MOVE_MULT = 222;
	private final double ROTATE_MULT = 0.8 * Math.PI;
	private void move(double delta) {
		if (Var.P1MoveUP) {
			Var.p1.move(+MOVE_MULT * delta);
		} if (Var.P1MoveDOWN) {
			Var.p1.move(-MOVE_MULT * delta);
		} 
		int rotMult1 = Var.P1MoveDOWN ? -1 : 1;
		if (Var.P1MoveLEFT) {
			Var.p1.rotate(rotMult1 * -ROTATE_MULT * delta);
		} if (Var.P1MoveRIGHT) {
			Var.p1.rotate(rotMult1 * +ROTATE_MULT * delta);
		}
		
		if (Var.P2MoveUP) {
			Var.p2.move(+MOVE_MULT * delta);
		} if (Var.P2MoveDOWN) {
			Var.p2.move(-MOVE_MULT * delta);
		}
		int rotMult2 = Var.P2MoveDOWN ? -1 : 1;
		if (Var.P2MoveLEFT) {
			Var.p2.rotate(rotMult2 * -ROTATE_MULT * delta);
		} if (Var.P2MoveRIGHT) {
			Var.p2.rotate(rotMult2 * +ROTATE_MULT * delta);
		}
		
		if (Var.P1Shoot) {
			Var.p1.shoot();
		} if (Var.P2Shoot) {
			Var.p2.shoot();
		}
		
		Bullet[] bullets = Var.bullets.toArray(new Bullet[0]);
		for (Bullet bullet : bullets) {
			if (! bullet.move(delta, Var.p1, Var.p2)) {
				Var.bullets.remove(bullet);
			}
		}
	}
}
