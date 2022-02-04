package tanks.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import tanks.core.Misc;
import tanks.core.Var;
import tanks.gobj.Bullet;

public class gamePanel extends JPanel{

	public static double s = 1;
	private static long m = 0;
	private static double delta = 0;
	
	public gamePanel() {
		setLayout(null);
		setFocusable(false);
		setBackground(Color.BLACK);
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        m = System.nanoTime();
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.setColor(Color.WHITE);
        
        g2.scale(s, s);
       
        g.setFont(Var.getModifiedFont(Var.pixelFont, 24));
        g.drawString("Bullets: " + Var.bullets.size(), 20, 75);
        
        Var.p1.render(g);
        Var.p2.render(g);
        
        Bullet[] bullets = Var.bullets.toArray(new Bullet[0]);
		for (Bullet bullet : bullets) {
			bullet.render(g);
		}
        
        Var.FPSCount.render(g);
        
        Var.FPSCount.tick();
        
        
        
        if(bullets.length > 0) {
        	for (int i = 0; i < bullets.length; i++) {
    			repaint(new Rectangle(bullets[i].getBounds().x-bullets[i].getBounds().width/2,
    					bullets[i].getBounds().y-bullets[i].getBounds().height/2,
    					bullets[i].getBounds().width*2,
    					bullets[i].getBounds().height*2
    					));
    			repaint(Var.p1.getBounds());
    			repaint(Var.p2.getBounds());
    			repaint(Var.FPSCount.getBounds());
    		}
        }else {
        	repaint();
        }
        
        
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
	
}
