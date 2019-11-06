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
import edu.utc.game.Sound;

 

public class SkeletonGame extends Game implements Scene{
	

	
	
	public SkeletonGame()
	{
		
		// inherited from the Game class, this sets up the window and allows us to access
		// Game.ui

		SkeletonDemoGame game=new SkeletonDemoGame();
		game.registerGlobalCallbacks();

		
		SimpleMenu menu = new SimpleMenu();
		menu.addItem(new SimpleMenu.SelectableText(30,  30,  30,  30,  "play the Game", 1,  0,  0,  1,  1,  1), game);
		menu.addItem(new SimpleMenu.SelectableText(30,  130,  30,  30,  "Exit", 1,  0,  0,  1,  1,  1), null);
		menu.select(0);

		game.setScene(menu);
		game.gameLoop();
		// screen clear is black (this could go in drawFrame if you wanted it to change
		glClearColor(.0f, .0f, .0f, .0f);
		
		
		
		
	}

	@Override
	public Scene drawFrame(int delta) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
