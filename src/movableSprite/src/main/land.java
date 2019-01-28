package movableSprite.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class land implements landEntity{
	public double x;
	public double y;
	private BufferedImage block;
	private int width;
	private int height;
	private int scale; 
	
	private Rectangle r;
	
	public land(double x, double y, int width, int height, BufferedImage block, int scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.height = height;
		this.width = width;
		
		r = new Rectangle((int)x, (int)y, width, height);
		
						
		this.block = block;
	
				
	}
	
	public void render(Graphics g) {
		g.drawImage(block,  (int)x, (int)y, (int)x + width*scale, (int)y + height*scale, 0, 0, width, height, null);
		
	}

	public Rectangle getBounds() {
	
		return r;
	}

	public double getX() {
	
		return x;
	}

	public double getY() {
	
		return y;
	}

}
