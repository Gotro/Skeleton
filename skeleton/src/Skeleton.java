import edu.utc.game.Game;
import edu.utc.game.GameObject;
import skeleton.SkeletonGame;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;


public class Skeleton{
	public static void main(String[] args) {
		System.out.println("Hello");
		SkeletonGame game = new SkeletonGame();
		game.gameLoop();
	}
}