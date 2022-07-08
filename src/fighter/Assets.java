package fighter;
import java.awt.image.BufferedImage;

public class Assets {
	
	//keeps image locations
	public static BufferedImage player;
	public static BufferedImage ufo;
	public static BufferedImage backGround;
	public static BufferedImage bullet;
	public static BufferedImage explosion;
	public static BufferedImage gameover;
	public static BufferedImage enemyBullet;
	public static BufferedImage healthbar;
	public static BufferedImage healthbarTwo;
	public static BufferedImage healthbarThree;
	public static BufferedImage healthbarFour;
	public static BufferedImage healthbarFive;
	public static BufferedImage healthbarSix;
	
	public static void init() {
		player = ImageLoader.loadImage("/textures/spaceShip.png");
		ufo = ImageLoader.loadImage("/textures/ufo.png");
		backGround = ImageLoader.loadImage("/textures/space.png");
		bullet = ImageLoader.loadImage("/textures/missile.png");
		explosion = ImageLoader.loadImage("/textures/explosion.png");
		gameover =  ImageLoader.loadImage("/textures/gameover.png");
		enemyBullet = ImageLoader.loadImage("/textures/enemybullet.png");
		healthbar = ImageLoader.loadImage("/textures/healthbar.png");
		healthbarTwo = ImageLoader.loadImage("/textures/healthbar2.png");
		healthbarThree = ImageLoader.loadImage("/textures/healthbar3.png");
		healthbarFour = ImageLoader.loadImage("/textures/healthbar4.png");
		healthbarFive = ImageLoader.loadImage("/textures/healthbar5.png");
		healthbarSix = ImageLoader.loadImage("/textures/healthbar6.png");
	}
	
}
