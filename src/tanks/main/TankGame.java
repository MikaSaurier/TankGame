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
import tanks.gobj.Ground;
import tanks.gobj.Tank;
import tanks.gobj.Wall;

public class TankGame {

	public static void main(String[] args) {	
		long time = System.nanoTime();
		
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
		
		Misc.AACFastPlay("res/TanksMusic.m4a", 20);
		
		System.out.println("Finished after " + ((System.nanoTime() - time) / 1_000_000_000.0) + " s");	
	}

	private static void init() {
		System.out.println("Init");
		
		Var.g1 = new Ground(0, 0, 64, Var.FrameWidth / 64, Var.FrameHeight / 64, Color.WHITE);
		//Var.g1.setTexture("res/planks.png", "res/planks90.png");
		Var.p1 = new Tank("Ernie", Var.FrameWidth/2 - 550, Var.FrameHeight / 2 - 12, 25, 0, new Color(10, 250, 250));
		Var.p2 = new Tank("Bernd", Var.FrameWidth/2 + 550, Var.FrameHeight / 2 - 12, 25, Math.PI, new Color(254, 1, 154));
		
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
		
		Var.gamepanel.getRenderables().add(Var.g1);
		Var.gamepanel.getRenderables().add(Var.p2);
		Var.gamepanel.getRenderables().add(Var.p1);
		Var.gamepanel.getWalls().add(new Wall(Var.FrameWidth / 2 - 50, 0, 100, 360, new Color(255, 131, 0)));
		Var.gamepanel.getWalls().add(new Wall(Var.FrameWidth / 2 - 50, Var.FrameHeight - 360, 100, 360, new Color(255, 131, 0)));
		Var.gamepanel.getWalls().add(new Wall(Var.FrameWidth / 2 - 420, Var.FrameHeight/2 - 210, 42, 420, new Color(10, 250, 250).darker()));
		Var.gamepanel.getWalls().add(new Wall(Var.FrameWidth / 2 + 420, Var.FrameHeight/2 - 210, 42, 420,  new Color(254, 1, 154).darker()));
		//Var.gamepanel.getRenderables().add(Var.FPSCount);
	}
}
