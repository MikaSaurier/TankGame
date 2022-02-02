package tanks.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import tanks.core.Keys;
import tanks.core.MoveMouseListener;
import tanks.core.Movement;
import tanks.core.Var;
import tanks.gobj.FPSCounter;
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
		
		Var.gamepanel = new gamePanel();
		Var.gamepanel.setVisible(true);
		Var.gamepanel.setBounds(0, 0, Var.FrameWidth, Var.FrameHeight);
		Var.f.add(Var.gamepanel);
		Var.f.setContentPane(Var.gamepanel);
		
		MoveMouseListener mml = new MoveMouseListener(Var.gamepanel);
		Var.f.addMouseListener(mml);
		Var.f.addMouseMotionListener(mml);
		
		System.out.println("Finished after " + (System.currentTimeMillis()-time) + " ms");
		
	}

	private static void init() {
		System.out.println("Init");
		Var.FrameWidth = 1280;
		Var.FrameHeight = 720;
		Var.p1 = new Tank("Ernie", 50, 10, 50, 50, Color.WHITE);
		Var.p2 = new Tank("Bernd", 500, 50, 50, 50, Color.WHITE);
		Var.FPSCount = new FPSCounter(20, 25);
		Movement.init();
		try {
			Var.pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/coders_crux.ttf")).deriveFont(125f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/coders_crux.ttf")));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

}
