package movableSprite.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Entity {
	//overall entity, can create more for object bounds and such.
	
	public void tick();
	public void render(Graphics g);
	public Rectangle getHitBox();
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
	public void moveX(double x);
	public void moveY(double y);
	public void hit();
	public int getTeam();
	public boolean getLeth();

}
