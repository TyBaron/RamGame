package movableSprite.src.main;

import java.awt.image.BufferedImage;

public class Texture {
	
	public BufferedImage[][] player = new BufferedImage[6][4];
	public BufferedImage[] monster = new BufferedImage[3];
	public BufferedImage bullet;
	public BufferedImage cursor; 
	public BufferedImage wallN;
	public BufferedImage wallE;
	public BufferedImage wallS;
	public BufferedImage wallW;
	public BufferedImage floor;
	public BufferedImage bg;
	public BufferedImage[] gun = new BufferedImage[2];
	public BufferedImage gunS;
	public BufferedImage bulletR;
	public BufferedImage corner;
	public BufferedImage playerHandL;
	public BufferedImage playerHandR;
	public BufferedImage dummy;
	
	private SpriteSheet ss; 
	
	public Texture(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	public void getTextures() {
		//0 = south, 1 = SE-E, 2 = NE, 3 = N, 4 = NW, 5 = W-SW
		player[0][0] = ss.grabImage(1, 1, 32, 32);
		player[0][1] = ss.grabImage(1, 2, 32, 32);
		player[0][2] = ss.grabImage(1, 3, 32, 32);
		player[0][3] = ss.grabImage(1, 4, 32, 32);
		
		player[1][0] = ss.grabImage(2, 1, 32, 32);
		player[1][1] = ss.grabImage(2, 2, 32, 32);
		player[1][2] = ss.grabImage(2, 3, 32, 32);
		player[1][3] = ss.grabImage(2, 4, 32, 32);
		
		player[2][0] = ss.grabImage(3, 1, 32, 32);
		player[2][1] = ss.grabImage(3, 2, 32, 32);
		player[2][2] = ss.grabImage(3, 3, 32, 32);
		player[2][3] = ss.grabImage(3, 4, 32, 32);
		
		player[3][0] = ss.grabImage(4, 1, 32, 32);
		player[3][1] = ss.grabImage(4, 2, 32, 32);
		player[3][2] = ss.grabImage(4, 3, 32, 32);
		player[3][3] = ss.grabImage(4, 4, 32, 32);
		
		player[4][0] = ss.grabImage(5, 1, 32, 32);
		player[4][1] = ss.grabImage(5, 2, 32, 32);
		player[4][2] = ss.grabImage(5, 3, 32, 32);
		player[4][3] = ss.grabImage(5, 4, 32, 32);
		
		player[5][0] = ss.grabImage(6, 1, 32, 32);
		player[5][1] = ss.grabImage(6, 2, 32, 32);
		player[5][2] = ss.grabImage(6, 3, 32, 32);
		player[5][3] = ss.grabImage(6, 4, 32, 32);
		playerHandL = ss.grabImage(8, 1, 5, 4);
		playerHandR = ss.grabImage(8, 2, 8, 4);
		
		//Monster
		monster[0] = ss.grabImage(12, 1, 32, 32);
		monster[1] = ss.grabImage(12, 2, 32, 32);
		monster[2] = ss.grabImage(12, 3, 32, 32);
		
		//dummy
		dummy = ss.grabImage(13,  1,  32, 32);
		
		bullet = ss.grabImage(11, 1, 9, 9);
		bulletR = ss.grabImage(11, 2, 9, 9);
		wallN = ss.grabImage(10, 2, 32, 32);
		wallE = ss.grabImage(10, 3, 32, 32);
		wallW = ss.grabImage(10, 4, 32, 32);
		wallS = ss.grabImage(10, 5, 32, 32);
		corner = ss.grabImage(9, 4, 32, 32);
		floor = ss.grabImage(9, 2, 32, 32);
		gun[0] = ss.grabImage(7, 1, 17, 23);
		gun[1] = ss.grabImage(7, 2, 17, 23);
		
	}

}
