package skeleton;

import edu.utc.game.*;

public class SkeletonGame extends Game {
	
	public SkeletonGame()
	{
		initUI(640,480, "Game");
		registerGlobalCallbacks();

		
		SimpleMenu menu = new SimpleMenu();
		menu.addItem(new SimpleMenu.SelectableText(30,  30,  30,  30,  "play the Game", 1,  0,  0,  1,  1,  1), null);
		menu.addItem(new SimpleMenu.SelectableText(30,  130,  30,  30,  "Exit", 1,  0,  0,  1,  1,  1), null);
		menu.select(0);

		setScene(menu);

		
		
	}

}

