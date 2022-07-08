package fighter;

import java.awt.Graphics;

public class Player extends Creature{

	public static int screenWidth = 1280;
	public static int screenHeight = 720;
	private Game game;
	protected int healthValue = 6;
	protected float movementSpeed = 6;
	
	public Player(Game game,float x, float y) {
		super(x, y);
		this.game = game;
	}

	//define left,right,up and down movement by keystroke 
	@Override
	public void tick() {
		if(!(healthValue<0)) {
		if(game.getKeyManager().up && y>0)
			y=y-movementSpeed;
		if(game.getKeyManager().down && y<660)
			y=y+movementSpeed;
		if(game.getKeyManager().left && x>0)
			x=x-movementSpeed;
		if(game.getKeyManager().right && x<1215)
			x=x+movementSpeed;
		
		// define how fast your jet going to attack
		if(game.getKeyManager().fire) {
			long breaks = (System.nanoTime()- game.getTime())/1000000;
			if(breaks > game.getDelay()) {
				Game.bullet.add(new Bullet((int)x+42,(int)y-20));
			}
			game.ResCurrent();
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.backGround,0,0,screenWidth,screenHeight, null);
		g.drawImage(Assets.player,(int)x,(int)y,null);
	}
	
	public void gameOver(Graphics g) {
		g.drawImage(Assets.gameover,0,0,1280,720,null);
	}
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}

}
