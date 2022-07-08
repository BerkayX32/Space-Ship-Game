package fighter;
import java.awt.Graphics;

public abstract class Entity {

	public abstract void render(Graphics g);
	public abstract void tick();
	
	protected float x;
	protected float y;
	
	public Entity(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
