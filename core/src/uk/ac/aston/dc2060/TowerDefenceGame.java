package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import uk.ac.aston.dc2060.view.screen.PauseScreen;
import uk.ac.aston.dc2060.view.screen.TowerDefenceScreen;
import uk.ac.aston.dc2060.view.screen.WelcomeScreen;

import java.awt.event.KeyEvent;

/**
 * The entrypoint file. The screens are managed from this class.
 */
public class TowerDefenceGame extends ApplicationAdapter {

    public static TiledMap TILE_MAP;

    public static Skin GUI_SKIN;

    private WelcomeScreen welcomeScreen;
    private TowerDefenceScreen towerDefenceScreen;
    private PauseScreen pauseScreen;

    @Override
    public void create () {

        // Load tilemap
        TmxMapLoader.Parameters parameters = new TmxMapLoader.Parameters();
        parameters.textureMagFilter = Texture.TextureFilter.Nearest;
        parameters.textureMinFilter = Texture.TextureFilter.Nearest;
        TILE_MAP = new TmxMapLoader().load("tilemap.tmx", parameters);

        // Initialise GUI themes
        GUI_SKIN = new Skin(new TextureAtlas(Gdx.files.internal("theme/Holo-dark-xhdpi.atlas")));
        GUI_SKIN.load(Gdx.files.internal("theme/Holo-dark-xhdpi.json"));

        welcomeScreen = new WelcomeScreen();
        welcomeScreen.onPlayButtonPressed(() -> {
            welcomeScreen.hide();
            towerDefenceScreen.show();
        });
        welcomeScreen.onExitButtonPressed(() -> {
            Gdx.app.exit();
        });

        towerDefenceScreen = TowerDefenceScreen.createInstance();
        towerDefenceScreen.onKeyPress(Input.Keys.ESCAPE, () -> {
            towerDefenceScreen.pause();
            pauseScreen.show();
        });

        pauseScreen = new PauseScreen();

        welcomeScreen.show();
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
