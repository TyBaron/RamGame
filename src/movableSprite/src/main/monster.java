package movableSprite.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class monster implements Entity{
	
	private double x;
	private double y;
	private Game game;
	private static int hp;
	public Texture texture;
	private int team = 1;
	private Random rand = new Random();
	private int width;
	private int height;
	private int scaledHeight;
	private int scaledWidth;
	public boolean leathBod;
	
	Animation anime;
	botMove move;
	
	public monster(double x, double y, Texture texture, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;
		this.leathBod = true;
		this.texture = texture;
		width = 29;
		height = 31;
		scaledWidth = width*game.SCALE;
		scaledHeight = height*game.SCALE;
		
		hp = 5;
		
		anime = new Animation(12, texture.monster[0], texture.monster[1], texture.monster[2]);
		move = new botMove(this, game);
	}
	
	public void tick() {
		int n = rand.nextInt(60) + 1;
		
		if(hp <= 0) {
			game.c.removeEntity(this);
		}
		
		double v = x;
		double w = y;

//		if(n == 8) 
//			shoot();
//		else 
			move.follow();
//		
		anime.runAnimation();
		
		if(fatalCheck.lCollision(this, game.entland)) {
			y = w;
			x = v;
		}
	}
	public void render(Graphics g) {
		anime.drawAnimation(g, x, y, width, height, scaledHeight, scaledHeight);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void moveX(double x) {
		this.x += x;
	}
	public void moveY(double y) {
		this.y += y;
	}
	
	public void hit() {
		hp--;
	}
	
	public void shoot() {
		double wayX = game.p.getX() - x;
		double wayY = game.p.getY() - y;
		game.c.addEntity(new Bullet(x, y, texture.bulletR, wayX, wayY, game, team));
	}

	public Rectangle getHitBox() {
		return new Rectangle((int)x, (int)y, scaledWidth, scaledHeight);
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, scaledWidth, scaledHeight);
	}

	public int getTeam() {
		return team;
	}
	public boolean getLeth() {
		return leathBod;
	}
}