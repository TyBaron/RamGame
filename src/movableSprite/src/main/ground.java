package movableSprite.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ground {
	public double x;
	public double y;
	private BufferedImage block;
	private int width;
	private int height;
	private int scale;
	
	public ground(double x, double y, int width, int height, BufferedImage block, int scale) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.scale = scale;
		this.block = block;
	
				
	}
	
	public void render(Graphics g) {
		g.drawImage(block,  (int)x, (int)y, (int)x + width*scale, (int)y + height*scale,
				0, 0, width, height, null);
		
	}

	public double getX() {
	
		return x;
	}

	public double getY() {
	
		return y;
	}

}

