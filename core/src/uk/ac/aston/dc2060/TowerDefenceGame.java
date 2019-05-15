package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import uk.ac.aston.dc2060.view.screen.PauseScreen;
import uk.ac.aston.dc2060.view.screen.TowerDefenceScreen;
import uk.ac.aston.dc2060.view.screen.WelcomeScreen;

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
            towerDefenceScreen.grabInput();
        });
        welcomeScreen.onExitButtonPressed(() -> {
            Gdx.app.exit();
        });

        towerDefenceScreen = TowerDefenceScreen.createInstance();
        towerDefenceScreen.onKeyPress(Input.Keys.ESCAPE, () -> {
            towerDefenceScreen.pause();
            pauseScreen.show();
            pauseScreen.grabInput();
        });

        pauseScreen = new PauseScreen();
        pauseScreen.onKeyPress(Input.Keys.ESCAPE, () -> {
            towerDefenceScreen.resume();
            pauseScreen.hide();
            towerDefenceScreen.grabInput();
        });

        welcomeScreen.show();
        welcomeScreen.grabInput();
    }

    @Override
    public void render () {
        float delta = Gdx.graphics.getDeltaTime();

        // Clear the window
        Gdx.gl.glClearColor(0.5804f, 0.6941f, 0.7059f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

        welcomeScreen.render(delta);
        towerDefenceScreen.render(delta);
        pauseScreen.render(delta);
    }

    @Override
    public void dispose () {
        welcomeScreen.dispose();
        towerDefenceScreen.dispose();
    }
}
