package movableSprite.src.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mouseInput extends MouseAdapter{
	
	Game game;
	
	public mouseInput(Game game) {
		this.game = game;
	}

	 public void mousePressed(MouseEvent e) {
	       game.mousePressed(e);
	   }

	  public void mouseReleased(MouseEvent e) {
	       game.mouseReleased(e);
	  }

	  public void mouseEntered(MouseEvent e) {

	  }

	  public void mouseExited(MouseEvent e) {
	    
	  }

	  public void mouseClicked(MouseEvent e) {
	      
	  }
	  public void mouseDragged(MouseEvent e) {
		  
	  }
	  public void mouseMoved(MouseEvent e) {
		  
	  }
	  
}
