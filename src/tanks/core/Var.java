package tanks.core;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tanks.gobj.Bullet;
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
	
	public static boolean P1MoveUP = false, P1MoveDOWN = false, P1MoveLEFT = false, P1MoveRIGHT = false, P1Shoot = false;
	public static boolean P2MoveUP = false, P2MoveDOWN = false, P2MoveLEFT = false, P2MoveRIGHT = false, P2Shoot = false;
	public static double sPerFrame;
	public static double SleptDiff = 0;
	
	
	public static Font getModifiedFont(Font font, int size) {
		return new Font(font.getName(), Font.PLAIN, size);
	}
	
	public static Font getModifiedFont(Font font, int style, int size) {
		return new Font(font.getName(), style, size);
	}
	
}
