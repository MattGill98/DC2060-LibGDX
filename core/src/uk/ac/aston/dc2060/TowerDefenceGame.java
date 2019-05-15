package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import uk.ac.aston.dc2060.view.screen.TowerDefenceScreen;
import uk.ac.aston.dc2060.view.screen.WelcomeScreen;

/**
 * The entrypoint file. The screens are managed from this class.
 */
public class TowerDefenceGame extends ApplicationAdapter {

    public static TiledMap TILE_MAP;

    public static Skin GUI_SKIN;
    public static BitmapFont GUI_FONT;
    public static TextButtonStyle GUI_BUTTON_STYLE;

    private WelcomeScreen welcomeScreen;
    private Screen towerDefenceScreen;

    @Override
    public void create () {

        // Load tilemap
        TmxMapLoader.Parameters parameters = new TmxMapLoader.Parameters();
        parameters.textureMagFilter = Texture.TextureFilter.Nearest;
        parameters.textureMinFilter = Texture.TextureFilter.Nearest;
        TILE_MAP = new TmxMapLoader().load("tilemap.tmx", parameters);

        // Initialise GUI themes
        GUI_SKIN = new Skin();
        GUI_SKIN.addRegions(new TextureAtlas(Gdx.files.internal("theme/Holo-dark-xhdpi.atlas")));
        GUI_FONT = new BitmapFont(Gdx.files.internal("theme/Roboto-xhdpi.fnt"), false);
        GUI_BUTTON_STYLE = new TextButtonStyle();
        GUI_BUTTON_STYLE.up = GUI_SKIN.getDrawable("btn_default_normal");
        GUI_BUTTON_STYLE.down = GUI_SKIN.getDrawable("btn_default_pressed");
        GUI_BUTTON_STYLE.focused = GUI_SKIN.getDrawable("btn_default_focused");
        GUI_BUTTON_STYLE.font = GUI_FONT;

        welcomeScreen = new WelcomeScreen();
        welcomeScreen.onPlayButtonPressed(() -> {
            welcomeScreen.hide();
            towerDefenceScreen.show();
        });
        welcomeScreen.onExitButtonPressed(() -> {
            Gdx.app.exit();
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
