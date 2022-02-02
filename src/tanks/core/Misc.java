package tanks.core;

import java.awt.Toolkit;

import tanks.main.gamePanel;

public class Misc {

	private static boolean toggleFullScreenWait;
	
	public static void sleeping(int ms) {
		try{Thread.sleep(ms);}catch(InterruptedException e){e.printStackTrace();}
	}
	
	public static double FPStoMS(int FPS){return 1000.0/(double)FPS;}
	
	public static void changeScale(int Width, int Height) {
		if((double)Width/Var.FrameWidth>(double)Var.FrameHeight/720) {
			gamePanel.s = (double)Height/Var.FrameHeight;
		}else {
			gamePanel.s = (double)Width/Var.FrameWidth;
		}
	}
	
	public static void toggleFullScreen() {
		if(!toggleFullScreenWait) {
			toggleFullScreenWait = true;
			if(Var.fullScreen) {
				Var.fullScreen = false;
				Var.f.dispose();
				Var.f.setUndecorated(true);
				Var.f.setVisible(true);
				Var.f.setSize(Var.FrameWidth_Cache, Var.FrameHeight_Cache);
				Var.f.setLocationRelativeTo(null);
				changeScale(Var.f.getWidth(), Var.f.getHeight());
			}else {
				Var.fullScreen = true;
				Var.FrameWidth_Cache = Var.f.getWidth();
				Var.FrameHeight_Cache = Var.f.getHeight();
				Var.f.dispose();
				Var.f.setUndecorated(true);
				Var.f.setVisible(true);
				Var.f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				Var.f.setLocation(0, 0);
				changeScale(Var.f.getWidth(), Var.f.getHeight());
			}
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					sleeping(500);
					toggleFullScreenWait = false;
				}
			});
			t.start();
		}
		
	}
	
}
