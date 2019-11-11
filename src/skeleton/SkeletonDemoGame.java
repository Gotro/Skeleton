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
import edu.utc.game.Sound;
import edu.utc.game.Text;
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

	Text text;

	ColorChangeText ctext;
	private boolean gotClick=false;
	// DemoGame instance data
	
	List<GameObject> targets;
	public int x =0;
	public int y = 0;
	public SkeletonDemoGame()
	{
		initUI(640,480,"Mouse Game");
    	//glClearColor(0.5f, 0.5f, 0.5f, 0.5f);

		
		// see also Game.ui.enableMouseCursor(false);
		Game.ui.showMouseCursor(false);
		//GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
		text = new Text(100,50, 30, 30, "0");
		ctext = new ColorChangeText(100,150, 30, 30, "begin!");
		text.setColor(1, 1, 1);
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
	
	int victory=0;
	int time=0;
	int time2=0;
	int time4=0;
	int time5=0;
	int pauseTime=0;
	int pause=0;
	public Scene drawFrame(int delta) {
		if(pause>0) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			//glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
			if (Game.ui.keyPressed(org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE))
			{
				pause=0;
			}
			ctext = new ColorChangeText(100,150, 30, 30, "pause");
			ctext.draw();
			return this;
		}
		else {
		Sound theSound;
		theSound=new Sound("res/sound.wav");
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
		//glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
		time5=time4;
		text = new Text(100,50, 30, 30, "time: "+ Integer.toString(time5/35));
		time4++;
		text.draw();
		ctext.draw();
		ctext.update(delta);
		time2++;
		if (Game.ui.mouseButtonIsPressed(0)&& time==0)
		{
			x++;
			theSound.play();
			System.out.println(x);
			time=20;
		}
		
		if(time>0) {
		time--;
		}
		if(time2>20) {
			//System.out.println("time");
			time2=0;
		}
		if(victory==0&& time2>19) {
		ctext = new ColorChangeText(100,150, 30, 30, Integer.toString(x));
		}
		if(x>10) {
			
			
			ctext = new ColorChangeText(100,150, 30, 30, "Victory");
			System.out.println("victory");
			x=0;
			victory++;
			
		}
		
		if (Game.ui.keyPressed(org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE))
		{
			pause++;
		}
		
		return this;
		}
	}
	
	
	
	
	private enum colorStates {TO_WHITE, TO_RED, TO_GREEN, TO_BLUE};

	private class ColorChangeText extends Text
    {
        colorStates color = colorStates.TO_RED;
        private boolean reddening = false;
        public ColorChangeText(int x, int y, int w, int h, String text){
            super(x,y,w,h, text);
        }

        public void update(int delta){
        	this.color = colorStates.TO_RED;
            
            
        }
    }

	
	
}

/*	boolean gamePaused = false;
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
*/
