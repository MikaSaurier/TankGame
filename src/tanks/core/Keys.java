package tanks.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_W) {
			Var.P1MoveUP = true;
		}if(k == KeyEvent.VK_A) {
			Var.P1MoveLEFT = true;
		}if(k == KeyEvent.VK_S) {
			Var.P1MoveDOWN = true;
		}if(k == KeyEvent.VK_D) {
			Var.P1MoveRIGHT = true;
		}if(k == KeyEvent.VK_I) {
			Var.P2MoveUP = true;
		}if(k == KeyEvent.VK_J) {
			Var.P2MoveLEFT = true;
		}if(k == KeyEvent.VK_K) {
			Var.P2MoveDOWN = true;
		}if(k == KeyEvent.VK_L) {
			Var.P2MoveRIGHT = true;
		}if(k == KeyEvent.VK_C) {
			Var.p1.shoot();
		}if(k == KeyEvent.VK_N) {
			Var.p2.shoot();
		}if(k == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}if(k == KeyEvent.VK_F) {
			Misc.toggleFullScreen();
		}if(k == KeyEvent.VK_PLUS || k == 93) {
			if(!Var.fullScreen && Var.f.getWidth()>=256 && Var.f.getHeight() >=144) {
				Var.f.setSize((int)(Var.f.getWidth()+64), (int)(Var.f.getHeight()+36));
				Misc.changeScale(Var.f.getWidth(), Var.f.getHeight());
	            Var.f.setLocationRelativeTo(null);
			}
		}if(k == KeyEvent.VK_MINUS || k == 47) {
			if(!Var.fullScreen && Var.f.getWidth()>=256 && Var.f.getHeight() >=144) {
				Var.f.setSize((int)(Var.f.getWidth()-64), (int)(Var.f.getHeight()-36));
				Misc.changeScale(Var.f.getWidth(), Var.f.getHeight());
	            Var.f.setLocationRelativeTo(null);
			}
		}if(k == KeyEvent.VK_Q) {
			Var.p1.setAngle(Var.p1.getAngle() - 0.05);
		}if(k == KeyEvent.VK_E) {
			Var.p1.setAngle(Var.p1.getAngle() + 0.05);
		}if(k == KeyEvent.VK_U) {
			Var.p2.setAngle(Var.p2.getAngle() - 0.05);
		}if(k == KeyEvent.VK_O) {
			Var.p2.setAngle(Var.p2.getAngle() + 0.05);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_W) {
			Var.P1MoveUP = false;
		}if(k == KeyEvent.VK_A) {
			Var.P1MoveLEFT = false;
		}if(k == KeyEvent.VK_S) {
			Var.P1MoveDOWN = false;
		}if(k == KeyEvent.VK_D) {
			Var.P1MoveRIGHT = false;
		}if(k == KeyEvent.VK_I) {
			Var.P2MoveUP = false;
		}if(k == KeyEvent.VK_J) {
			Var.P2MoveLEFT = false;
		}if(k == KeyEvent.VK_K) {
			Var.P2MoveDOWN = false;
		}if(k == KeyEvent.VK_L) {
			Var.P2MoveRIGHT = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		int k = e.getKeyCode();
	}

}
