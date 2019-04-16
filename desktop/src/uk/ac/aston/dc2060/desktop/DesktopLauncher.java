package uk.ac.aston.dc2060.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import uk.ac.aston.dc2060.TowerDefenceGame;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Tower Defence Game";
        config.width = 800;
        config.height = 600;
        new LwjglApplication(new TowerDefenceGame(), config);
    }
}
