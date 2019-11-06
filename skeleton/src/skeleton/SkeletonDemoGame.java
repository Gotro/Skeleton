package skeleton;


import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.opengl.GL11;

import edu.utc.game.Game;
import edu.utc.game.GameObject;
import edu.utc.game.Scene;
import edu.utc.game.SimpleMenu;
import edu.utc.game.XYPair;
import edu.utc.game.SimpleMenu.SelectableText;
public class SkeletonDemoGame extends Game implements Scene {
private static java.util.Random rand=new java.util.Random();

	
	public static void main(String[] args)
	{
	
		// construct a DemoGame object and launch the game loop
		// DemoGame game = new DemoGame();
		// game.gameLoop();
	
		SkeletonDemoGame game=new SkeletonDemoGame();
		game.registerGlobalCallbacks();

		SimpleMenu menu = new SimpleMenu();
		menu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Launch Game", 1, 0, 0, 1, 1, 1), game);
		menu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit", 1, 0, 0, 1, 1, 1), null);
		menu.select(0);

		game.setScene(menu);
		game.gameLoop();
	}

	private boolean gotClick=false;
	// DemoGame instance data
	
	List<GameObject> targets;
	int x =0;
	
	public SkeletonDemoGame()
	{
		initUI(640,480,"Mouse Game");
    	glClearColor(1.0f, 1.0f, 1.0f, 0.0f);

		
		// see also Game.ui.enableMouseCursor(false);
		Game.ui.showMouseCursor(false);
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);

	
		
		// I didn't want to hide this in the UI class just yet, since it's key to understanding how GLFW and callbacks work
		// You might want to do something similar to this to detect keyboard or game controller events.
		
		GLFW.glfwSetMouseButtonCallback(Game.ui.getWindow(), 
				new GLFWMouseButtonCallback()
				{
					public void invoke(long window, int button, int action, int mods)
					{
						if ( action==GLFW.GLFW_PRESS)
						{
							// right button press --> save a green box at the current location
							
							
							gotClick=true;
						}
					}
			
				});
		
		
	}
	

	int time=0;
	public Scene drawFrame(int delta) {
		glClearColor(1f, 1f, 1f, 1f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

		if (Game.ui.mouseButtonIsPressed(0)&& time==0)
		{
			x++;
			System.out.println(x);
			time=20;
		}
		if(time>0) {
		time--;
		}
	
		
		return this;
	}
	
	
	
}
