package tanks.core;

import java.awt.Toolkit;

import tanks.main.GamePanel;

public class Misc {

	private static boolean toggleFullScreenWait;
	
	public static void AACFastPlay(String in, float vol) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				AACPlayer player = new AACPlayer(in, vol);
				try {
					player.play();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}
	
	public static void AACFastPlay(String in) {
		AACFastPlay(in, 50);
	}
	
	public static void sleeping(int ms) {
		try{Thread.sleep(ms);}catch(InterruptedException e){e.printStackTrace();}
	}
	
	public static double FPStoMS(int FPS) {
		return 1000.0/(double)FPS;
	}
	
	public static void changeScale(int Width, int Height) {
		if ((double) Width / Var.FrameWidth > (double) Var.FrameHeight / 720) {
			GamePanel.s = (double) Height / Var.FrameHeight;
		}else {
			GamePanel.s = (double) Width / Var.FrameWidth;
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
				Var.didScreenChange = true;
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
				Var.didScreenChange = true;
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
