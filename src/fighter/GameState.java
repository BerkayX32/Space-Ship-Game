package fighter;

import java.awt.Graphics;
import java.io.IOException;

public class GameState extends State {
	public int px;
	public int py;
	private Player player;
	//added prevent infinite loop writing score 
	private boolean control = false;
	
	public GameState(Game game) {
		super(game);
		player = new Player(game,600,600);
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		player.tick();
		px = player.getX();
		py = player.getY();
	}
	
	public void decHP(){
		player.healthValue--;
	}

	public int getHP() {
		return player.healthValue;
	}
	public int getX() {
		return px;
	}
	public int getY() {
		return py;
	}
	
	@Override
	public void render(Graphics g) {
		
		if(!(player.healthValue<0)) {
			player.render(g);
		}else{
			player.gameOver(g);
		}
		if(player.healthValue<0 && control == false) {
			ScoreTable write = new ScoreTable(game.getScore(), game.getUserName());
			try {
				write.writeScore();
				control=true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
