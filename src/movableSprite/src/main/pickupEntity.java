package movableSprite.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface pickupEntity {
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
}
