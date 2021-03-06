package fighter;

import java.awt.Graphics;

public class EnemyBullet {
	private int x;
	private int y;
	private int speed;
	
	
	public EnemyBullet(int x, int y) {
		this.x = x;
		this.y = y;
		speed=5;
	}
	
	public void tick() {
		y +=speed;
	}

	public void render(Graphics g) {
		g.drawImage(Assets.enemyBullet, x-13,y-4,null);
	}
	
	public int getY() {
		return y;
	}
	public int getX() {
		return x;
	}
}
