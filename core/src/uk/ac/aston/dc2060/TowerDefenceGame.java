package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import uk.ac.aston.dc2060.view.screen.TowerDefenceScreen;
import uk.ac.aston.dc2060.view.screen.WelcomeScreen;

/**
 * The entrypoint file. The screens are managed from this class.
 */
public class TowerDefenceGame extends ApplicationAdapter {

    public static TiledMap TILE_MAP;

    private WelcomeScreen welcomeScreen;
    private Screen towerDefenceScreen;

    @Override
    public void create () {
        TmxMapLoader.Parameters parameters = new TmxMapLoader.Parameters();
        parameters.textureMagFilter = Texture.TextureFilter.Linear;
        parameters.textureMinFilter = Texture.TextureFilter.Linear;
        TILE_MAP = new TmxMapLoader().load("tilemap.tmx", parameters);

        welcomeScreen = new WelcomeScreen();
        welcomeScreen.onPlayButtonPressed(() -> {
            welcomeScreen.hide();
            towerDefenceScreen.show();
        });
        welcomeScreen.onExitButtonPressed(() -> {
            System.exit(0);
        });
        welcomeScreen.show();

        towerDefenceScreen = new TowerDefenceScreen();
    }

    @Override
    public void render () {
        float delta = Gdx.graphics.getDeltaTime();
        welcomeScreen.render(delta);
        towerDefenceScreen.render(delta);
    }

    @Override
    public void dispose () {
        welcomeScreen.dispose();
        towerDefenceScreen.dispose();
    }
}
