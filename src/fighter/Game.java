package fighter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Runnable{

	public static ArrayList<Bullet> bullet;
	private static ArrayList<Ufo> enemies;
	public static ArrayList<EnemyBullet> lasers;
	private State gameState;
	private KeyStroke keyManager;

	private Display display;
	public String title;
	public String userName;
	public int width;
	public int height;
	public long current;
	public long delay;
	
	private int score;
	public int playerXaxis;
	public int playerYaxis;
	
	public int enemyXaxis;
	public int enemyYaxis;
	
	private long current2;
	private long delayEnemy;
	
	private Thread thread;
	private boolean running=false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	public static int hitScore = 10;

	public void  ResCurrent() {
		this.current=System.nanoTime();
	}
	
	public void setCurrent() {
		this.current=System.nanoTime();
	}
	private int delay() {
		delay=100;
		return (int) delay;
	}
	
	public void setTime() {
		this.current=getTime();
		this.delay=delay();
	}
	
	public long getTime() {
		return current;
	}
	
	public int getDelay() {
		return (int) delay;
	}
	
	
	public Game(int width,int height,String userName,String title){
		this.width = width;
		this.height = height;
		this.userName = userName;
		this.title = title;
		keyManager = new  KeyStroke();
	}

	private void init(){
		setTime();
		display = new Display(title,width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		gameState = new GameState(this);
		State.setState(gameState);
		
		bullet = new ArrayList<Bullet>();
		enemies = new ArrayList<Ufo>();
		lasers = new ArrayList<>();
		
		current2 = System.nanoTime();
		delayEnemy =1300;
		score = 0;
	}
	

	private void tick(){
		keyManager.tick();
		
		for(int i=0;i<bullet.size();i++) {
			bullet.get(i).tick();
		}
		for(int i=0;i<lasers.size();i++) {
			lasers.get(i).tick();
		}

		if(State.getState() != null) {
			State.getState().tick();
		}
		
		long breaksForEnemy = (System.nanoTime()- current2)/1000000;
		if(breaksForEnemy > delayEnemy) {
			for(int i=0;i<2;i++) {
				Random rand = new Random();
				int randX = rand.nextInt(960);
				int randY = rand.nextInt(250);
				enemies.add(new Ufo(randX,-randY));
			}
			current2 = System.nanoTime();
		}
		
		for(int i=0;i<enemies.size();i++) {
			enemies.get(i).tick();
		}
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if( bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//clear
		g.clearRect(0, 0, width, height);
		//draw
		
		if(State.getState() != null) {
			State.getState().render(g);
		}
		
		
		if(gameState.getHP()>=0) {
		//bullet render
		for(int i=0;i<bullet.size();i++) {
			bullet.get(i).render(g);
		}
		
		//if bullet goes out of map
		for(int j=bullet.size()-1;j>=0;j--) {
			if(bullet.get(j).getY()<=10) {
				bullet.remove(j);
			}
		}
		
		//enemylaser render
		for(int k=0;k<lasers.size();k++) {
			lasers.get(k).render(g);
		}
		
		//enemy render
		for(int i=0;i<enemies.size();i++) {
			if(!(gameState.getHP()<0)) {
			if (!(enemies.get(i).getX()<=60 ||enemies.get(i).getX()>=960 || enemies.get(i).getY()>=660))
					enemies.get(i).render(g);
			}
		}
			
		//enemy ile player collisoný
		for(int i=0;i<enemies.size();i++) {

			playerXaxis = gameState.getX();
			playerYaxis = gameState.getY();
			
			enemyXaxis = enemies.get(i).getX();
			enemyYaxis = enemies.get(i).getY();
			
			if (playerXaxis < enemyXaxis + 25 && playerXaxis + 30 > enemyXaxis && playerYaxis < enemyYaxis + 25 && playerYaxis + 30 > enemyYaxis) {
				enemies.remove(i);
				i--;
				gameState.decHP();
			}

			//bullet hits the enemy and adds scores
			for (int j = 0; j < bullet.size(); j++) {
				int bx = bullet.get(j).getX();
				int by = bullet.get(j).getY();

				if (enemyXaxis < bx + 8 && enemyXaxis + 50 > bx && enemyYaxis < by + 8 && enemyYaxis + 50 > by) {
					enemies.remove(i);
					g.drawImage(Assets.explosion, enemyXaxis + 8, enemyYaxis + 8, null);
					i--;
					bullet.remove(j);
					j--;
					score=score+hitScore;
				}
			}
		}

		//enemylaser ile player collisoný
		for(int k=0;k<lasers.size();k++){
			int	elx = lasers.get(k).getX();
			int	ely = lasers.get(k).getY();
			playerXaxis=gameState.getX();
			playerYaxis=gameState.getY();
			if(playerXaxis < elx + 6 && playerXaxis + 128 > elx && playerYaxis < ely  && playerYaxis + 64 > ely){
				gameState.decHP();
				lasers.remove(k);
				k--;
				g.drawImage(Assets.explosion,elx + 3 - 16,ely + 5 - 16,null);
			}
		}
		
		drawHealthBar();
		drawScoreString();
		
		}
		bs.show();
		g.dispose();
	}

	private void drawHealthBar() {
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.ITALIC,15));
		g.drawString("Health Bar",1025, 630);
		
		if(gameState.getHP()==6) {
			g.drawImage(Assets.healthbarSix, 1000, 640, null);
		}else if(gameState.getHP()==5){
			g.drawImage(Assets.healthbarFive, 1000, 640, null);
		}else if(gameState.getHP()==4){
			g.drawImage(Assets.healthbarFour, 1000, 640, null);
		}else if(gameState.getHP()==3){
			g.drawImage(Assets.healthbarThree, 1000, 640, null);
		}else if(gameState.getHP()==2){
			g.drawImage(Assets.healthbarTwo, 1000, 640, null);
		}else if(gameState.getHP()==1){
			g.drawImage(Assets.healthbar, 1000, 640, null);
		}
		
	}

	public int getScore() {
		return score;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void run() {
		init(); 
		int fps = 60;		
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime(); 
		long timer = 0;

		while(running){
			now=System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now-lastTime;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				delta--;
			}
			if(timer >=1000000000) {
				timer=0;
			}
		}
		stop();
	}	
	
	public KeyStroke getKeyManager() {
		return keyManager;
	}
	
	//print score on screen
	public void drawScoreString() {
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.ITALIC,30));
		g.drawString("Score: "+score, 1100, 50);
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){	
		if(!running)
		return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
	


