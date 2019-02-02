package gamefromscratch;

import com.badlogic.gdx.Game;
import gamefromscratch.Screens.MainScreen;

public class Fuzzy extends Game {
	public static final int DEFAULT_WIDTH = 1600;
	public static final int DEFAULT_HEIGHT = 900;

	public Fuzzy() {
	}

	public void create() {
		this.setScreen(new MainScreen(this));
	}
}
