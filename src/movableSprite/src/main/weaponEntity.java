package movableSprite.src.main;

import java.awt.Graphics;

public interface weaponEntity {
	public void tick();
	public void render(Graphics g);
	
	public double getX();
	public double getY();
	
	public void shoot(double moveX, double moveY);
}
