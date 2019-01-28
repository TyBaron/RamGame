package movableSprite.src.main;

public class botMove {
	
	private Entity ent;
	private Game game;
	
	
	public botMove(Entity ent, Game game) {
		this.ent = ent;
		this.game = game;
	}
	
	public void follow() {
		
		//follow player
		double disX = (game.p.getX()-ent.getX());
		double disY = (game.p.getY()-ent.getY());
		double tot;
		tot = (Math.abs(disX) + Math.abs(disY));
				
		ent.moveY((disY/tot));
		ent.moveX((disX/tot));
		
	}
	
	public void sprint() {
		
		//run to player
		double disX = (game.p.getX()-ent.getX());
		double disY = (game.p.getY()-ent.getY());
		double tot;
		tot = (Math.abs(disX) + Math.abs(disY));
				
		ent.moveY((disY/tot)*5);
		ent.moveX((disX/tot)*5);
	}

}
