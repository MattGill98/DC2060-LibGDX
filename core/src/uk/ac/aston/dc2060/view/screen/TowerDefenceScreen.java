package uk.ac.aston.dc2060.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import uk.ac.aston.dc2060.controller.TowerDefenceStage;
import uk.ac.aston.dc2060.controller.spawner.TowerSpawner;
import uk.ac.aston.dc2060.model.tower.BasicTower;
import uk.ac.aston.dc2060.view.GridView;
import uk.ac.aston.dc2060.view.TowerDefenceMapRenderer;

import static uk.ac.aston.dc2060.TowerDefenceGame.TILE_MAP;

/**
 * A screen which runs the actual game.
 */
public class TowerDefenceScreen implements Screen {

    private boolean enabled;

    private OrthographicCamera camera;
    private Stage gameStage;

    private OrthogonalTiledMapRenderer mapRenderer;
    private GridView grid;

    public TowerDefenceScreen() {
        // Configure the game view
        int virtualMapWidth = (Integer) TILE_MAP.getProperties().get("width");
        int virtualMapHeight = (Integer) TILE_MAP.getProperties().get("height");
        int virtualWidth = 1 + virtualMapWidth;
        int virtualHeight = virtualMapHeight;
        this.camera = new OrthographicCamera(virtualWidth, virtualHeight);
        Viewport gameViewport = new FitViewport(virtualWidth, virtualHeight, camera);

        // Configure rendered scene
        this.gameStage = new TowerDefenceStage(gameViewport, virtualMapWidth, virtualMapHeight);
        this.mapRenderer = new TowerDefenceMapRenderer(camera, virtualMapWidth);
        this.grid = new GridView(camera, virtualWidth, virtualHeight);

        // Add GUI icons
        this.gameStage.addActor(new BasicTower(virtualMapWidth, 0, 0.1f, 500));
        this.gameStage.addActor(new BasicTower(virtualMapWidth, 1, 0.4f, 2000));

        // Add Tower generator
        this.gameStage.addListener(new TowerSpawner((TowerDefenceStage) gameStage));
    }

    @Override
    public void show() {
        enabled = true;
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void render(float delta) {
        if (enabled) {
            // Clear the window
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // Update the camera
            camera.update();

            // Render map
            mapRenderer.render();

            // Render grid
            grid.render();

            // Render actors
            gameStage.act();
            gameStage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        throw new UnsupportedOperationException("Resizing not supported.");
        //gameViewport.update(width, height);
        //camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        //camera.update();
    }

    @Override
    public void pause() {
        throw new UnsupportedOperationException("Pausing not supported.");
    }

    @Override
    public void resume() {
        throw new UnsupportedOperationException("Resuming not supported.");
    }

    @Override
    public void hide() {
        enabled = false;
    }

    @Override
    public void dispose() {
        grid.dispose();
        gameStage.dispose();
    }
}
