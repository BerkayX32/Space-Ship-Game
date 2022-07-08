package fighter;

import java.awt.Graphics;
import java.util.Random;

public class Ufo extends Creature {

	private long lastFiredLaser = 0;
	private long fireDelay = 2500;

	public Ufo(float x, float y) {
		// TODO Auto-generated constructor stub
		super(x, y);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		y=y+1;
		if (this.y > 0) {
			Random random = new Random();
			int randNum = random.nextInt(100);
			if (randNum == 0 && lastFiredLaser + fireDelay < System.currentTimeMillis()) {
				lastFiredLaser = System.currentTimeMillis();
				Game.lasers.add(new EnemyBullet((int) (this.x + 32), (int) (this.y + 64)));
			}
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.ufo,(int)x,(int)y,null);   
	}
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}

}
