package movableSprite.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
//import java.io.IOException;

public class level1 {
	private BufferedImage background;


	public level1(Controller c, Texture tex, int scale, Game game) {

		//North
		c.addEntity(new ground(0, 0, 32*scale, 32*scale, tex.corner, scale));
		for(int i = 32*scale; i<475*scale; i=i+32*scale) {
			c.addEntity(new land(i, 0, 32*scale, 32*scale, tex.wallN, scale));
		}
		//East
		c.addEntity(new ground(0, 1152, 32*scale, 32*scale, tex.corner, scale));
		for(int i = 32*scale; i<272*scale; i=i+32*scale) {
			c.addEntity(new land(480*scale, i, 32*scale, 32*scale, tex.wallE, scale));
		}
		//West
		c.addEntity(new ground(1920, 1152, 32*scale, 32*scale, tex.corner, scale));
		for(int i = 32*scale; i<272*scale; i=i+32*scale) {
			c.addEntity(new land(0, i, 32*scale, 32*scale, tex.wallW, scale));
		}
		//South
		c.addEntity(new ground(1920, 0, 32*scale, 32*scale, tex.corner, scale));
		for(int i = 32*scale; i<475*scale; i=i+32*scale) {
			c.addEntity(new land(i, 288*scale, 32*scale, 32*scale, tex.wallS, scale));
		}
		for(int i = 32*scale; i<480*scale; i=i+32*scale) {
			for(int j = 32*scale; j<270*scale; j=j+32*scale) {
				c.addEntity(new ground(i, j, 32*scale, 32*scale, tex.floor, scale ));
			}
		}
		c.addEntity(new monster(81*game.SCALE, 37*game.SCALE, tex, game));
		game.p.makeWeapon();
	}

	public void render(Graphics g) {
		g.drawImage(background, 0, 0, null);		
	}

}
