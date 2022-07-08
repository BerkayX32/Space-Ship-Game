package fighter;
import java.awt.Graphics;

public class Bullet {
	private int x;
	private int y;
	private int speed;
	
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		speed=10;
	} 
	public void tick() {
		y -=speed;
	}

	public void render(Graphics g) {
		//missile position
		g.drawImage(Assets.bullet, x-23,y-8,null);
		/* for fire up 2 missiles
		g.drawImage(Assets.bullet, x-5,y-8,null);
		g.drawImage(Assets.bullet, x-45,y-8,null);
		*/
	}
	
	public int getY() {
		return y;
	}
	public int getX() {
		return x;
	}
}
