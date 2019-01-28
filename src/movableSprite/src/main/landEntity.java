package movableSprite.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface landEntity {
	
	//public void tick();//probably don't need but might for destruction
	public void render(Graphics g);
	public Rectangle getBounds();
	public double getX();
	public double getY();

}
