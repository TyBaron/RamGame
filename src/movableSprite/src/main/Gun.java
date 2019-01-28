package movableSprite.src.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Gun implements weaponEntity{
	private int width;
	private int height; 
	private int scaledHeight;
	private int scaledWidth;
	private double x;
	private double y;
	private double xTip;
	private double yTip;
	private double[] hiltX = new double[2];
	private double[] hiltY = new double[2];
	private double opposite;
	private double adjacent;
	private double hypot;
	private double mX;
	private double mY;
	private int team;
	private Game game;
	private Texture texture;
	private int left; 
	private int right;
	private char handSwitch;

	public Gun(double x, double y, Game game, Texture texture, int team, int left, int right) {
		width = 11;
		height = 15;
		scaledHeight = height*game.SCALE;
		scaledWidth = width*game.SCALE;
		this.x = x;
		this.y = y;	
		this.team = team;
		this.game = game;
		this.texture = texture;	
		this.left = left;
		this.right = right;
		handSwitch = 'L';
				
	}
	
	public void tick() {
		//-2 and +10 inorder to place gun in hand
		x = game.p.getX() - 2*game.SCALE;
		y = game.p.getY() + 10*game.SCALE;
		//mouse around middle
		mX = game.point.getX() - scaledWidth;
		mY = game.point.getY() - scaledHeight;
		hiltX[0] = x+(5*game.SCALE)+left;
		hiltY[0] = y+(13*game.SCALE);
		hiltX[1] = x+(7*game.SCALE)+right;
		hiltY[1] = y+(13*game.SCALE);
				
		adjacent = mX - game.cam.relationX;
		opposite = mY - game.cam.relationY;
		hypot = (Math.sqrt((adjacent*adjacent)+(opposite*opposite)));

		if((int)adjacent > 90)
			handSwitch = 'R';
		if((int)adjacent < -90)
			handSwitch = 'L';
		setTip();
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if(handSwitch == 'R' && adjacent > 0) {
			g2d.rotate((-Math.acos(opposite/(hypot+0.01)) - 3.14159), hiltX[1], hiltY[1]);
			g2d.drawImage(texture.gun[1], (int)x+right, (int)y, (int)x + scaledWidth + right,
					(int)y + scaledHeight, 0, 0, width, height, null);
			g2d.drawImage(texture.playerHandR, (int)hiltX[1] - 8, (int)hiltY[1] - 10,
					(int)hiltX[1] + 5*game.SCALE - 8, (int)hiltY[1] + 4*game.SCALE - 10,
					0, 0, 5, 4, null);
			g2d.rotate((Math.acos(opposite/(hypot+0.01)) - 3.14159), hiltX[1], hiltY[1]);
		}	
		else if(handSwitch == 'R' && adjacent < 0) {
			g2d.rotate((Math.acos(opposite/hypot)-3.14159), hiltX[1], hiltY[1]);
			g2d.drawImage(texture.gun[1], (int)x+right, (int)y, (int)x + scaledWidth + right,
					(int)y + scaledHeight, 0, 0, width, height, null);
			g2d.drawImage(texture.playerHandR, (int)hiltX[1] - 8, (int)hiltY[1] - 10,
					(int)hiltX[1] + 5*game.SCALE - 8, (int)hiltY[1] + 4*game.SCALE - 10,
					0, 0, 5, 4, null);			
			g2d.rotate((-Math.acos(opposite/hypot)-3.14159), hiltX[1], hiltY[1]);
		}
		else if(handSwitch == 'L' && adjacent > 0) {
			g2d.rotate((-Math.acos(opposite/(hypot+0.01)) - 3.14159), hiltX[0], hiltY[0]);
			g2d.drawImage(texture.gun[0], (int)x+left, (int)y, (int)x + scaledWidth+left, 
					(int)y + scaledHeight, 0, 0, width, height, null);
			g2d.drawImage(texture.playerHandL, (int)hiltX[0] - 16, (int)hiltY[0] - 10,
					(int)hiltX[0] + 5*game.SCALE - 16, (int)hiltY[0] + 4*game.SCALE - 10,
					0, 0, 5, 4, null);
			g2d.rotate((Math.acos(opposite/(hypot+0.01)) - 3.14159), hiltX[0], hiltY[0]);
		}

		else {
			g2d.rotate((Math.acos(opposite/hypot)-3.14159), hiltX[0], hiltY[0]);
			g2d.drawImage(texture.gun[0], (int)x+left, (int)y, (int)x + scaledWidth+left, 
					(int)y + scaledHeight, 0, 0, width, height, null);
			g2d.drawImage(texture.playerHandL, (int)hiltX[0] - 16, (int)hiltY[0] - 10,
					(int)hiltX[0] + 5*game.SCALE - 16, (int)hiltY[0] + 4*game.SCALE - 10,
					0, 0, 5, 4, null);
			g2d.rotate((-Math.acos(opposite/hypot)-3.14159), hiltX[0], hiltY[0]);
		}

	}
	public double getY() {
		return y;
	}
	public double getX() {
		return x;
	}
	public void shoot(double moveX, double moveY) {
				
		game.c.addEntity(new Bullet(xTip, yTip, texture.bullet, moveX, 
				moveY, game, team));

	}
	public void setTip() {
		if(handSwitch == 'L') {
			//System.out.println((adjacent/((Math.abs(adjacent)+Math.abs(opposite))))+"     "+(opposite/((Math.abs(adjacent)+Math.abs(opposite)))));
			xTip = x + (16 * (adjacent/((Math.abs(adjacent)+Math.abs(opposite))))*game.SCALE);
			yTip = y-8*game.SCALE + (16 * (opposite/((Math.abs(adjacent)+Math.abs(opposite))))*game.SCALE);
		}
		else {
			xTip = x + 18*game.SCALE + (16 * (adjacent/((Math.abs(adjacent)+Math.abs(opposite))))*game.SCALE);
			yTip = y-8*game.SCALE + (16 * (opposite/((Math.abs(adjacent)+Math.abs(opposite))))*game.SCALE);
		}
			
	}
	
}
