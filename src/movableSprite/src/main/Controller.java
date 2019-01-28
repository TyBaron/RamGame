package movableSprite.src.main;

import java.awt.Graphics;
import java.util.LinkedList;


public class Controller {
	
	//monster player
	private LinkedList<Entity> e = new LinkedList<Entity>();
	//Bullet
	private LinkedList<leathalEntity> el = new LinkedList<leathalEntity>();
	//terrain
	private LinkedList<landEntity> landE = new LinkedList<landEntity>();
	//pick up
	private LinkedList<pickupEntity> puE = new LinkedList<pickupEntity>();
	//guns are handled by entity
	//ground
	private LinkedList<ground> gE = new LinkedList<ground>();
	
	Entity monEnt;
	leathalEntity lethEnt;
	landEntity landEnt;
	pickupEntity pickEnt;
	ground ground;
	
	Game game;
	Texture texture;
	Player p;
	
	public Controller(Game game, Texture texture, Player p) {
		this.game = game;
		this.p = p;
		this.texture = texture;
		e.add(p);
	}
	
	public void tick() {
		//player is a monster
		for(int i = 0; i < e.size(); i++) {
			monEnt = e.get(i);
			
			monEnt.tick();
		}
		
		for(int i = 0; i < el.size(); i++) {
			lethEnt = el.get(i);
			
			lethEnt.tick();
		}
		
		for(int i = 0; i < puE.size(); i++){
			pickEnt = puE.get(i);
			
			pickEnt.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < gE.size(); i++) {
			ground = gE.get(i);

			ground.render(g);		
		}
		
		for(int i = 0; i < landE.size(); i++) {
			landEnt = landE.get(i);
			
			landEnt.render(g);
		}
		
		for(int i = 0; i < e.size(); i++) {
			monEnt = e.get(i);
			
			monEnt.render(g);
			
		}	
				
		for(int i = 0; i < el.size(); i++) {
			lethEnt = el.get(i);
			
			lethEnt.render(g);
		}
		
		for(int i = 0; i < puE.size(); i++){
			pickEnt = puE.get(i);
			
			pickEnt.render(g);
		}
	
	}
	public void addEntity(Entity block) {
		e.add(block);
	}
	public void removeEntity(Entity block) {
		e.remove(block);
	}
	
	public void addEntity(leathalEntity block) {
		el.add(block);
	}
	public void removeEntity(leathalEntity block) {
		el.remove(block);
	}
	public void addEntity(landEntity block) {
		landE.add(block);
	}
	public void removeEntity(landEntity block) {
		landE.remove(block);
	}
	public void addEntity(pickupEntity block) {
		puE.add(block);
	}
	public void removeEntity(pickupEntity block) {
		puE.remove(block);
	}
	public void addEntity(ground block) {
		gE.add(block);
	}
	public void removeEntity(ground block) {
		gE.remove(block);
	}
	public LinkedList<Entity> getEntity(){
		return e;
	}
	public LinkedList<leathalEntity> getLeathalEntity(){
		return el;
	}
	public LinkedList<landEntity> getLandEntity(){
		return landE;
	}	
	public LinkedList<pickupEntity> getPickupEntity(){
		return puE;
	}

	public LinkedList<ground> getGround(){
		return gE;
	}
	

}
