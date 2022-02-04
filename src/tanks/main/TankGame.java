//Tank Game in early state
package tanks.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import tanks.core.Keys;
import tanks.core.Misc;
import tanks.core.MoveMouseListener;
import tanks.core.Var;
import tanks.gobj.Tank;

public class TankGame {

	public static void main(String[] args) {
		
		long time = System.currentTimeMillis();
		
		init();
		
		Var.f = new JFrame();
		
		Var.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Var.f.setBounds(0, 0, Var.FrameWidth, Var.FrameHeight);
		Var.f.setResizable(true);
		Var.f.setBackground(Color.BLACK);
		Var.f.setLocationRelativeTo(null);
		Var.f.addKeyListener(new Keys());
		Var.f.setFocusable(true);
		Var.f.requestFocus();
		Var.f.requestFocusInWindow();
		Var.f.setUndecorated(true);
		Var.f.setVisible(true);
		Var.f.setLayout(null);
		Var.f.add(Var.gamepanel);
		Var.f.setContentPane(Var.gamepanel);
		
		MoveMouseListener mml = new MoveMouseListener(Var.gamepanel);
		Var.f.addMouseListener(mml);
		Var.f.addMouseMotionListener(mml);
		
		Misc.AACFastPlay("res/TanksMusic.m4a", 10);
		
		System.out.println("Finished after " + (System.currentTimeMillis()-time) + " ms");
		
	}

	private static void init() {
		System.out.println("Init");
		
		Var.FrameWidth = 1600;
		Var.FrameHeight = 900;
		
		Var.sPerFrame = 1.0 / 120.0;
		Var.p1 = new Tank("Ernie", 250, 50, 50, 50, 0, Color.BLUE);
		Var.p2 = new Tank("Bernd", 500, 50, 50, 50, Math.PI, Color.RED);
		
		try {
			Var.pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/coders_crux.ttf")).deriveFont(125f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/coders_crux.ttf")));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		Var.gamepanel = new GamePanel();
		Var.gamepanel.setVisible(true);
		Var.gamepanel.setBounds(0, 0, Var.FrameWidth, Var.FrameHeight);
		
		Var.gamepanel.getRenderables().add(Var.p2);
		Var.gamepanel.getRenderables().add(Var.p1);
		//Var.gamepanel.getRenderables().add(Var.FPSCount);
	}

}
