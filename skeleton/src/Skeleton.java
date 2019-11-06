import edu.utc.game.Game;
import edu.utc.game.*;
import edu.utc.game.GameObject;
import edu.utc.game.Scene;
import skeleton.SkeletonGame;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;





public class Skeleton{
	
	private static java.util.Random rand=new java.util.Random();
	public static void main(String[] args) {
		System.out.println("Hello");
		SkeletonGame game = new SkeletonGame();
		game.gameLoop();
	}


/*boolean gamePaused = false;
while(!Display.isCloseRequested()){

	if(Keyboard.isKeyDown(Keyboard.KEY_P) && !gamePaused) {
	    gamePaused = true;
	} else if(Keyboard.isKeyDown(Keyboard.KEY_P) && gamePaused) {
	    gamePaused = false;
	}

    if(gamePaused){
        while(true){
            System.out.println("Game paused.");
            if(Keyboard.isKeyDown(Keyboard.KEY_P)){
                gamePaused = false;
                break;
            }
        }
    }
    //...game logic
}
}*/
	

	
	
	
}