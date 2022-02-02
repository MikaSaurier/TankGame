package tanks.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import tanks.core.Var;

public class gamePanel extends JPanel{

	public static double s = 1;
	
	public gamePanel() {
		setLayout(null);
		setFocusable(false);
		setBackground(Color.BLACK);
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.scale(s, s);
        g.setColor(Color.BLACK);
        g.setFont(Var.getModifiedFont(Var.pixelFont, 18));
        g.drawString("Test", 100, 100);
        
        Var.p1.render(g);
        Var.p2.render(g);
        Var.FPSCount.render(g);
        
        Var.FPSCount.tick();
        repaint();
	}	
}
