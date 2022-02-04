package tanks.core;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tanks.gobj.Bullet;
import tanks.gobj.FPSCounter;
import tanks.gobj.Tank;
import tanks.main.GamePanel;

public class Var {

	public static JFrame f;
	public static GamePanel gamepanel;
	
	public static Font pixelFont;
	
	public static int FrameWidth, FrameHeight, FrameWidth_Cache, FrameHeight_Cache;
	public static boolean fullScreen, showBounds, didScreenChange;
	
	public static Tank p1, p2;
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	public static boolean P1MoveUP, P1MoveDOWN, P1MoveLEFT, P1MoveRIGHT;
	public static boolean P2MoveUP, P2MoveDOWN, P2MoveLEFT, P2MoveRIGHT;
	public static FPSCounter FPSCount;
	public static int fps;
	public static double SleptDiff = 0;
	
	
	public static Font getModifiedFont(Font font, int size) {
		return new Font(font.getName(), Font.PLAIN, size);
	}
	
	public static Font getModifiedFont(Font font, int style, int size) {
		return new Font(font.getName(), style, size);
	}
	
}
