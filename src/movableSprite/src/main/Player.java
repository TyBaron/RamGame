package movableSprite.src.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Player implements Entity{
	
	private double x;
	private double y;
	private int hp;
	//direction character is facing
	private int facing = 0;
		
	private double velX = 0;
	private double velY = 0;
	
	public double dashX = 0;
	public double dashY = 0;
	
	private int dashTime = 0;
	private int dashCool = 0;
	private int speed = 8;
	private int diagSpeed = (int) Math.sqrt((speed*speed)+(speed*speed))/2;
	//bullet team
	private int team = 0;
	private int shotCool = 10;
	private boolean leathBod;
	
	public BufferedImage[] player = new BufferedImage[6];
	
	private Texture texture; 
	private Game game;
	private int width;
	private int height;
	private int scaledWidth;
	private int scaledHeight;
	public weaponEntity gun;
	public boolean armed;
	private double mX;
	private double mY;
	private double opposite;
	private double adjacent;
	private int rightHand;
	private int leftHand;
	
	Animation[] anime = new Animation[6];
	
	public Player(double x, double y, Texture texture, Game game) {
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.game = game;
		hp = 1;
		leathBod = false;
		width = 29;
		height = 32;
		rightHand = 20*game.SCALE;
		leftHand = 1*game.SCALE;
		scaledWidth = width*game.SCALE;
		scaledHeight = height*game.SCALE;
		armed = false;
		
		anime[0] = new Animation(10, texture.player[0][1], texture.player[0][2], texture.player[0][3]);
		anime[1] = new Animation(10, texture.player[1][1], texture.player[1][2], texture.player[1][3]);
		anime[2] = new Animation(10, texture.player[2][1], texture.player[2][2], texture.player[2][3]);
		anime[3] = new Animation(10, texture.player[3][1], texture.player[3][2], texture.player[3][3]);
		anime[4] = new Animation(10, texture.player[4][1], texture.player[4][2], texture.player[4][3]);
		anime[5] = new Animation(10, texture.player[5][1], texture.player[5][2], texture.player[5][3]);
		
	}
	
	public void tick() {
		//check if dead
		if(hp <= 0) {
			game.c.removeEntity(this);
		}
		//check if shoot
		if(game.mouseHeld == true && shotCool > 9) {			
			shoot();
			shotCool--;
		}
		if(shotCool < 10) {
			shotCool--;
		}
		if(shotCool < 0) {
			shotCool = 10;
		}
		
		//check if in enemy 
		fatalCheck.tCollision(this, game.ent);
		
		//dashing
		if(dashTime > 0) {
			double h = x;
			double w = y;
		
			x+=dashX;
			if(fatalCheck.lCollision(this, game.entland)) {
				x = h;
			}
		
			y+=dashY;
			if(fatalCheck.lCollision(this, game.entland)) {
				y = w;
			}
			dashTime--;
		}
		
		else {
			leathBod = false;
			if(dashCool > 0) {
				dashX = 0;
				dashY = 0;
				dashCool--;
			}
			mX = game.point.getX();
			mY = game.point.getY();
			
			//facing animation
			adjacent = mX - game.cam.relationX;
			opposite = mY - game.cam.relationY;
			if(armed == true) {
				if(opposite < 0 && adjacent > -100 && adjacent < 100)
					facing = 3;
				else if(opposite > 0 && adjacent > -120 && adjacent < 120)
					facing = 0;
				else if(opposite < -60 && adjacent  < 0)
					facing = 4;
				else if(opposite  > -60 && adjacent < 0)
					facing = 5;
				else if(opposite < -60 && adjacent > 0)
					facing = 2;
				else if(opposite > -60 && adjacent > 0)
					facing = 1;
			}
			else {
				if(velX < -1 && velY < -1)
					facing = 4;
				else if(velX > 1 && velY < -1)
					facing = 2;
				else if(velX < -1)
					facing = 5;
				else if(velX > 1)
					facing = 1;
				else if(velY > 1)
					facing = 0;
				else if(velY < -1)
					facing = 3;
			}
			//place holders
			double h = x;
			double w = y;
		
			x+=velX;
			if(fatalCheck.lCollision(this, game.entland)) {
				x = h;
			}
		
			y+=velY;
			if(fatalCheck.lCollision(this, game.entland)) {
				y = w;
			}
		
			anime[0].runAnimation();
			anime[1].runAnimation();
			anime[2].runAnimation();
			anime[3].runAnimation();
			anime[4].runAnimation();
			anime[5].runAnimation();
		}
		if(armed == true) {
			gun.tick();
		}
	}
	
	public void render(Graphics g) {
		if(armed == true && (facing == 4 || facing == 2 || facing == 3)) {
			gun.render(g);
		}
		Graphics2D g2d = (Graphics2D) g;
		if(velX != 0 || velY !=0) {
			anime[facing].drawAnimation(g2d, x, y, width, height, scaledWidth, scaledHeight);
		}
		
		else 
			g2d.drawImage(texture.player[facing][0], (int)x, (int)y, (int)x + scaledWidth, (int)y + scaledHeight, 0, 0, width, height, null);
		if(armed == true && (facing == 1 || facing == 0 || facing == 5)) {
			gun.render(g);
		}
	}
	
	public void dash() {
		if(dashCool == 0 && ( velY != 0 || velX != 0)) {
			leathBod = true;
			dashTime = 10;
			dashCool = 120;
			
			if(velX < -1)
				dashX = -25;
			else if(velX > 1)
				dashX = 25;
			if(velY < -1)
				dashY = -25;
			else if(velY >1)
				dashY = 25;
		}
	}
	
	public Rectangle getHitBox() {
		return new Rectangle((int)x+6*game.SCALE, (int)y+3*game.SCALE, scaledWidth-12*game.SCALE, scaledHeight-6*game.SCALE);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x+6*game.SCALE, (int)y+3*game.SCALE, scaledWidth-12*game.SCALE, scaledHeight-6*game.SCALE);
	}

	public void hit() {
		hp--;		
	}

	public int getTeam() {
		return team;
	}
	
	public void makeWeapon() {
		armed = true;
		gun = new Gun(0, 0, game, texture, team, leftHand, rightHand);
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setVelX(double velX) {
		if(this.velY >= 1 || this.velY <= -1) {
			this.velX = velX*diagSpeed;
		}
		else {
			this.velX = velX*speed;
		}
	}
	public void setVelY(double velY) {
		if(this.velX >= 1 || this.velX <= -1) {
			this.velY = velY*diagSpeed;
		}
		else 
			this.velY = velY*speed;
	}
	
	public void shoot() {
		if(armed == true) {
					
			double moveX = game.point.getX() - game.cam.relationX-14;
			double moveY = game.point.getY() - game.cam.relationY-60;
		
			gun.shoot(moveX, moveY);
		}
	}

	public void moveX(double x) {
		
	}

	public void moveY(double y) {
		
	}
	
	public boolean getLeth() {
		return leathBod;
	}
}
