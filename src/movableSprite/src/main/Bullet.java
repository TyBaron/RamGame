package movableSprite.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.lang.Math;


public class Bullet implements leathalEntity{
	
	private int centerX;
	private int centerY;
	private int width; 
	private int height;
	private double x;
	private double y;
	private double moveX;
	private double moveY;
	private Game game;
	private int team;
	private int hp;
	
	private BufferedImage texture;

	private int speed = 4*game.SCALE;
	
	public Bullet(double x, double y, BufferedImage texture, double wayX, double wayY, Game game, int team) {
		width = 7;
		height = 7;
		centerX = (width+1)/2;
		centerY = (height+1)/2;
		this.x = x+28-centerX;
		this.y = y+58-centerY;
		this.game = game;
		this.team = team;
		hp = 3;

		moveX = wayX;
		moveY = wayY;
		double tot = Math.abs(wayX) + Math.abs(wayY);
		moveX =  (moveX)/(tot) * speed;
		moveY = (moveY)/(tot) * speed;
						
		this.texture = texture;	
				
	}
	
	public void tick() {
		if(hp <= 0) {
			game.c.removeEntity(this);
		}
		//remove bullet if hit monster
		if(fatalCheck.Collision(this, game.ent)) {
			game.c.removeEntity(this);
		}
				
		//remove bullet if hit land
//		if(fatalCheck.lCollision(this, game.entland)) {
//			game.c.removeEntity(this);
//		}
		
		//land reflect bullet
		if(fatalCheck.lCollision(this, game.entland)) {
			moveX = moveX*-1;
			moveY = moveY*1;
			double tempX = x;
			double tempY = y;
			x += moveX;
			y += moveY;	
			if(fatalCheck.lCollision(this, game.entland)) {
				moveY = moveY*-1;
				moveX = moveX*-1;
			}
			x = tempX;
			y = tempY;
			hp--;
		}

		x += moveX;
		y += moveY;

	}
	public void render(Graphics g) {
		g.drawImage(texture, (int)x, (int)y, (int)x + width*game.SCALE, (int)y 
				+ height*game.SCALE, 0, 0, width, height, null);
	}
	
	public double getY() {
		return y;
	}
	
	public double getX() {
		return x;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width*game.SCALE, height*game.SCALE);
	}

	public int getTeam() {
		return team;
	}

}
