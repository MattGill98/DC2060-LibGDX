package uk.ac.aston.dc2060.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import uk.ac.aston.dc2060.TowerDefenceGame;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Tower Defence Game";
        config.width = 1088;
        config.height = 768;
        config.resizable = false;
        config.samples = 2;
        new LwjglApplication(new TowerDefenceGame(), config);
    }
}
