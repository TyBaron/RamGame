package movableSprite.src.main;

import java.util.LinkedList;


public class fatalCheck {
		
	//Bullet hits monster
	public static boolean Collision(leathalEntity entl, LinkedList<Entity> ent) {
		for(int i = 0; i < ent.size(); i++) {
			if(entl.getBounds().intersects(ent.get(i).getHitBox())) {
				if(entl.getTeam() != ent.get(i).getTeam())
					ent.get(i).hit();
				if(entl.getTeam() == ent.get(i).getTeam())
					return false;
				return true;
			}
		}
		return false;		
	}
	//Monster hits bullet
	public static boolean Collision(Entity ent, LinkedList<leathalEntity> entl) {
		for(int i = 0; i < entl.size(); i++) {
			if(ent.getHitBox().intersects(entl.get(i).getBounds())) {
				return true;
			}
		}
		return false;		
	}
	
	//Entity hits land;
	public static boolean lCollision(Entity ent, LinkedList<landEntity> entland) {
		for(int i = 0; i < entland.size(); i++) {
			if(ent.getBounds().intersects(entland.get(i).getBounds())) {
				return true;
			}
		}
		return false;		
	}
	
	//Bullet hits land
	public static boolean lCollision(leathalEntity entl, LinkedList<landEntity> entland) {
		for(int i = 0; i < entland.size(); i++) {
			if(entl.getBounds().intersects(entland.get(i).getBounds())) {
				return true;
			}
		}
		return false;		
	}
	//player passes pickup
	public static boolean upCollision(pickupEntity entUP, LinkedList<Entity> ent) {
		for(int i = 0; i < ent.size(); i++) {
			if(entUP.getBounds().intersects(ent.get(i).getBounds())) {
				return true;
			}
		}
		return false;		
	}
	//player touches monster
	public static boolean tCollision(Entity ent, LinkedList<Entity> mon) {
		for(int i = 1; i < mon.size(); i++) {
			if(ent.getBounds().intersects(mon.get(i).getBounds()) && ent.getLeth() == true) {
				mon.get(i).hit();
				return true;
			}
			else if(ent.getBounds().intersects(mon.get(i).getBounds())) {
				return true;
			}
		}
		return false;		
	}
}
