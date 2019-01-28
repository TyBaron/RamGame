package movableSprite.src.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage ss;
	
	public SpriteSheet(BufferedImage ss) {
		
		this.ss = ss;
	}

	public BufferedImage grabImage(int col, int row, int width, int height) {
		
		//each image on sheet is 32*32
		BufferedImage img = ss.getSubimage((col * 33) - 32, (row * 33) - 32
				, width, height);
		return img;
	}

}

