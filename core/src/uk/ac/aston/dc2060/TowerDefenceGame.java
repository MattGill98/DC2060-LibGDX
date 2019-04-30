package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import uk.ac.aston.dc2060.view.screen.TowerDefenceScreen;

/**
 * The entrypoint file. The screens are managed from this class.
 */
public class TowerDefenceGame extends ApplicationAdapter {

    private Screen towerDefenceScreen;

    @Override
    public void create () {
        towerDefenceScreen = new TowerDefenceScreen();
        towerDefenceScreen.show();
    }

    @Override
    public void render () {
        // Clear the window
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();
        towerDefenceScreen.render(delta);
    }

    @Override
    public void dispose () {
        towerDefenceScreen.dispose();
    }
}
