package movableSprite.src.main;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
//import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int SCALE = 4;
	public static final int WIDTH = 480*SCALE;
	public static final int HEIGHT = 270*SCALE;
			
	private boolean running = false;
	private Thread thread;
	private JFrame frame = new JFrame();

	public int keyX;//x value that is being held;
	public int keyY;//y value that is being held;
	public boolean keys[] = new boolean[4];//keys being held;
	public boolean mouseHeld = false; 
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private Texture texture;
	//private Toolkit toolkit = Toolkit.getDefaultToolkit();
	//private Cursor cursor;
	private PointerInfo mouseLocation;
	public Point point;
	
	public Camera cam;
	private level1 level;
	public Player p;
	public Controller c;
	//private Sounds s = new Sounds();
	
	public LinkedList<Entity> ent;
	public LinkedList<leathalEntity> entl;
	public LinkedList<landEntity> entland;
	public LinkedList<pickupEntity> entPickup;
	
	public void init() {
		
		//s.playSounds();
		requestFocus();
		imageLoader loader = new imageLoader();
		try {
			spriteSheet = loader.loadImage("/trueRam.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));
		addMouseListener(new mouseInput(this));
		
		texture = new Texture(this);
//		cursor = toolkit.createCustomCursor(texture.cursor, new Point(this.getX(),
//				this.getY()), "cursor");
						
		p = new Player(241*SCALE, 225*SCALE, texture, this);
		c = new Controller(this, texture, p);
		level = new level1(c, texture, SCALE, this);
		
		//setCursor(cursor);
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		cam = new Camera(0, 0);
		
		ent = c.getEntity();
		entl = c.getLeathalEntity();
		entland = c.getLandEntity();
		entPickup = c.getPickupEntity();
		
	}	
	
	private synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
	
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	private synchronized void restart() {

		init();
	}
	
	public void run() {
		init();
		
		long lastTime = System.nanoTime();
		final double tickAmount = 60.0;
		double ns = 1000000000 / tickAmount;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				updates++;
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps "+ frames);
				updates = 0; 
				frames = 0;
			}
			//spawn 
						
		}
	}
	
	private void tick() {
		
		updateMove();
		mouseLocation = MouseInfo.getPointerInfo();
		point = mouseLocation.getLocation();
		point.x = (int) (point.x-frame.getLocationOnScreen().getX());
		point.y = (int) (point.y-frame.getLocationOnScreen().getY());
			
		c.tick();				
		cam.tick(this);	
	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			//3 buffers
			createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		//draw////////////////////////////////////////////////
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		//begin camera;
		g2d.translate(cam.getX(), cam.getY());
		
		level.render(g);
		c.render(g);
		g2d.translate(-cam.getX(), -cam.getY());
		//end camera;
		//end here////////////////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	public void keyPressed( KeyEvent e) {
		int key = e.getKeyCode();
		
		
		if(key == KeyEvent.VK_W) {
		
			if(keys[0] == false) {
			keyY = -1;
			keys[0] = true;
			}
			
		}
		else if(key == KeyEvent.VK_A) {
			
			if(keys[1] == false) {
			keyX = -1;
			keys[1] = true;
			}
		}
		else if(key == KeyEvent.VK_S) {
			
			if(keys[2] == false) {
			keyY = 1;
			keys[2] = true;
			}
		}
		else if(key == KeyEvent.VK_D) {
		
			if(keys[3] == false) {
			keyX = 1;
			keys[3] = true;
			}
		}
		else if(key == KeyEvent.VK_SPACE) {
			p.dash();
		}
		
	}
	
	public void keyReleased (KeyEvent e) {
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_W) {
		
			keys[0] = false;
			if(keys[2]==true)
				keyY = 1;
			else
				keyY = 0;

		}
		else if(key == KeyEvent.VK_A) {
	
			keys[1] = false;
			if(keys[3]==true)
				keyX = 1;
			else
				keyX = 0;
		}
		else if(key == KeyEvent.VK_S) {
		
			keys[2] = false;
			if(keys[0]==true)
				keyY = -1;
			else
			    keyY = 0;
		}
		else if(key == KeyEvent.VK_D) {
			
			keys[3] = false;
			if(keys[1]==true)
				keyX = -1;
			else
				keyX = 0;
		}
		else if(key == KeyEvent.VK_R) {
			stop();
		}
		else if(key == KeyEvent.VK_T) {
			restart();			
		}
		
	}
	
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			mouseHeld = true;

	}

	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			mouseHeld = false;		
		
	}
	
		
	private void updateMove() {
		
		p.setVelX(keyX);
		p.setVelY(keyY);
						
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setResizable(false);
		game.frame.setLocationRelativeTo(null);
		//game.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		game.frame.setVisible(true);
		
		game.start();
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

}
