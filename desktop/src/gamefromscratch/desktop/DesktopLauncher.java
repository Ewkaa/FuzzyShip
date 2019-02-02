package gamefromscratch.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import gamefromscratch.Fuzzy;

import static gamefromscratch.Fuzzy.DEFAULT_HEIGHT;
import static gamefromscratch.Fuzzy.DEFAULT_WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.height = DEFAULT_HEIGHT;
		config.width = DEFAULT_WIDTH;
		config.title = "Fuzzy ship";

		new LwjglApplication(new Fuzzy(), config);
	}
}
