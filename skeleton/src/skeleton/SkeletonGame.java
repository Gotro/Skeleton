package skeleton;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;


import edu.utc.game.Game;
import edu.utc.game.GameObject;
import edu.utc.game.Scene;
import edu.utc.game.SimpleMenu;

 

public class SkeletonGame extends Game implements Scene{
	
	public static void main(String[] args)
	{
		
		
		//DemoGame game=new DemoGame();
		//game.registerGlobalCallbacks();
		SkeletonGame game=new SkeletonGame();
		game.registerGlobalCallbacks();

		
		SimpleMenu menu = new SimpleMenu();
		menu.addItem(new SimpleMenu.SelectableText(30,  30,  30,  30,  "play the Game", 1,  0,  0,  1,  1,  1), game);
		menu.addItem(new SimpleMenu.SelectableText(30,  130,  30,  30,  "Exit", 1,  0,  0,  1,  1,  1), null);
		menu.select(0);

		game.setScene(menu);
		game.gameLoop();
			
	}
	
	private static java.util.Random rand=new java.util.Random();
	

	
	// DemoGame instance data
	
	List<GameObject> targets;
	Player player;
	
	public SkeletonGame()
	{
		// inherited from the Game class, this sets up the window and allows us to access
		// Game.ui
		initUI(640, 480, "Skeleton Game");

		// screen clear is black (this could go in drawFrame if you wanted it to change
		glClearColor(.0f, .0f, .0f, .0f);
		
		
		targets = new java.util.LinkedList<GameObject>();
		
		player = new Player();
		spawnTargets(10);
		
		
	}
	
	public void spawnTargets(int count)
	{
		float r = rand.nextFloat()*0.5f+0.25f;
		float g = rand.nextFloat()*0.5f+0.25f;
		float b = rand.nextFloat()*0.5f+0.25f;
		
		for (int i=0; i<count; i++)
		{
			targets.add(new Target(player, r, g, b));
		}
	}
	
	
	public Scene drawFrame(int delta) {
		glClearColor(1f, 1f, 1f, 1f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

		player.update(delta);

		// update all targets and player object
		for (GameObject o : targets)
		{
			o.update(delta);
		}
		
		// check for deactivated objects
		Iterator<GameObject> it = targets.iterator();
		while (it.hasNext()) {
			GameObject o = it.next();
			if (! o.isActive())
			{
				it.remove();
			}
		}
		
		// if all targets have been destroyed, spawn some more
		if (targets.isEmpty()) {
			spawnTargets(10);
		}
		
		// draw existing targets
		for (GameObject o : targets)
		{
			o.draw();
		}
		
		// draw the player last so it will appear on top of targets
		player.draw();
		
		
		boolean gamePaused = false;
		while(!Display.isCloseRequested()){

			if(Game.ui.keyPressed(org.lwjgl.glfw.GLFW.GLFW_KEY_P) && !gamePaused) {
			    gamePaused = true;
			} else if(Game.ui.keyPressed(org.lwjgl.glfw.GLFW.GLFW_KEY_P) && gamePaused) {
			    gamePaused = false;
			}
			
		    if(gamePaused){
		        while(true){
		            System.out.println("Game paused.");
		            if(Game.ui.keyPressed(org.lwjgl.glfw.GLFW.GLFW_KEY_P)){
		                gamePaused = false;
		                break;
		                
		            }
		        }
		    }
		}
		
		
		
		return this;
	}
	
	public static enum DIR { LEFT, RIGHT, UP, DOWN };
	
	private class Player extends GameObject
	{

		
		public DIR direction=DIR.LEFT;
		
		public Player()
		{
			this.hitbox.setSize(10, 10);
			this.hitbox.setLocation(Game.ui.getWidth()/2-5, Game.ui.getHeight()/2-5);
			this.setColor(1,0,0);
		}
		
		// this allows you to steer the player object
		public void update(int delta)
		{
			float speed=0.25f;
			if (Game.ui.keyPressed(org.lwjgl.glfw.GLFW.GLFW_KEY_UP))
			{
				this.hitbox.translate(0,  (int)(-speed*delta));
				direction=DIR.UP;
			}
			if (Game.ui.keyPressed(org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN))
			{
				this.hitbox.translate(0,  (int)(speed*delta));
				direction=DIR.DOWN;
			}
			if (Game.ui.keyPressed(org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT))
			{
				this.hitbox.translate((int)(-speed*delta), 0);
				direction=DIR.LEFT;
			}
			if (Game.ui.keyPressed(org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT))
			{
				this.hitbox.translate((int)(speed*delta),0);
				direction=DIR.RIGHT;
			}
		}
	}
	
	private class Target extends GameObject
	{
		private Player player;
		private int size=50;
		
		
		// construct a target in a random location within the bounds of the UI
		public Target(Player p, float r, float g, float b)
		{
			this.player = p;
			this.hitbox.setSize(size, size);
			this.setColor(r,g,b);
			this.hitbox.setLocation(
					(int)(rand.nextFloat()*Game.ui.getWidth()),
					(int)(rand.nextFloat()*Game.ui.getHeight()))
					;
			//System.out.println(this.hitbox);
		}

		// if the space key is pressed, check to see if we should deactivate this target
		public void update(int delta)
		{
			if (//Game.ui.keyPressed(org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE) && 
					player.intersects(this)) {
				
				Rectangle overlap=player.intersection(this);
				int dx=0;
				int dy=0;
				
				switch (player.direction)
				{
				case LEFT: dx=-(int)overlap.getWidth(); break;
				case RIGHT: dx=(int)overlap.getWidth(); break;
				case UP: dy=-(int)overlap.getHeight(); break;
				case DOWN: dy=(int)overlap.getHeight(); break;
				
				}
				this.hitbox.translate(dx, dy);
				// this.deactivate();
				
				if (this.hitbox.getX() < 0 || 
					this.hitbox.getX()> Game.ui.getWidth() ||
					this.hitbox.getY() < 0 ||
					this.hitbox.getY() > Game.ui.getHeight())
				{
					this.deactivate();
				}
			}
		}
	
	}
	
	
	

}

