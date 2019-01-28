package movableSprite.src.main;

public class Camera {
	private double x, y, mX, mY;
	public double relationX, relationY;
	
	public Camera(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(Game game) {
		mX = game.point.getX()+28 - (Game.WIDTH/2) ;
		mY = game.point.getY()+58 - (Game.HEIGHT/2);
		x = (-game.p.getX() + (Game.WIDTH/2)-28) - (mX/4);
		y = (-game.p.getY() + (Game.HEIGHT/2)-58) - (mY/4);
		relationX = ((Game.WIDTH/2)) - (mX/4);
		relationY = ((Game.HEIGHT/2)) - (mY/4);
	}
	
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}

}
