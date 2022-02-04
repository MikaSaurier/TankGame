package tanks.core;

import java.util.Timer;
import java.util.TimerTask;

import tanks.gobj.Bullet;

public class Movement {

	private static Timer move;
	
	public static void init() {
		move = new Timer();
		move.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run() {
				if(Var.P1MoveUP) {
					Var.p1.setY(Var.p1.getY()-1);
				}if(Var.P1MoveDOWN) {
					Var.p1.setY(Var.p1.getY()+1);
				}if(Var.P1MoveLEFT) {
					Var.p1.setX(Var.p1.getX()-1);
				}if(Var.P1MoveRIGHT) {
					Var.p1.setX(Var.p1.getX()+1);
				}if(Var.P2MoveUP) {
					Var.p2.setY(Var.p2.getY()-1);
				}if(Var.P2MoveDOWN) {
					Var.p2.setY(Var.p2.getY()+1);
				}if(Var.P2MoveLEFT) {
					Var.p2.setX(Var.p2.getX()-1);
				}if(Var.P2MoveRIGHT) {
					Var.p2.setX(Var.p2.getX()+1);
				}
				
				Bullet[] bullets = Var.bullets.toArray(new Bullet[0]);
				//System.out.println("rendering " + bullets.length + " bullets");
				for (Bullet bullet : bullets) {
					if (! bullet.move(Var.p1, Var.p2)) {
						Var.bullets.remove(bullet);
					}
				}
			}
		}, 0, 6);
	}
	
}
